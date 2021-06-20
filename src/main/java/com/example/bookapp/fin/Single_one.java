package com.example.bookapp.fin;

public class Single_one {

    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0&maxResults=10";
    //    String JsonURL = "https://www.googleapis.com/books/v1/volumes?q=java?projection=full&&key=AIzaSyCmCQHzatVef3haHHXk6RWwYroWt-flbU0";


    private static final Single_one ourInstance = new Single_one();
    public static Single_one getInstance() {
        return ourInstance;
    }
    private Single_one() { }


    public String getJsonURL() {
        return JsonURL;
    }

    public void setJsonURL(String jsonURL) {
        JsonURL = jsonURL;
    }
}
