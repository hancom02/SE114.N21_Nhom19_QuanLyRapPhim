package com.example.quanlyrapphim.models;

import com.google.firebase.firestore.FieldValue;

import java.util.Date;

public class CreateFilm {
    private String name;
    private String type;
    private String country;
    private String cast;
    private String content;
    private Date releaseDate;
    private String image;
    private FieldValue createdAt;

    public CreateFilm(String name, String type, String country, String cast, String content, Date releaseDate, FieldValue createdAt) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.cast = cast;
        this.content = content;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getCast() {
        return cast;
    }

    public String getContent() {
        return content;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FieldValue getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(FieldValue createdAt) {
        this.createdAt = createdAt;
    }
}