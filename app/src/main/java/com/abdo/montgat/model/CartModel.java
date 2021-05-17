package com.abdo.montgat.model;

public class CartModel {
    private String key_id, item_price, item_new_price, item_title, size, quantity;
    private int item_image;

    public CartModel() {
    }

    public CartModel(String key_id, String item_price, String item_new_price, String item_title, String size, String quantity, int item_image) {
        this.key_id = key_id;
        this.item_price = item_price;
        this.item_new_price = item_new_price;
        this.item_title = item_title;
        this.size = size;
        this.quantity = quantity;
        this.item_image = item_image;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_new_price() {
        return item_new_price;
    }

    public void setItem_new_price(String item_new_price) {
        this.item_new_price = item_new_price;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }


}
