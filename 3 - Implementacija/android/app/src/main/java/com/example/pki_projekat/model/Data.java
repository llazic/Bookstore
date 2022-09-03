package com.example.pki_projekat.model;

import java.util.ArrayList;

public class Data {

    public ArrayList<User> users;

    public ArrayList<Book> books;

    public ArrayList<Recommendation> recommendations;

    public User currentUser;

    public int currentBookId;

    public Data() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.recommendations = new ArrayList<>();
    }
}
