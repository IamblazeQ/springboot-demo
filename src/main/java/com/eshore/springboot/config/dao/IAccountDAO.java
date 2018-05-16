package com.eshore.springboot.config.dao;

import com.eshore.springboot.config.bean.Account;

import java.util.List;

/**
 * Created by Blaze on 2018/5/15.
 */
public interface IAccountDAO {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
