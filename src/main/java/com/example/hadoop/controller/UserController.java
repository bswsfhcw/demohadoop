package com.example.hadoop.controller;


import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.hadoop.entity.User;
import com.example.hadoop.servie.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * http://localhost:8980/hadoop/user/save
     */
    @ApiOperation(value = "新增用户")
    @GetMapping(value ="/save")
    public Object usersave(){
        User user = new User();
        user.setXm("11111");
        userService.save(user);
        return  user;
    }
}
