package com.harshit.newsapp;

public class NewsListMode {

    String image;
    String author;
    String title;
    String description;
    String cont;

    public NewsListMode(String image, String author, String title, String description, String cont) {
        this.image = image;
        this.author = author;
        this.title = title;
        this.description = description;
        this.cont = cont;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
