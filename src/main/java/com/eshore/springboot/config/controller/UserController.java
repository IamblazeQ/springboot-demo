package com.eshore.springboot.config.controller;

import com.eshore.springboot.config.bean.User;
import com.eshore.springboot.config.dao.IUserDAO;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Blaze on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserDAO userDAO;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAccounts() {
        return userDAO.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "userName", required = true) String userName,
                                @RequestParam(value = "age", required = true) int age) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setAge(age);
        User r_user = userDAO.saveAndFlush(user);
        return r_user.toString();

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "age") int age) {
        User user = new User();

        if (!Strings.isNullOrEmpty(userName)){
            user.setUserName(userName);
        }

        if (!StringUtils.isEmpty(age)){
            user.setAge(age);
        }
        User r_user = userDAO.save(user);
        return r_user.toString();

    }

}
