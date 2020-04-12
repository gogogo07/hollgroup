package com.holl.wechat.dao;

import com.holl.wechat.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderMapper {

    List<Order> selectAll();

    List<Order> selectAllOld();

    Order selectOrderById(String id);

    int publishOrder(Order order);

    int startOrder(@Param("id") String id, @Param("startTime") String startTime);

    int deleteOrder(String id);

    int insertIntoOld(String id);

    int setOrderFinishTime(String id, String finishTime);
}
