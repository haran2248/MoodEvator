package com.example.moodevator;

public class Info {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Info(){

    }


    String name;
    String Email;
    String doctor;
    String friend1;
    String friend2;
    String location;
    String phoneNo;
    String help;

    public Info(String name, String email, String doctor, String friend1, String friend2, String location, String phoneNo, String help)
    {
        this.name = name;
        Email = email;
        this.doctor = doctor;
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.location = location;
        this.phoneNo = phoneNo;
        this.help = help;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFriend1() {
        return friend1;
    }

    public void setFriend1(String friend1) {
        this.friend1 = friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
