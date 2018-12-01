package com.example.vvitcodelabs.newwaytoshop;

public class ItemsPojo {
    private String name,description,image;
    long price;

    public ItemsPojo() {

    }

    public ItemsPojo(String name, String description, String image, long price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public long getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
