package com.holl.wechat.dao;

import com.holl.wechat.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealMapper {

    List<Deal> selectAll();

    List<Deal> selectPublishedDeal();          //查找所有发布但还没有接单的任务

    List<Deal> selectMyPublishedDealWithoutAccept(String from_id); //查找我发布的但是还没有人接单的订单

    List<Deal> selectMyPublishedDeal(String from_id);   //查找我发布的有人接单的订单

    Deal selectDealById(String order_id);   //通过订单号查找订单的详细信息

    Deal selectDealWithoutAcceptById(String order_id);    //查找还没有人接单的订单的详细信息

    List<Deal> selectMyAcceptDeal(String to_id);      //查找我接受的但是还没结束的订单

    List<Deal> selectHistoryDeal(String from_id);          //根据id查找用户的历史订单

    Deal selectHistoryDealById(String order_id);

    int publishDeal(Deal deal);

    int startDeal(Long id, String openId);

    int deleteDeal(Long id);

    int insertIntoOld(Long id);
}
