package com.holl.wechat.service.impl;

import com.holl.wechat.dao.OrderRepository;
import com.holl.wechat.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderRepository orderRepository;


}
