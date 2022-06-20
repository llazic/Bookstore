package com.example.pki_projekat.model;

public class Review {
    public String username, comment;
    public int rating;

    public Review() {}

    public Review(String username, String comment, int rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }
}
