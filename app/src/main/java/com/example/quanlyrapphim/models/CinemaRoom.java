package com.example.quanlyrapphim.models;

import java.util.ArrayList;

public class CinemaRoom {
    private String id;
    private String name;
    private String type;
    private double seatsColumn;
    private double seatsRow;
    private ArrayList<String> equipmentIds;
//    private Equipment projector;
//    private Equipment screen;
//    private Equipment lamp;
//    private Equipment speaker;

    public CinemaRoom() {}

    public CinemaRoom(String name, String type, double column_seats, double row_seats) {
        this.name = name;
        this.type = type;
        this.seatsColumn = column_seats;
        this.seatsRow = row_seats;
    }
    public CinemaRoom(String name, String type, double column_seats, double row_seats, ArrayList<String> equipmentIds) {
        this.name = name;
        this.type = type;
        this.seatsRow = column_seats;
        this.seatsColumn = row_seats;
        this.equipmentIds = equipmentIds;

//        this.projector = projector;
//        this.screen = screen;
//        this.lamp = lamp;
//        this.speaker = speaker;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Equipment getProjector() {
//        return projector;
//    }
//
//    public void setProjector(Equipment projector) {
//        this.projector = projector;
//    }
//
//    public Equipment getScreen() {
//        return screen;
//    }
//
//    public void setScreen(Equipment screen) {
//        this.screen = screen;
//    }
//
//    public Equipment getLamp() {
//        return lamp;
//    }
//
//    public void setLamp(Equipment lamp) {
//        this.lamp = lamp;
//    }
//
//    public Equipment getSpeaker() {
//        return speaker;
//    }
//
//    public void setSpeaker(Equipment speaker) {
//        this.speaker = speaker;
//    }

    public double getSeatsColumn() {
        return seatsColumn;
    }

    public void setSeatsColumn(int seatsColumn) {
        this.seatsColumn = seatsColumn;
    }

    public double getSeatsRow() {
        return seatsRow;
    }

    public void setSeatsRow(int seatsRow) {
        this.seatsRow = seatsRow;
    }

    public ArrayList<String> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(ArrayList<String> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
}
