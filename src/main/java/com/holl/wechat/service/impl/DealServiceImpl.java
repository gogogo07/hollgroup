package com.holl.wechat.service.impl;

import com.holl.wechat.dao.DealMapper;
import com.holl.wechat.model.Deal;
import com.holl.wechat.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    DealMapper dealMapper;

    @Override
    public List<Deal> selectAll() {
        return dealMapper.selectAll();
    }

    @Override
    public List<Deal> selectPublishedDeal() {
        return dealMapper.selectPublishedDeal();
    }

    @Override
    public List<Deal> selectMyPublishedDeal(String from_id) {
        return dealMapper.selectMyPublishedDeal(from_id);
    }

    @Override
    public List<Deal> selectMyUnfinishDeal(String from_id) {
        return dealMapper.selectMyUnfinishDeal(from_id);
    }

    @Override
    public List<Deal> selectOtherUnfinishDeal(String to_id) {
        return dealMapper.selectOtherUnfinishDeal(to_id);
    }

    @Override
    public List<Deal> selectHistoryDeal(String from_id) {
        return dealMapper.selectHistoryDeal(from_id);
    }

    @Override
    public int publishDeal(Deal deal) {
        return dealMapper.publishDeal(deal);
    }

    @Override
    public int startDeal(Long orderId, String openId) {
        return dealMapper.startDeal(orderId, openId);
    }

    @Override
    public int finishDeal(String id) {
        Long my_id = Long.parseLong(id);
        int insert = dealMapper.insertIntoOld(my_id);
        int delete = dealMapper.deleteDeal(my_id);
        if (insert == 1 && delete == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
