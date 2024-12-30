package com.gatepass.controllers;

public class PersonData {
    int id, houseNo;
    String name, subject;
    public PersonData(int id, String name, String subject,int houseNo){
        this.id=id;
        this.subject=subject;
        this.houseNo=houseNo;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
