package com.tuum.banking.mapper;

import com.tuum.banking.model.Transaction;
import com.tuum.banking.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM transactions where id = #{id}")
    Transaction findById(@Param("id") Long id);

    @Select("SELECT * FROM transactions where account_id = #{id}")
    List<Transaction> findByAccountId(@Param("id") Long id);

    @Select("INSERT INTO transactions (account_id, amount, direction, description, balance_after_transaction) VALUES (#{customerId}, #{amount}, #{direction}, #{description}, #{balanceAfterTransaction}) RETURNING id")
    Long addTransaction(Transaction transaction);

    @Select("UPDATE transactions SET account_id=#{accountId}, amount =#{amount}, direction =#{direction}, description =#{description}, balance_after_transaction =#{balanceAfterTransaction} WHERE id =#{id} RETURNING id")
    Long updateTransaction(Transaction transaction);

    @Select("DELETE FROM transactions WHERE id =#{id} RETURNING id")
    Long deleteTransaction(Long id);
}
