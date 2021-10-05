package com.tuum.banking.mapper;

import com.tuum.banking.model.Account;
import com.tuum.banking.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts where id = #{id}")
    Account findById(@Param("id") Long id);

    @Select("INSERT INTO accounts (customer_id, country) VALUES (#{customerId}, #{country}) RETURNING id")
    Long addAccount(Account account);

    @Select("UPDATE accounts SET customer_id=#{customerId}, country =#{country} WHERE id =#{id} RETURNING id")
    Long updateAccount(Account account);

    @Select("DELETE FROM accounts WHERE id =#{id} RETURNING id")
    Long deleteAccount(Long id);
}
