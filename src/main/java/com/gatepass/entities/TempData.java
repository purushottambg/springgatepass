package com.gatepass.entities;

public class TempData {
    private String name;
    private String address;
    private String phoneNumber;
    private Integer age;

    public TempData(String name, String address, String phoneNumber, Integer age) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public TempData(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
