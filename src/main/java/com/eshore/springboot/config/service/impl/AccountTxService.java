package com.eshore.springboot.config.service.impl;

import com.eshore.springboot.config.dao.IAccountTxMapper;
import com.eshore.springboot.config.service.IAccountTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Blaze on 2018/6/20.
 */
@Service
public class AccountTxService implements IAccountTxService{
    @Autowired
    IAccountTxMapper accountTxMapper;

    @Transactional
    public void updateTransactional() throws RuntimeException{
        accountTxMapper.update(90,1);//用户1减10块 用户2加10块
        int i=1/0;
        accountTxMapper.update(110,2);
    }
}
