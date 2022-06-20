package com.example.pki_projekat.model;

public class Recommendation {
    public String from, to;
    public int bookId;

    public Recommendation() {}
    public Recommendation(String from, String to, int bookId) {
        this.from = from;
        this.to = to;
        this.bookId = bookId;
    }
}
