package com.example.exerciseproject;

public class Person {
    private  String Name;
    private  String Avatar;
    private  String Phone;

    public Person(String name, String avatar, String phone){
        this.Name = name;
        this.Avatar = avatar;
        this.Phone = phone;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getAvatar(){
        return Avatar;
    }

    public void setAvatar(String avatar){
        this.Avatar = avatar;
    }

    public String getPhone(){
        return Phone;
    }

    public void setPhone(String phone){
        this.Phone = phone;
    }
}

