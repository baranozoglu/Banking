package com.tuum.banking.mapper;

import com.tuum.banking.model.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customers where id = #{id}")
    Customer findById(@Param("id") Long id);

    @Select("INSERT INTO customers (name, surname, email, phone) VALUES (#{name}, #{surname}, #{email}, #{phone}) RETURNING id")
    Long addCustomer(Customer customer);

    @Select("UPDATE customers SET name=#{name}, surname =#{surname}, phone =#{phone}, email =#{email} WHERE id =#{id} RETURNING id")
    Long updateCustomer(Customer customer);

    @Select("DELETE FROM customers WHERE id =#{id} RETURNING id")
    Long deleteCustomer(Long id);
}
