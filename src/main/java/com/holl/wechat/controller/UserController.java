package com.holl.wechat.controller;

import com.holl.wechat.model.User;
import com.holl.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public String login(@RequestParam(name = "code", defaultValue = " ") String code) {
        return null;
    }

    @RequestMapping("/findALL")
    public Map<String, List<User>> findAll() {
        Map<String, List<User>> modelMap = new HashMap<>();
        if (userService.findAll().size() != 0) {
            modelMap.put("users", userService.findAll());
        } else {
            modelMap.put("users", null);
        }
        return modelMap;
    }
}
