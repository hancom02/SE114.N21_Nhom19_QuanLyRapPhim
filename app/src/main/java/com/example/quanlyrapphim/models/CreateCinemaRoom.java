package com.example.quanlyrapphim.models;

import java.util.ArrayList;

public class CreateCinemaRoom {
    private String name;
    private String type;
    private double seatsColumn;
    private double seatsRow;
    private ArrayList<String> equipmentIds;

    public CreateCinemaRoom(String name, double seatsRow, double seatsColumn){
        this.name = name;
        this.seatsRow = seatsRow;
        this.seatsColumn = seatsColumn;
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

    public double getSeatsColumn() {
        return seatsColumn;
    }

    public void setSeatsColumn(double seatsColumn) {
        this.seatsColumn = seatsColumn;
    }

    public double getSeatsRow() {
        return seatsRow;
    }

    public void setSeatsRow(double seatsRow) {
        this.seatsRow = seatsRow;
    }

    public ArrayList<String> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(ArrayList<String> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
}
