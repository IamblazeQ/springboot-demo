package com.eshore.springboot.config.controller;

import com.eshore.springboot.config.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Blaze on 2018/5/10.
 */
@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class ConfigController {

    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String indexController(){
        String msg = configBean.getGreeting()+",age:"+configBean.getAge();
        return msg;
    }

}
