package com.blueHouse.pojo;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class Access {
    private Integer id;
    private Timestamp start_time;
    private Timestamp end_time;
    private Integer sojourn;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStart_time() { return this.start_time; }

    public void setStart_time(Timestamp ts) { this.start_time = ts; }

    public Timestamp getEnd_time() { return this.end_time; }

    public void setEnd_time(Timestamp ts) { this.end_time = ts; }

    public int getSojourn() { return this.sojourn; }

    public void setSojourn(int sojourn) { this.sojourn = sojourn; }
}

