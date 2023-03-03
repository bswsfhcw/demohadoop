package com.example.hadoop.servie.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hadoop.dao.UserMapper;
import com.example.hadoop.entity.User;
import com.example.hadoop.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

    @Autowired
    private UserMapper userMapper;
}
