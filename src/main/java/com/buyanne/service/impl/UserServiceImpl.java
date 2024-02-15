package com.buyanne.service.impl;

import com.buyanne.mapper.UserMapper;
import com.buyanne.pojo.entity.User;
import com.buyanne.service.UserService;
import com.buyanne.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void register(String username, String password) {
        String pwd= Md5Util.getMD5String(password);
        userMapper.add(username,pwd);
    }
}
