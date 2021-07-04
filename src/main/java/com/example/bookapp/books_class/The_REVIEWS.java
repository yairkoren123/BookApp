package com.example.bookapp.books_class;

public class The_REVIEWS {

    String description = "";
    String stars = "";
    String name = "";
    String likes = "";
    String date = "";

    public The_REVIEWS() {
    }


    public The_REVIEWS(String description, String stars, String name, String likes, String date) {
        this.description = description;
        this.stars = stars;
        this.name = name;
        this.likes = likes;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
