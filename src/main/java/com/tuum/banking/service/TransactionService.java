package com.tuum.banking.service;

import com.tuum.banking.converter.TransactionConverter;
import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.exception.*;
import com.tuum.banking.mapper.TransactionMapper;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    final private TransactionMapper transactionMapper;
    final private BalanceService balanceService;
    final private AccountService accountService;
    final private TransactionConverter transactionConverter;

    private final DirectExchange exchange;
    private final AmqpTemplate rabbitTemplate;
    @Value("${sample.rabbitmq.routingKey1}")
    String routingKey1;
    @Value("${sample.rabbitmq.queue1}")
    String queueName1;

    public List<TransactionResponse> getTransactionByAccountId(Long id) {
        accountService.getAccount(id);
        final List<Transaction> transactionList = transactionMapper.findByAccountId(id);
        if(!Objects.nonNull(transactionList) || transactionList.isEmpty()) {
            throw new NotFoundException();
        }
        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionList.forEach(t -> transactionResponseList.add(transactionConverter.entityToResponse(t)));
        return transactionResponseList;
    }

    @Transactional
    public TransactionResponse addTransaction(TransactionRequest request) throws ResponseStatusException {
        if(!Objects.nonNull(request.getDescription())) {
            throw new DescriptionMissingException();
        }
        final Balance accountBalance = getBalance(request.getAccountId(), request.getCurrency());

        final Double balanceAfterTransaction = checkBalance(accountBalance, DirectionEnum.of(request.getDirection()), request.getAmount());

        accountBalance.setAvailableAmount(balanceAfterTransaction);
        final Balance balance = balanceService.updateBalance(accountBalance);

        final Long _id = transactionMapper.addTransaction(transactionConverter.requestToEntity(request, balance, balanceAfterTransaction));
        final Transaction transaction = transactionMapper.findById(_id);

        return transactionConverter.entityToResponse(transaction);
    }

    public Double checkBalance(Balance balance, DirectionEnum direction, Double amount) {
        if(!Objects.nonNull(balance)) {
            throw new NotFoundException();
        }
        if(Objects.equals(direction, DirectionEnum.OTHER)) {
            throw new InvalidDirectionException();
        }
        if(!Objects.nonNull(amount) || amount < 0) {
            throw new InvalidAmountException();
        }
        Double balanceAfterTransaction;
        if (Objects.equals(direction, DirectionEnum.IN)) {
            balanceAfterTransaction = balance.getAvailableAmount() + amount;
        }
        else {
            if(balance.getAvailableAmount() < amount) {
                throw new InsufficientFundsException();
            }
            balanceAfterTransaction = balance.getAvailableAmount() - amount;
        }
        return balanceAfterTransaction;
    }

    public Balance getBalance(Long accountId, String requestCurrency) {
        final List<Balance> accountBalanceList = balanceService.getBalanceByAccountId(accountId);

        return accountBalanceList.stream().filter(f -> {
            final CurrencyEnum currency = CurrencyEnum.of(requestCurrency);
            if(Objects.equals(CurrencyEnum.OTHER, currency)) {
                throw new InvalidCurrencyException();
            }
            return Objects.equals(f.getCurrency(), currency);
        }).findFirst().orElse(null);
    }

    public TransactionResponse deleteTransaction(Long id) {
        try {
            Long _id = transactionMapper.deleteTransaction(id);
            return TransactionResponse.builder()
                    .transactionId(_id)
                    .build();
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public void transactionStart(TransactionRequest request) {
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey1, request);
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue1}")
    public TransactionResponse callAddTransaction(TransactionRequest request) {
        return addTransaction(request);
    }

}
