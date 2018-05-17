package com.eshore.springboot.config.dao;

import com.eshore.springboot.config.bean.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Blaze on 2018/5/17.
 */
@Mapper
@Component(value = "accountMapper")
public interface IAccountMapper {

    @Insert("insert into account(name,money) values (#{name},#{money})")
    int add(@Param("name") String name,@Param("money") double money);

    @Delete("delete from account where id=#{id}")
    int delete(@Param("id") int id);

    @Update("update account set name=#{name}, money=#{money} where id=#{id}")
    int update(@Param("name") String name,@Param("money") double money,@Param("id") int id);

    @Select("select id, name as name, money as money from account where id = #{id}")
    Account findAccount(@Param("id") int id);

    @Select("select id, name as name, money as money from account")
    List<Account> findAccountList();

}
