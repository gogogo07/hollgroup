package com.holl.wechat.service.impl;

import com.holl.wechat.dao.OrderMapper;
import com.holl.wechat.model.Order;
import com.holl.wechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public Order selectOrderById(String id) {
        return orderMapper.selectOrderById(id);
    }

    @Override
    public int publishOrder(Order order) {
        return orderMapper.publishOrder(order);
    }

    @Override
    public int startOrder(String id) {
        return orderMapper.startOrder(id, new Timestamp(System.currentTimeMillis()).toString().substring(0, 19));
    }

    @Override
    public int finishOrder(String id) {
        int delete = orderMapper.deleteOrder(id);
        if (delete == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int insertIntoOld(String id) {
        int insert = orderMapper.insertIntoOld(id);
        int update = orderMapper.setOrderFinishTime(id, new Timestamp(System.currentTimeMillis()).toString().substring(0, 19));
        if (insert == 1 && update == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
