package com.example.bookapp.fin;

import android.os.Bundle;

import com.example.bookapp.books_class.The_Book;

import java.util.ArrayList;

public class Single_one {

    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0&maxResults=10";
    //    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java?projection=full&&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0";

    Bundle savedInstanceState;

    boolean first_time = true;

    boolean in_search_book = false;

    String now_drow = "home";

    The_Book now_in_overview ;

    String  quote_search_now = "";

    ArrayList<The_Book> title1 = new ArrayList<>();
    ArrayList<The_Book> title2 = new ArrayList<>();
    ArrayList<The_Book> title3 = new ArrayList<>();
    ArrayList<The_Book> title4 = new ArrayList<>();
    ArrayList<The_Book> title5 = new ArrayList<>();

    ArrayList<String> final_titles = new ArrayList<>();



    private static final Single_one ourInstance = new Single_one();
    public static Single_one getInstance() {
        return ourInstance;
    }
    private Single_one() { }

    public String getQuote_search_now() {
        return quote_search_now;
    }

    public void setQuote_search_now(String quote_search_now) {
        this.quote_search_now = quote_search_now;
    }

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



    // save the automatic book in single ones


    public ArrayList<The_Book> getTitle1() {
        return title1;
    }

    public void setTitle1(ArrayList<The_Book> title1) {
        this.title1 = title1;
    }

    public ArrayList<The_Book> getTitle2() {
        return title2;
    }

    public void setTitle2(ArrayList<The_Book> title2) {
        this.title2 = title2;
    }

    public ArrayList<The_Book> getTitle3() {
        return title3;
    }

    public void setTitle3(ArrayList<The_Book> title3) {
        this.title3 = title3;
    }

    public ArrayList<The_Book> getTitle4() {
        return title4;
    }

    public void setTitle4(ArrayList<The_Book> title4) {
        this.title4 = title4;
    }

    public ArrayList<The_Book> getTitle5() {
        return title5;
    }

    public void setTitle5(ArrayList<The_Book> title5) {
        this.title5 = title5;
    }

    public ArrayList<String> getFinal_titles() {
        return final_titles;
    }

    public void setFinal_titles(ArrayList<String> final_titles) {
        this.final_titles = final_titles;
    }

    // first time come to the app :


    public boolean isFirst_time() {
        return first_time;
    }

    public void setFirst_time(boolean first_time) {
        this.first_time = first_time;
    }

    public boolean isIn_search_book() {
        return in_search_book;
    }

    public void setIn_search_book(boolean in_search_book) {
        this.in_search_book = in_search_book;
    }
}
