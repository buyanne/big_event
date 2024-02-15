package com.buyanne.service;

import com.buyanne.pojo.entity.User;

public interface UserService {
    User findUserByName(String username);

    void register(String username,String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePasword(String newPwd);
}
