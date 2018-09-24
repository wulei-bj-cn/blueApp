package com.blueHouse.pojo.services;


import org.aspectj.weaver.ast.Or;

/**
 * Created by wulei on 23/07/2018.
 */
public class OrderData {
    private String id;
    private String order_num;
    private String image_url;
    private String title;
    private String status;

    public OrderData(
            String id,
            String order_num,
            String image_url,
            String title,
            String status
    ) {
        this.id = id;
        this.order_num = order_num;
        this.image_url = image_url;
        this.title = title;
        this.status = status;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getOrder_num() { return this.order_num; }
    public void setOrder_num(String order_num) { this.order_num = order_num; }

    public String getImage_url() { return this.image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status;}
}

