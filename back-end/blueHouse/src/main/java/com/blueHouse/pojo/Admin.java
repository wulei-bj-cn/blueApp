package com.blueHouse.pojo;

import java.sql.Timestamp;

/**
 * Created by lihan on 2018/9/1.
 */
public class Admin {
    private String id;
    private String login;
    private String name;
    private String password;
    private String role;
    private String status;
    private Timestamp last_log_on;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLast_log_on() {
        return last_log_on;
    }

    public void setLast_log_on(Timestamp last_log_on) {
        this.last_log_on = last_log_on;
    }
}
