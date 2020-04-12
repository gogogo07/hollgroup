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
    public List<Deal> selectAllMyDeal() {
        List<Deal> deals = dealMapper.selectFromDeal();
        deals.addAll(dealMapper.selectToDeal());
        return deals;
    }

    @Override
    public int publishDeal(Deal deal) {
        return dealMapper.publishDeal(deal);
    }

    @Override
    public int startDeal(String orderId, String type, String openId) {
        if (type.equals("111")) {
            return dealMapper.startFromDeal(orderId, openId);
        } else {
            return dealMapper.startToDeal(orderId, openId);
        }
    }

    @Override
    public int finishDeal(String id) {
        int insert = dealMapper.insertIntoOld(id);
        int delete = dealMapper.deleteDeal(id);
        if (insert == 1 && delete == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
