package com.blueHouse.pojo;

/**
 * Created by wulei on 23/07/2018.
 */
public class User {
    private String id;
    private String name;
    private String password;
    private Integer age;
    private String phone;
    private String address;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getPhone() { return this.phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

    public int getAge() { return this.age; }

    public void setAge(int age) { this.age = age; }
}

