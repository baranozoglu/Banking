package com.tuum.banking.mapper;

import com.tuum.banking.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts where id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="customer", column ="customer_id" , one = @One(select = "com.tuum.banking.mapper.CustomerMapper.findById")),
            @Result(property="country", column ="country" ),
            @Result(property="createdAt", column ="created_at" ),
            @Result(property="updatedAt", column ="updated_at" ),
            @Result(property="balances", column ="id", javaType = List.class, many = @Many(select="com.tuum.banking.mapper.BalanceMapper.findByAccountId")),
    })
    Account findById(@Param("id") Long id);

    @Select("INSERT INTO accounts (customer_id, country) VALUES (#{customerId}, #{country}) RETURNING id")
    Long addAccount(Account account);

    @Select("UPDATE accounts SET customer_id=#{customerId}, country =#{country} WHERE id =#{id} RETURNING id")
    Long updateAccount(Account account);

    @Select("DELETE FROM accounts WHERE id =#{id} RETURNING id")
    Long deleteAccount(Long id);

    @Select("SELECT id FROM accounts ORDER BY id LIMIT 1")
    Long getLastId();
}
