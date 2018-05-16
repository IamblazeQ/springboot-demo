package com.eshore.springboot.config.dao;

import com.eshore.springboot.config.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Blaze on 2018/5/16.
 */
public interface IUserDAO extends JpaRepository<User,Integer>{
}
