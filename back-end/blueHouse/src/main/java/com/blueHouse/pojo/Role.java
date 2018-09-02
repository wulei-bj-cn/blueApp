package com.blueHouse.pojo;

/**
 * Created by lihan on 2018/9/1.
 */
public class Role {
    private String id;
    private String description;
    private String permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
