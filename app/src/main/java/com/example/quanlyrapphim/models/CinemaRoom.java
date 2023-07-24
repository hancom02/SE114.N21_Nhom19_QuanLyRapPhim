package com.example.quanlyrapphim.models;

public class CinemaRoom {
    private String name;
    private String type;
    private int column_seats;
    private int row_seats;

    public CinemaRoom(String name, String type, int column_seats, int row_seats) {
        this.name = name;
        this.type = type;
        this.column_seats = column_seats;
        this.row_seats = row_seats;
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

    public int getColumn_seats() {
        return column_seats;
    }

    public void setColumn_seats(int column_seats) {
        this.column_seats = column_seats;
    }

    public int getRow_seats() {
        return row_seats;
    }

    public void setRow_seats(int row_seats) {
        this.row_seats = row_seats;
    }
}
