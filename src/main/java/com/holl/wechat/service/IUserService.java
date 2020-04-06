package com.holl.wechat.service;

import com.holl.wechat.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(String id);

}
