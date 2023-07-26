package com.example.quanlyrapphim.models;

public class Seat {
    private int status; // 0: available, 1: not available, 2: choosing

    public Seat(int status) {
        this.status = status;
    }

    public static String getLabel(int index, int numColumn) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rowStr;
        int row = index / numColumn;
        int column = index % numColumn;
        if (row >= 0 && row <= 25) {
            rowStr = LETTERS.substring(row, row + 1).charAt(0) + "";
        } else {
            rowStr = "?";
        }
        return rowStr + (column+1);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
