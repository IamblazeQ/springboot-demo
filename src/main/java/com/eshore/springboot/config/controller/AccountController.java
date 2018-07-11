package com.eshore.springboot.config.controller;

import com.eshore.springboot.config.bean.Account;
import com.eshore.springboot.config.service.IAccountService;
import com.eshore.springboot.config.service.IAccountTxService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by Blaze on 2018/5/16.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IAccountTxService accountTxService;


  /**
    @Api：修饰整个类，描述Controller的作用
    @ApiOperation：描述一个类的一个方法，或者说一个接口
    @ApiParam：单个参数描述
    @ApiModel：用对象来接收参数
    @ApiProperty：用对象接收参数时，描述对象的一个字段
    @ApiResponse：HTTP响应其中1个描述
    @ApiResponses：HTTP响应整体描述
    @ApiIgnore：使用该注解忽略这个API
    @ApiError ：发生错误返回的信息
    @ApiParamImplicitL：一个请求参数
    @ApiParamsImplicit 多个请求参数
    **/


    @ApiIgnore//使用该注解忽略这个API
    //mybatis Transactional test
    @RequestMapping(value = "/testMybatisTrans",method = RequestMethod.GET)
    public void testMybatisTrans(){
        accountTxService.updateTransactional();
    }

    @ApiOperation(value="获取账号列表", notes="获取账号列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)

    public List<Account> getAccounts(){
        //return accountService.findAccountList();//jpa
        return accountService.findAccountList_m(); //mybatis
    }

    @ApiOperation(value="获取账户详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int",paramType = "path")
    @RequestMapping(value = "/query/{id}",method = RequestMethod.GET)
    public  Account getAccountById(@PathVariable("id") int id){
        //return accountService.findAccountById(id);
        return accountService.findAccount_m(id);//mybatis
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public int delete(@PathVariable("id") int id){
        return accountService.delete(id);
    }

    @ApiOperation(value="更新信息", notes="根据url的id来指定账户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账户ID", required = true, dataType = "Int",paramType = "path"),
            @ApiImplicitParam(name = "name", value = "图书名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String saveAccount( @RequestParam(value = "name")String name,
                                @RequestParam(value = "money" )double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.add(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }
}
