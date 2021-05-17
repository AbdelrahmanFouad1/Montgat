package com.abdo.montgat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements Serializable {

    private String key_id, favStatus,title, description, price, deprecatedPrice, intoCart;
    private int Image;
    private List<String> size = new ArrayList<>();
    private List<Integer> color = new ArrayList<>();

    public ProductModel() {
    }


    public ProductModel(String key_id, String favStatus, String intoCart, String title, String description, String price, String deprecatedPrice, int image, List<String> size, List<Integer> color) {
        this.key_id = key_id;
        this.favStatus = favStatus;
        this.intoCart = intoCart;
        this.title = title;
        this.description = description;
        this.price = price;
        this.deprecatedPrice = deprecatedPrice;
        Image = image;
        this.size = size;
        this.color = color;
    }


    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getIntoCart() {
        return intoCart;
    }

    public void setIntoCart(String intoCart) {
        this.intoCart = intoCart;
    }

    public String getLabel() {
        return title;
    }

    public void setLabel(String label) {
        this.title = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeprecatedPrice() {
        return deprecatedPrice;
    }

    public void setDeprecatedPrice(String deprecatedPrice) {
        this.deprecatedPrice = deprecatedPrice;
    }

    public List<String> getSize() {
        return size;
    }

    public List<String> getSizePosition(int potion) {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<Integer> getColor() {
        return color;
    }

    public void setColor(List<Integer> color) {
        this.color = color;
    }
}
