package com.blueHouse.pojo.browse;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class T_Solution {
    private String id;
    private String name;
    private String cover;
    private String url;
    private Timestamp ts;
    private String designer;
    private String category;

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getCover() { return this.cover; }
    public void setCover(String cover) { this.cover = cover; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public Timestamp getTs() { return this.ts; }
    public void setTs(Timestamp ts) { this.ts = ts; }

    public String getDesigner() { return this.designer; }
    public void setDesigner(String designer) { this.designer = designer; }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }

}

