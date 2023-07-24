package com.example.quanlyrapphim.models;


import java.util.Date;

public class Film {
    private String id;
    private String name;
    private String type;
    private String cast;
    private String country;
    private String content;
    private Date releaseDate;
    private Date createdAt;
    private String image;

    public Film() {
        // for firebase
    }

    public Film(String name, String type, String cast, String country, String content, Date releaseDate, Date createdAt, String image) {
        this.name = name;
        this.type = type;
        this.cast = cast;
        this.country = country;
        this.content = content;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.image = image;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
