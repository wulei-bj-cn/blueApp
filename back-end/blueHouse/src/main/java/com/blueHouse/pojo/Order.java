package com.blueHouse.pojo;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class Order {
    private String user_id;
    private String id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String status;

    public String getUser_id() { return this.user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public Timestamp getStart_time() { return this.start_time; }
    public void setStart_time(Timestamp start_time) { this.start_time = start_time; }

    public Timestamp getEnd_time() { return this.end_time; }
    public void setEnd_time(Timestamp end_time) { this.end_time = end_time; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

 }

