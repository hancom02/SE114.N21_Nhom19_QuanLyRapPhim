package com.example.quanlyrapphim.models;

public class DropdownTextField {
    private String nameDropdown;
    private String contentText;

    public DropdownTextField(String nameDropdown, String contentText) {
        this.nameDropdown = nameDropdown;
        this.contentText = contentText;
    }

    public String getNameDropdown() {
        return nameDropdown;
    }

    public void setNameDropdown(String nameDropdown) {
        this.nameDropdown = nameDropdown;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
