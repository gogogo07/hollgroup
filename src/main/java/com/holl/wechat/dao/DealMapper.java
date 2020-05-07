package com.holl.wechat.dao;

import com.holl.wechat.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealMapper {

    List<Deal> selectAll();

    List<Deal> selectPublishedDeal();          //查找所有发布但还没有接单的任务

    List<Deal> selectMyPublishedDeal(String from_id);   //查找我发布的但是还没有人接单的订单

    List<Deal> selectMyUnfinishDeal(String from_id);          //这个是查找我发布的并且有人接单但是还没有结束的订单

    List<Deal> selectOtherUnfinishDeal(String to_id);      //查找我接受的但是还没结束的订单

    List<Deal> selectHistoryDeal(String from_id);          //根据id查找用户的历史订单

    int publishDeal(Deal deal);

    int startDeal(Long id, String openId);

    int deleteDeal(Long id);

    int insertIntoOld(Long id);
}
