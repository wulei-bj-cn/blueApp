package com.blueHouse.pojo;

import java.sql.Timestamp;

/**
 * Created by wulei on 23/07/2018.
 */
public class Article {
    private String id;
    private String title;
    private String digest;
    private String url;
    private String content;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getDigest() { return this.digest; }

    public void setDigest(String digest) { this.digest = digest; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public String getContent() { return this.content; }

    public void setContent(String content) { this.content = content; }
}
