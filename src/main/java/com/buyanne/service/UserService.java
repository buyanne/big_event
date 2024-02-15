package com.buyanne.service;

import com.buyanne.pojo.entity.User;

public interface UserService {
    User findUserByName(String username);

    void register(String username,String password);
}
