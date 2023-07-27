package com.example.quanlyrapphim.models;

import java.util.Date;

public class ShowTimeUI {
    public String filmName;
    public Date date;
    public String time;
    public String theaterName;
    public int price;
    public boolean isSelected;

    public ShowTimeUI(String filmName, Date date, String time, String theaterName, int price) {
        this.filmName = filmName;
        isSelected = false;
        this.date = date;
        this.time = time;
        this.theaterName = theaterName;
        this.price = price;
    }
}
