package com.blueHouse.pojo.orders;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class Design {
    private String user_id;
    private String order_id;
    private String id;
    private String name;
    private String url;
    private Timestamp ts;
    private String designer;
    private String status;
    private String details;

    public String getUser_id() { return this.user_id; }
    public void setUser_id() { this.user_id = user_id; }

    public String getOrder_id() { return this.order_id; }
    public void setOrder_id(String order_id) { this.order_id = order_id; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public Timestamp getTs() { return this.ts; }
    public void setTs(Timestamp ts) { this.ts = ts; }

    public String getDesigner() { return this.designer; }
    public void setDesigner() { this.designer = designer; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public String getDetails() { return this.details; }
    public void setDetails(String details) { this.details = details; }
}

