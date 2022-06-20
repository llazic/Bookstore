package com.example.pki_projekat.model;

import java.util.ArrayList;

public class Book {
    public int id, pageCount;
    public String name, author, releaseYear, img, description;
    public boolean promo;
    public ArrayList<Review> reviews;

    public Book() {}

    public Book(int id, int pageCount, String name, String author, String releaseYear, String img, String description, boolean promo, ArrayList<Review> reviews) {
        this.id = id;
        this.pageCount = pageCount;
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
        this.img = img;
        this.description = description;
        this.promo = promo;
        this.reviews = reviews;
    }
}
