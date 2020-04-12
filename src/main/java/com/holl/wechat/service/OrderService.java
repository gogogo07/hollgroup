package com.holl.wechat.service;

import com.holl.wechat.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> selectAll();

    Order selectOrderById(String id);

    int publishOrder(Order order);

    int startOrder(String id);

    int finishOrder(String id);

    int insertIntoOld(String id);
}
