package com.buyanne.service.impl;

import com.buyanne.mapper.UserMapper;
import com.buyanne.pojo.entity.User;
import com.buyanne.service.UserService;
import com.buyanne.utils.Md5Util;
import com.buyanne.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePasword(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        newPwd=Md5Util.getMD5String(newPwd);
        userMapper.updatePassword(newPwd,id);
    }
}
