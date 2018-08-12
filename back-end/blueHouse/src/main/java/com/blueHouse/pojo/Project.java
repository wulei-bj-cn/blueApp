package com.blueHouse.pojo;

/**
 * Created by wulei on 23/07/2018.
 */
public class Project {
    private String user_id;
    private String order_id;
    private String id;
    private String name;
    private String category;
    private int enabled;
    private String status;

    public String getUser_id() { return this.user_id; }
    public void setUser_id() { this.user_id = user_id; }

    public String getOrder_id() { return this.order_id; }
    public void setOrder_id(String order_id) { this.order_id = order_id; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }

    public int getEnabled() { return this.enabled; }
    public void setEnabled(int enabled) { this.enabled = enabled; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
}

