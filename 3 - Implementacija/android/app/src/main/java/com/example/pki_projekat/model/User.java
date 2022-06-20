package com.example.pki_projekat.model;

import java.util.ArrayList;

public class User {
    public String name, surname, address, username, password, phoneNumber;
    public int id;
    public UserType userType;

    public enum UserType { BUYER, SELLER };

    public User() {}

    public User(String name, String surname, String address, String username, String password, String phoneNumber, int id, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.userType = userType;
    }
}
