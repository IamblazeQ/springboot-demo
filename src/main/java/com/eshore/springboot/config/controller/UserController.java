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
    /**
     * @api {POST} /register 注册用户
     * @apiGroup Users
     * @apiVersion 0.0.1
     * @apiDescription 用于注册用户
     * @apiParam {String} account 用户账户名
     * @apiParam {String} password 密码
     * @apiParam {String} mobile 手机号
     * @apiParam {int} vip = 0  是否注册Vip身份 0 普通用户 1 Vip用户
     * @apiParam {String} [recommend] 邀请码
     * @apiParamExample {json} 请求样例：
     *                ?account=sodlinken&password=11223344&mobile=13739554137&vip=0&recommend=
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (200) {int} code 0 代表无错误 1代表有错误
     * @apiSuccessExample {json} 返回样例:
     *                {"code":"0","msg":"注册成功"}
     */
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
