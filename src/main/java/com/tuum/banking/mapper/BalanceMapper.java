package com.tuum.banking.mapper;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.model.Balance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BalanceMapper {

    @Select("SELECT * FROM balances where id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="accountId", column ="account_id"),
            @Result(property="availableAmount", column ="available_amount"),
            @Result(property="currency", column ="currency"),
            @Result(property="createdAt", column ="created_at"),
    })
    Balance findById(@Param("id") Long id);

    @Select("SELECT * FROM balances where account_id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="accountId", column ="account_id"),
            @Result(property="availableAmount", column ="available_amount"),
            @Result(property="currency", column ="currency"),
            @Result(property="createdAt", column ="created_at"),
    })
    List<Balance> findByAccountId(@Param("id") Long id);

    @Select("INSERT INTO balances (account_id, available_amount, currency) VALUES (#{accountId}, #{availableAmount}, #{currency}) RETURNING id")
    Long addBalance(Balance balance);

    @Select("UPDATE balances SET account_id=#{accountId}, available_amount =#{availableAmount}, currency =#{currency}, updated_at =#{updatedAt} WHERE id =#{id} RETURNING id")
    Long updateBalance(Balance balance);

    @Select("DELETE FROM balances WHERE id =#{id} RETURNING id")
    Long deleteBalance(Long id);
}
