package com.eshore.springboot.config.service.impl;

import com.eshore.springboot.config.bean.Account;
import com.eshore.springboot.config.dao.IAccountDao;
import com.eshore.springboot.config.dao.IAccountMapper;
import com.eshore.springboot.config.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Blaze on 2018/5/16.
 */
@Service
@Component
public class AccountService implements IAccountService {

    @Autowired
    IAccountDao accountDAO;

    @Autowired
    IAccountMapper accountMapper;

    //实现jpa方法
    @Override
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {

        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }

    //下面实现mybatis方法
    @Override
    public int add_m(String name, double money) {
        return accountMapper.add(name, money);
    }
    @Override
    public int update_m(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }
    @Override
    public int delete_m(int id) {
        return accountMapper.delete(id);
    }
    @Override
    public Account findAccount_m(int id) {
        return accountMapper.findAccount(id);
    }

    @Override
    public List<Account> findAccountList_m() {
        return accountMapper.findAccountList();
    }

}
