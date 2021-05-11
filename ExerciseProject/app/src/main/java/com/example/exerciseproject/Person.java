package com.example.exerciseproject;

public class Person {
    private String Name;
    private String Avatar;
    private String Phone;
    private String ClassRoom;
    private float Point;
    private String Id;

    public Person(String name, String avatar) {
        this.Name = name;
        this.Avatar = avatar;
    }

    public Person(String name, String avatar, String phone) {
        this.Name = name;
        this.Avatar = avatar;
        this.Phone = phone;
    }

    public Person(String id, String name, String avatar, String classRoom, float point) {
        this.Id = id;
        this.Name = name;
        this.Avatar = avatar;
        this.ClassRoom = classRoom;
        this.Point = point;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        this.Avatar = avatar;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getClassRoom() {
        return ClassRoom;
    }

    public void setClass(String classRoom) {
        this.ClassRoom = classRoom;
    }

    public float getPoint() {
        return Point;
    }

    public void setPoint(float point) {
        this.Point = point;
    }
}

