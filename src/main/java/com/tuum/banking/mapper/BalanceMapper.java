package com.tuum.banking.mapper;

import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BalanceMapper {

    @Select("SELECT * FROM balances where id = #{id}")
    Balance findById(@Param("id") Long id);

    @Select("SELECT * FROM balances where account_id = #{id}")
    List<Balance> findByAccountId(@Param("id") Long id);

    @Select("INSERT INTO balances (account_id, available_amount, currency) VALUES (#{accountId}, #{availableAmount}, #{currency}) RETURNING id")
    Long addBalance(Balance balance);

    @Select("UPDATE balances SET account_id=#{accountId}, available_amount =#{availableAmount}, currency =#{currency} WHERE id =#{id} RETURNING id")
    Long updateBalance(Balance balance);

    @Select("DELETE FROM balances WHERE id =#{id} RETURNING id")
    Long deleteBalance(Long id);
}
