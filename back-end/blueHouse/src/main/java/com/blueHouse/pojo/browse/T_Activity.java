package com.blueHouse.pojo.browse;

import java.sql.Timestamp;

/**
 * Created by lihan on 2018/8/11.
 */
public class T_Activity {
    private String id;
    private String des;
    private String url;
    private String act_type;
    private String target_url;
    private String active_id;
    private Timestamp start_time;
    private Timestamp end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getAct_type() { return act_type; }

    public void setAct_type(String act_type) { this.act_type = act_type; }

    public String getTarget_url() { return this.target_url; }

    public void setTarget_url(String target_url) { this.target_url = target_url; }

    public String getActive_id() { return this.active_id; }

    public void setActive_id(String active_id) { this.active_id = active_id; }
}
