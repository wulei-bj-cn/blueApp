package com.blueHouse.pojo.orders;

/**
 * Created by wulei on 23/07/2018.
 */
public class Material {
    private String user_id;
    private String order_id;
    private String id;
    private String class_name;
    private String category;
    private String brand;
    private String name;
    private double price;
    private String url;

    public String getUser_id() { return this.user_id; }
    public void setUser_id() { this.user_id = user_id; }

    public String getOrder_id() { return this.order_id; }
    public void setOrder_id(String order_id) { this.order_id = order_id; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getClass_name() { return this.class_name; }
    public void setClass_name(String class_name) { this.class_name = class_name; }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return this.brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return this.price; }
    public void setPrice(double price) { this.price = price; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

}

