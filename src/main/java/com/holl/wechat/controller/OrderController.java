package com.holl.wechat.controller;

import com.holl.wechat.model.Order;
import com.holl.wechat.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    private List<Order> curOrders = new LinkedList<>();

    @RequestMapping("/order/submit")
    public Map submit(String title, String price, String localtion, String detail) {
        Order order = new Order();
        Float money = Float.valueOf(price);
        order.setTitle(title);
        order.setMoney(money);
        order.setLocation(localtion);
        order.setDetail(detail);

        order.setPublishTime(new Timestamp(System.currentTimeMillis()));

        curOrders.add(order);
        //orderService.insertByOrder(order);
        Map map = new HashMap();
        map.put("mes", "提交成功");
        return map;
    }

    @RequestMapping("/order/fresh")
    public List fresh() {
        return curOrders;
    }

    @RequestMapping("/order/acceptorder")
    public Map acceptOrder(String id) {
        boolean isExsit = false;
        Order order;
        Map map = new HashMap();
        Iterator<Order> iterator = curOrders.iterator();
        while (iterator.hasNext()) {
            order = iterator.next();
            if (order.getId().equals(id)) {
                order.setStartTime(new Timestamp(System.currentTimeMillis()));
                isExsit = true;
                break;
            }
        }
        if (isExsit) {
            map.put("msg", "接单成功");
        } else {
            map.put("msg", "接单失败");
        }
        return map;
    }

    @RequestMapping("/order/finish")
    public Map finishOrder(String id) {
        boolean isExist = false;
        Order order;
        Map map = new HashMap();
        Iterator<Order> iterator = curOrders.iterator();
        while (iterator.hasNext()) {
            order = iterator.next();
            if (order.getId().equals(id)) {
                order.setFinishTime(new Timestamp(System.currentTimeMillis()));
                orderService.insertByOrder(order);
                iterator.remove();
                isExist = true;
                break;
            }
        }
        if (isExist) {
            map.put("msg", "订单完成");
        } else {
            map.put("msg", "接单失败");
        }
        return map;
    }
}
