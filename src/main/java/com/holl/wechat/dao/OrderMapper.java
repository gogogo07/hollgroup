package com.holl.wechat.dao;

import com.holl.wechat.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    List<Order> selectAll();

    List<Order> selectAllOld();

    Order selectOrderById(Long id);

    int publishOrder(Order order);

    int startOrder(Long id, String startTime);

    int deleteOrder(Long id);

    int insertIntoOld(Long id);

    int setOrderFinishTime(Long id, String finishTime);

    Long getOrderMaxId();

    Long getOrderOldMaxId();
}
