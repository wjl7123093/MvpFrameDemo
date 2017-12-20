package com.fred_w.demo.mvpframedemo.mvp.model.entity;

/**
 * 登录用户 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 */
public class User {

    private String app_id;
    private int id;
    private int group;
    private String role;
    private String name;
    private String mobile;
    private String ticket;

    @Override
    public String toString() {
        return "User{" +
                "app_id='" + app_id + '\'' +
                ", id=" + id +
                ", group=" + group +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
