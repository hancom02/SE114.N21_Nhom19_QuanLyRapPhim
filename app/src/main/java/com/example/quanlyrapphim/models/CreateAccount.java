package com.example.quanlyrapphim.models;

import com.google.firebase.firestore.FieldValue;

import java.util.Date;

public class CreateAccount {
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private String gender;
    private String avatar;
    private String role;
    private FieldValue createdAt;


    public CreateAccount(String email, String password, String name, Date birthday, String gender, String role, FieldValue createdAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.role = role;
        this.createdAt = createdAt;
        this.avatar = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public FieldValue getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(FieldValue createdAt) {
        this.createdAt = createdAt;
    }
}
