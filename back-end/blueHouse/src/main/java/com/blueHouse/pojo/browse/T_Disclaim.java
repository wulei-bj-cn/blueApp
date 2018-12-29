package com.blueHouse.pojo.browse;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class T_Disclaim {
    private String id;
    private String name;
    private String url;
    private Timestamp ts;
    private String crew;

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public Timestamp getTs() { return this.ts; }
    public void setTs(Timestamp ts) { this.ts = ts; }

    public String getCrew() { return this.crew; }
    public void setCrew(String crew) { this.crew = crew; }

}

