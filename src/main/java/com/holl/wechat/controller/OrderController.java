package com.holl.wechat.controller;

import com.holl.wechat.Global;
import com.holl.wechat.model.Deal;
import com.holl.wechat.model.Order;
import com.holl.wechat.service.DealService;
import com.holl.wechat.service.OrderService;
import com.holl.wechat.util.HistoryDealData;
import com.holl.wechat.util.DealDetail;
import com.holl.wechat.util.PublishDealData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DealService dealService;

    @RequestMapping("/submit")
    public Map<String, Object> submit(String openid, String title, String price, String type, String location,
            String detail, String phone, String picture) {
        Global.lock.lock();
        Order order = new Order();
        order.setId(orderService.getMaxId() + 1);
        Float money = Float.valueOf(price);
        order.setTitle(title);
        order.setMoney(money);
        order.setType(type);
        order.setLocation(location);
        order.setDetail(detail);
        order.setPhone(phone);
        order.setPicture(picture);
        order.setPublishTime(new Timestamp(System.currentTimeMillis()).toString().substring(0, 19));

        Deal deal = new Deal();
        deal.setOrderId(order.getId());
        deal.setFromId(openid);

        Map<String, Object> map = new HashMap<>();
        if (orderService.publishOrder(order) == 1) {
            if (dealService.publishDeal(deal) == 1) {
                map.put("mes", "提交成功");
            }
        } else {
            map.put("msg", "提交失败");
        }
        Global.lock.unlock();
        return map;
    }

    // 查找所有发布了的但是没有人接单的订单
    @RequestMapping("/freshByType")
    public Map<String, List<PublishDealData>> fresh(int type) {
        Map<String, List<PublishDealData>> orders = new HashMap<>();
        int i = 0;
        String myType = String.valueOf(type + 1);
        switch (myType) {
            case "1":
                List<PublishDealData> sendOrder = new ArrayList<>();
                for (Deal deal : dealService.selectPublishedDealByType(myType)) {
                    sendOrder.add(new PublishDealData(i++, deal));
                }
                orders.put("sendOrder", sendOrder);
                break;
            case "2":
                List<PublishDealData> questionOrder = new ArrayList<>();
                for (Deal deal : dealService.selectPublishedDealByType(myType)) {
                    questionOrder.add(new PublishDealData(i++, deal));
                }
                orders.put("questionOrder", questionOrder);
                break;
            case "3":
                List<PublishDealData> marketOrder = new ArrayList<>();
                for (Deal deal : dealService.selectPublishedDealByType(myType)) {
                    marketOrder.add(new PublishDealData(i++, deal));
                }
                orders.put("marketOrder", marketOrder);
                break;
            case "4":
                List<PublishDealData> otherOrder = new ArrayList<>();
                for (Deal deal : dealService.selectPublishedDealByType(myType)) {
                    otherOrder.add(new PublishDealData(i++, deal));
                }
                orders.put("otherOrder", otherOrder);
                break;
            default:
                break;
        }
        return orders;
    }

    //通过订单id查找订单的详细信息
    @RequestMapping("/getDealById")
    public DealDetail selectDealById(String orderId) {
        Deal deal = dealService.selectDealById(orderId);
        return new DealDetail(deal);
    }

    // 查找用户id的用户发布的但是还未完成的所有订单
    @RequestMapping("/getMyPublishedOrder")
    public List<Deal> getMyPublishedOrder(String fromId) {
        return dealService.selectMyPublishedDeal(fromId);
    }    

    // 查找用户为id的用户已经接受的但是还没完成的订单
    @RequestMapping("/getMyAcceptDeal")
    public List<Deal> getMyAcceptDeal(String toId) {
        return dealService.selectMyAcceptDeal(toId);
    }

    //查找用户发布的和接受的未完成的订单
    @RequestMapping("/getOrderInfo")
    public Map<String, List<DealDetail>> getOrderInfo(String id) {
        Map<String, List<DealDetail>> orders = new HashMap<>();
        List<DealDetail> publishOrder = new ArrayList<>();
        List<DealDetail> acceptOrder = new ArrayList<>();
        for (Deal deal: getMyPublishedOrder(id)) {
            publishOrder.add(new DealDetail(deal));
        }
        for (Deal deal: getMyAcceptDeal(id)) {
            acceptOrder.add(new DealDetail(deal));
        }
        orders.put("publishOrder", publishOrder);
        orders.put("acceptOrder", acceptOrder);
        return orders;
    }

    // 接单
    @RequestMapping("/start")
    public Map<String, Object> acceptOrder(String orderId, String openId) {
        Global.lock.lock();
        Map<String, Object> map = new HashMap<>();
        Long myOrderId = Long.parseLong(orderId);
        if (orderService.startOrder(myOrderId) != 0) {
            if (dealService.startDeal(myOrderId, openId) != 0) {
                map.put("msg", "接单成功");
            } else {
                map.put("msg", "订单添加成功，交易添加失败");
            }
        } else {
            map.put("msg", "订单添加失败");
        }
        Global.lock.unlock();
        return map;
    }

    @RequestMapping("/finish")
    public Map<String, Object> finishOrder(String orderId) {
        Global.lock.lock();
        Map<String, Object> map = new HashMap<>();
        Long myOrderId = Long.parseLong(orderId);
        if (orderService.insertIntoOld(myOrderId) == 1) {
            if (dealService.finishDeal(orderId) == 1) {
                if (orderService.finishOrder(myOrderId) == 1) {
                    map.put("msg", "交易完成");
                } else {
                    map.put("msg", "订单删除失败");
                }
            } else {
                map.put("msg", "订单转移完成，交易转移失败");
            }
        } else {
            map.put("msg", "订单转移失败");
        }
        Global.lock.unlock();
        return map;
    }

    @RequestMapping("getHistoryOrder")
    public List<HistoryDealData> getHistoryOrder(String userId) {
        List<Deal> historyDeals = dealService.selectHistoryDeal(userId);
        if (historyDeals.size() == 0) {
            return null;
        }
        List<HistoryDealData> historyDealDatas = new ArrayList<>();
        int i = 0;
        String time = historyDeals.get(0).getOrder().getFinishTime().substring(5, 10);
        HistoryDealData historyDealData = new HistoryDealData(time);
        for (Deal deal : historyDeals) {
            String tmpTime = deal.getOrder().getFinishTime().substring(5, 10);
            if (!tmpTime.equals(historyDealData.time)) {
                historyDealDatas.add(historyDealData);
                historyDealData = new HistoryDealData(tmpTime);
                i = 0;
            }
            historyDealData.addItem(i++, deal);
        }
        historyDealDatas.add(historyDealData);
        return historyDealDatas;
    }

    @RequestMapping("/getHistoryDealById")
    public DealDetail getHistoryDealById(String orderId) {
        Deal deal = dealService.selectHistoryDealById(orderId);
        return new DealDetail(deal);
    }
}
