package com.blueHouse.pojo.browse;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class T_Design {
    private String id;
    private String name;
    private String url;
    private Timestamp ts;
    private String designer;
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public Timestamp getTs() { return this.ts; }
    public void setTs(Timestamp ts) { this.ts = ts; }

    public String getDesigner() { return this.designer; }
    public void setDesigner(String designer) { this.designer = designer; }

}

