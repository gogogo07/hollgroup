package com.holl.wechat.util;

import java.util.ArrayList;
import java.util.List;

import com.holl.wechat.model.Deal;

public class HistoryDealData {
    public String time;
    public List<HistoryDealDataItem> things;

    public HistoryDealData(String time) {
        this.time = time;
        this.things = new ArrayList<>();
    }

    public void addItem(int id, Deal deal) {
        things.add(new HistoryDealDataItem(id, deal));
    }
}

class HistoryDealDataItem{
    public int id;
    public Long orderId;
    public String thing;
    public boolean istake;
    
    public HistoryDealDataItem(int id, Deal deal) {
        this.id = id;
        this.orderId = deal.getOrderId();
        this.thing = deal.getOrder().getTitle();
        this.istake = true;
    }
}