package com.abdo.montgat.model;

public class FilterSearchModel {
     private  String category, size, gender;

    public FilterSearchModel(String category) {
    }

    public FilterSearchModel(String category, String size, String gender) {
        this.category = category;
        this.size = size;
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
