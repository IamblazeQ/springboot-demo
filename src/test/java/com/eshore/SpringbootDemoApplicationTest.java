package com.eshore;

import com.eshore.springboot.config.bean.Account;
import com.eshore.springboot.config.dao.IAccountDao;
import com.eshore.springboot.config.dao.impl.RedisDaoImpl;
import com.eshore.springboot.config.service.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class SpringbootDemoApplicationTest {

    public static Logger logger = LoggerFactory.getLogger(SpringbootDemoApplicationTest.class);

    @Test
    public void contextLoads() {

    }

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Bean("jsonRedisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //解决日期序列化问题
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        return template;
    }

    @Test
    public void set() {
        redisTemplate.opsForValue().set("ccc", "13");
    }
    ///////////////////Test  Redis/////////////
    @Autowired
    RedisDaoImpl redisDaoImpl;
    @Test
    public void testRedis(){
        redisDaoImpl.setKey("name","ddd");
        redisDaoImpl.setKey("age","11");
        logger.info(redisDaoImpl.getValue("name"));
        logger.info(redisDaoImpl.getValue("age"));
    }

    ///////////////////Test  MongoDB/////////////

    @Autowired
    private IAccountDao accountDao;

    @Test
    public void testSaveMongoAccount(){
        Account account = new Account();
        account.setId(4);
        account.setName("Redis");
        account.setMoney(1900d);
        accountDao.saveMongoAccount(account);
    }
    /**
     * 根据名称查询对象
     */
    @Test
    public void testFindMongoAccountByName(){
        String name = "Redis";
        Account account =accountDao.findMongoAccountByName(name);
        System.out.println("account is "+account);
    }

    /**
     * 更新对象
     */
    @Test
    public void testUpdateMongoAccount(){
        Account account = new Account();
        account.setId(1);
        account.setName("Change to MongoDB");
        account.setMoney(2000d);
        accountDao.updateMongoAccount(account);
    }

    /**
     * 删除对象
     */
    @Test
    public void testDeleteMongoAccountById(){
        accountDao.deleteMongoAccountById(6);
    }

    @Autowired
    IAccountService accountService;

    @Test
    public void testAccountList(){
        List<Account> accountList1 =accountService.findAccountList();
        for (Account account1 : accountList1) {
            System.out.println("时间："+new Date()+"数据："+account1.getName());
        }
        System.out.println("first line finish,begin to read cache");
        List<Account> accountList2 =accountService.findAccountList();
        for (Account account2 : accountList2) {
            System.out.println("时间："+new Date()+"数据："+account2.getName());
        }
    }


}