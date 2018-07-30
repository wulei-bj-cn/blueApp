package com.blueHouse.pojo;

/**
 * Created by wulei on 23/07/2018.
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer age;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
