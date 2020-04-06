package com.holl.wechat.service.impl;

import com.holl.wechat.dao.UserRepository;
import com.holl.wechat.model.User;
import com.holl.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        Optional<User> tmp = userRepository.findById(id);
        if (!tmp.isPresent()) {
            return tmp.get();
        } else {
            return null;
        }
    }
}
