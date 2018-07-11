package com.eshore.springboot.config.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Blaze on 2018/5/17.
 */
@Mapper
@Component(value = "accountTxMapper")
public interface IAccountTxMapper {

    int update(@Param("money") double money, @Param("id") int  id);
}
