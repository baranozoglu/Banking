package com.tuum.banking.mapper;

import com.tuum.banking.model.Transaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM transactions where id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="accountId", column ="account_id"),
            @Result(property="amount", column ="amount"),
            @Result(property="currency", column ="currency"),
            @Result(property="direction", column ="direction"),
            @Result(property="description", column ="description"),
            @Result(property="balanceAfterTransaction", column ="balance_after_transaction"),
            @Result(property="createdAt", column ="created_at"),
    })
    Transaction findById(@Param("id") Long id);

    @Select("SELECT * FROM transactions where account_id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="accountId", column ="account_id"),
            @Result(property="amount", column ="amount"),
            @Result(property="currency", column ="currency"),
            @Result(property="direction", column ="direction"),
            @Result(property="description", column ="description"),
            @Result(property="balanceAfterTransaction", column ="balance_after_transaction"),
            @Result(property="createdAt", column ="created_at"),
    })
    List<Transaction> findByAccountId(@Param("id") Long id);

    @Select("INSERT INTO transactions (account_id, amount, direction, description, currency, balance_after_transaction) VALUES (#{accountId}, #{amount}, #{direction}, #{description}, #{currency}, #{balanceAfterTransaction}) RETURNING id")
    Long addTransaction(Transaction transaction);

    @Select("UPDATE transactions SET account_id=#{accountId}, amount =#{amount}, direction =#{direction}, description =#{description}, balance_after_transaction =#{balanceAfterTransaction} WHERE id =#{id} RETURNING id")
    Long updateTransaction(Transaction transaction);

    @Select("DELETE FROM transactions WHERE id =#{id} RETURNING id")
    Long deleteTransaction(Long id);
}
