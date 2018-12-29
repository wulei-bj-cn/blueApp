package com.blueHouse.pojo.orders;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class OrderItem {

    private String order_id;
    private String item_id;
    private String item_class;
    private Timestamp start_time;
    private Timestamp end_time;
    private String status;

    public String getOrder_id() { return this.order_id; }

    public void setOrder_id(String order_id) { this.order_id = order_id; }

    public String getItem_id() { return this.item_id; }

    public void setItem_id(String item_id) { this.item_id = item_id; }

    public String getItem_class() { return this.item_class; }

    public void setItem_class(String item_class) { this.item_class = item_class; }

    public Timestamp getStart_time() { return this.start_time; }

    public void setStart_time(Timestamp start_time) { this.start_time = start_time; }

    public Timestamp getEnd_time() { return this.end_time; }

    public void setEnd_time(Timestamp end_time) { this.end_time = end_time; }

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }
}

