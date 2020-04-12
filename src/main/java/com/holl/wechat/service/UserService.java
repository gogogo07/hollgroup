package com.holl.wechat.service;

import com.holl.wechat.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(String id);

    int updateUser(User user);

    int insertUser(User user);
}
