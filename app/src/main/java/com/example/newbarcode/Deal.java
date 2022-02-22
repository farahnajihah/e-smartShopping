package com.example.e_smartshopping;

public class Deal {

    public String id;
    public String location;
    public String name;
    public String picture;
    public String price;

    public Deal(String id, String location, String name, String picture, String price) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.picture = picture;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


