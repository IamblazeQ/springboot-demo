package com.eshore.springboot.config.dao;

import com.eshore.springboot.config.bean.Account;

import java.util.List;

/**
 * Created by Blaze on 2018/5/15.
 */
public interface IAccountDao {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
////////////MO
    /**
     * 创建对象
     * @param account
     */

     void saveMongoAccount(Account account);
    /**
     * 根据名称查询对象
     * @param name
     * @return
     */
    Account findMongoAccountByName(String name);

    /**
     * 更新对象
     * @param account
     */
    void updateMongoAccount(Account account);

    /**
     * 删除对象
     * @param id
     */
    void deleteMongoAccountById(int id);
}
