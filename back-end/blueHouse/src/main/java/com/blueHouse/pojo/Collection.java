package com.blueHouse.pojo;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class Collection {
    private String user_id;
    private String item_id;
    private String item_class;
    private Timestamp add_time;

    public String getUser_id() { return this.user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getItem_id() { return this.item_id; }

    public void setItem_id(String item_id) { this.item_id = item_id; }

    public String getItem_class() { return this.item_class; }

    public void setItem_class(String item_class) { this.item_class = item_class; }

    public Timestamp getAdd_time() { return this.add_time; }

    public void setAdd_time(Timestamp add_time) { this.add_time = add_time; }
}
