package com.blueHouse.pojo.browse;

/**
 * Created by wulei on 23/07/2018.
 */
public class T_Project {

    private String id;
    private String name;
    private String url;
    private String category;
    private int enabled;
    private String status;

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }

    public int getEnabled() { return this.enabled; }
    public void setEnabled(int enabled) { this.enabled = enabled; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
}

