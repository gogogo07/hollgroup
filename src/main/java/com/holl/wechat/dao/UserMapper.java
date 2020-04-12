package com.holl.wechat.dao;

import com.holl.wechat.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectAll();

    User selectUserById(String id);

    int updateUser(User user);

    int insertUser(User user);
}
