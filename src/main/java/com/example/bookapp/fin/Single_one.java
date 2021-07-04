package com.example.bookapp.fin;

import android.os.Bundle;

import com.example.bookapp.books_class.The_Book;

public class Single_one {

    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0&maxResults=10";
    //    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java?projection=full&&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0";

    Bundle savedInstanceState;

    String now_drow = "home";

    The_Book now_in_overview ;


    private static final Single_one ourInstance = new Single_one();
    public static Single_one getInstance() {
        return ourInstance;
    }
    private Single_one() { }

    public The_Book getNow_in_overview() {
        return now_in_overview;
    }

    public void setNow_in_overview(The_Book now_in_overview) {
        this.now_in_overview = now_in_overview;
    }

    public String getNow_drow() {
        return now_drow;
    }

    public void setNow_drow(String now_drow) {
        this.now_drow = now_drow;
    }

    public String getJsonURL() {
        return JsonURL;
    }

    public void setJsonURL(String jsonURL) {
        JsonURL = jsonURL;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public void setSavedInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }
}
