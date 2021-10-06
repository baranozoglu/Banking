package com.tuum.banking.service;

import com.tuum.banking.converter.AccountConverter;
import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.exception.GeneralException;
import com.tuum.banking.exception.InvalidAccountException;
import com.tuum.banking.mapper.AccountMapper;
import com.tuum.banking.model.Account;
import com.tuum.banking.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    final private AccountMapper accountMapper;
    final private AccountConverter accountConverter;
    final private BalanceService balanceService;

    public AccountResponse getAccount(Long id) {
        final Account account = accountMapper.findById(id);
        if(!Objects.nonNull(account)) {
            throw new InvalidAccountException();
        }
        return accountConverter.entityToResponse(account);
    }

    @Transactional
    public AccountResponse addAccount(AccountRequest request) {
        try {
            final List<Balance> balances = balanceService.createBalances(request.getCurrencyList(), accountMapper.getLastId());
            final Long accountId = accountMapper.addAccount(accountConverter.requestToEntity(request, balances));

            balances.forEach(balance -> {
                balance.setAccountId(accountId);
                balanceService.addBalance(balance);
            });

            final Account account = accountMapper.findById(accountId);
            return accountConverter.entityToResponse(account);
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public AccountResponse updateAccount(AccountRequest request) {
        try {
            Long _id = accountMapper.updateAccount(accountConverter.requestToEntity(request, null));
            return AccountResponse.builder()
                    .accountId(_id)
                    .build();
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public AccountResponse deleteAccount(Long id) {
        try {
            Long _id = accountMapper.deleteAccount(id);
            return AccountResponse.builder()
                    .accountId(_id)
                    .build();
        } catch (Exception e) {
            throw new GeneralException();
        }
    }
}
