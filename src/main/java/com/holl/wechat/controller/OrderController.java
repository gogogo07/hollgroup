package com.holl.wechat.controller;

import com.holl.wechat.model.Deal;
import com.holl.wechat.model.Order;
import com.holl.wechat.service.DealService;
import com.holl.wechat.service.OrderService;
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
    public Map submit(String openid, String title, String price, String type, String location, String detail) {
        Order order = new Order();
        Float money = Float.valueOf(price);
        order.setTitle(title);
        order.setMoney(money);
        order.setType(type);
        order.setLocation(location);
        order.setDetail(detail);
        order.setPublishTime(new Timestamp(System.currentTimeMillis()).toString().substring(0, 19));

        Deal deal = new Deal();
        deal.setOrderId(order.getId());
        if (type.equals("111")) {
            deal.setFromId(openid);
        } else {
            deal.setToId(openid);
        }

        Map map = new HashMap();
        if (orderService.publishOrder(order) == 1){
            if (dealService.publishDeal(deal) == 1) {
                map.put("mes", "提交成功");
            }
        } else {
            map.put("msg", "提交失败");
        }
        return map;
    }

    @RequestMapping("/fresh")
    public List fresh() {
        return dealService.selectAllMyDeal();
    }
    //先查找订单，通过订单的种类，来确定发过来的openid是from还是to的
    @RequestMapping("/start")
    public Map acceptOrder(String orderId, String type, String openId) {
        Map map = new HashMap();
        if (orderService.startOrder(orderId) != 0) {
            if (dealService.startDeal(orderId, type, openId) != 0) {
                map.put("msg", "接单成功");
            } else {
                map.put("msg", "订单添加成功，交易添加失败");
            }
        } else {
            map.put("msg", "订单添加失败");
        }
        return map;
    }

    @RequestMapping("/finish")
    public Map finishOrder(String id) {
        Map map = new HashMap();
        if (orderService.insertIntoOld(id) == 1) {
            if (dealService.finishDeal(id) == 1) {
                if (orderService.finishOrder(id) == 1) {
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
        return map;
    }
}
