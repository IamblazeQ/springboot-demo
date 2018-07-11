package com.eshore.springboot.config.dao.impl;

import com.eshore.springboot.config.bean.Account;
import com.eshore.springboot.config.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Blaze on 2018/5/15.
 */
@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public int add(Account account) {
        return jdbcTemplate.update("insert into account(name, money) values(?, ?)",
                account.getName(),account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE  account SET NAME=? ,money=? WHERE id=?",
                account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from TABLE account where id=?",id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> list = jdbcTemplate.query("select * from account where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Account.class));
        if(list!=null && list.size()>0){
            Account account = list.get(0);
            return account;
        }else{
            return null;
        }
    }

    @Override
    @Cacheable("account")
    public List<Account> findAccountList() {

        simulateSlowService();//spring cache测试

        List<Account> list = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
/////////////////////////////Test MongoDB///////////////////////////////
    @Override
    public void saveMongoAccount(Account account) {
        mongoTemplate.save(account);
    }

    @Override
    public Account findMongoAccountByName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        Account user =  mongoTemplate.findOne(query , Account.class);
        return user;
    }

    @Override
    public void updateMongoAccount(Account account) {
        Query query=new Query(Criteria.where("id").is(account.getId()));
        Update update= new Update().set("name", account.getName()).set("money", account.getMoney());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Account.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    @Override
    public void deleteMongoAccountById(int id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Account.class);
    }

    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
