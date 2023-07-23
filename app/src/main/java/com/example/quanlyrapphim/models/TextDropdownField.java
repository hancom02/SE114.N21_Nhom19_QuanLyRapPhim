package com.example.quanlyrapphim.models;

public class TextDropdownField {
    private String nameText;
    private String contentDropdown;

    public TextDropdownField(String nametext, String contentDropdown) {
        this.nameText = nameText;
        this.contentDropdown = contentDropdown;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getContentDropdown() {
        return contentDropdown;
    }

    public void setContentDropdown(String contentDropdown) {
        this.contentDropdown = contentDropdown;
    }
}
