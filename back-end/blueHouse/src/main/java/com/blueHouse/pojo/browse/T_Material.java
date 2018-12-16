package com.blueHouse.pojo.browse;

/**
 * Created by wulei on 23/07/2018.
 */
public class T_Material {
    private String id;
    private String class_name;
    private String category;
    private String brand;
    private String name;
    private double prePrice;
    private double newPrice;
    private String url;
    private String details;

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getClass_name() { return this.class_name; }
    public void setClass_name(String class_name) { this.class_name = class_name; }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return this.brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(double prePrice) {
        this.prePrice = prePrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public double getPrice() {
        return newPrice;
    }

    public void setPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }



    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

