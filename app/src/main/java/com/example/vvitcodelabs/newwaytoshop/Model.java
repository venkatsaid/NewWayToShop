package com.example.vvitcodelabs.newwaytoshop;

public class Model {
    String name,email,mobile,gender;

    public Model() {
    }

    public Model(String email , String name, String mobile, String gender) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
