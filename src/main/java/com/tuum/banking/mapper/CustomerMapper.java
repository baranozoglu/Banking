package com.tuum.banking.mapper;

import com.tuum.banking.model.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customers where id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="name", column ="name"),
            @Result(property="surname", column ="surname"),
            @Result(property="email", column ="email"),
            @Result(property="phone", column ="phone"),
            @Result(property="createdAt", column ="created_at"),
            @Result(property="updatedAt", column ="updated_at"),
    })
    Customer findById(@Param("id") Long id);

    @Select("INSERT INTO customers (name, surname, email, phone) VALUES (#{name}, #{surname}, #{email}, #{phone}) RETURNING id")
    Long addCustomer(Customer customer);

    @Select("UPDATE customers SET name=#{name}, surname =#{surname}, phone =#{phone}, email =#{email} WHERE id =#{id} RETURNING id")
    Long updateCustomer(Customer customer);

    @Select("DELETE FROM customers WHERE id =#{id} RETURNING id")
    Long deleteCustomer(Long id);
}
