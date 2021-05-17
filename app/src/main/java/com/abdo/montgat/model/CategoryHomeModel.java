package com.abdo.montgat.model;

public class CategoryHomeModel {
    private String name;
    private int image;
    private int code;


    public CategoryHomeModel(String name, int image, int code) {
        this.name = name;
        this.image = image;
        this.code = code;
    }

    public CategoryHomeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
