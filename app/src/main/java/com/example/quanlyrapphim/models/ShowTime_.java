package com.example.quanlyrapphim.models;

import java.util.Date;
import java.util.List;

public class ShowTime_ {
    public String id;
    public Date day;
    public String filmId;
    public int price;
    public List<Integer> seats;
    public int seatsColumn;
    public String threaterId;
    public String timeSlotId;
    public ShowTime_(){
        // for firebase
    }

}