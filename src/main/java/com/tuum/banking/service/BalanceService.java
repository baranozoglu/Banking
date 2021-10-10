package com.tuum.banking.service;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.exception.GeneralException;
import com.tuum.banking.exception.NotFoundException;
import com.tuum.banking.mapper.BalanceMapper;
import com.tuum.banking.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BalanceService {

    final private BalanceMapper balanceMapper;

    public List<Balance> getBalanceByAccountId(Long id) {
        final List<Balance> balanceList = balanceMapper.findByAccountId(id);
        if(!Objects.nonNull(balanceList) || balanceList.isEmpty()) {
            throw new NotFoundException();
        }
        return balanceList;
    }

    public List<Balance> createBalances(List<CurrencyEnum> currencyList, Long accountLastId) {
        try {
            final List<Balance> balanceList = new ArrayList<>();
            currencyList.forEach(currencyEnum -> {
                balanceList.add(new Balance(accountLastId + 1, Double.valueOf(0), currencyEnum));
            });
            return balanceList;
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public Balance addBalance(Balance request) {
        try {
            final Long _id = balanceMapper.addBalance(request);
            final Balance balance = balanceMapper.findById(_id);
            return balance;
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public Balance updateBalance(Balance request) {
        try {
            request.setUpdatedAt(LocalDateTime.now());
            Long _id = balanceMapper.updateBalance(request);
            Balance balance = balanceMapper.findById(_id);
            return balance;
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public Balance getBalance(Long id) {
        try {
            final Balance balance = balanceMapper.findById(id);
            return balanceMapper.findById(id);
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public Balance deleteBalance(Long id) {
        try {
            Long _id = balanceMapper.deleteBalance(id);
            return Balance.builder()
                    .id(_id)
                    .build();
        } catch (Exception e) {
            throw new GeneralException();
        }
    }


}
