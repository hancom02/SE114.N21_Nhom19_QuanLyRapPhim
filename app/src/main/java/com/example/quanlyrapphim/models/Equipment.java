package com.example.quanlyrapphim.models;

import com.google.android.gms.common.util.ScopeUtil;

import java.util.Date;

public class Equipment {
    private String id;
    private String name;
    private Date boughtAt;
    private String status;

    public Equipment() {}
    public Equipment(String name, Date boughtAt, String status) {
        this.name = name;
        this.boughtAt = boughtAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(Date boughtAt) {
        this.boughtAt = boughtAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
