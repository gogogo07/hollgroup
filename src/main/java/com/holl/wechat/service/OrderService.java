package com.holl.wechat.service;

import com.holl.wechat.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> selectAll();

    Order selectOrderById(Long id);

    int publishOrder(Order order);

    int startOrder(Long id);

    int finishOrder(Long id);

    int insertIntoOld(Long id);

    Long getMaxId();
}
