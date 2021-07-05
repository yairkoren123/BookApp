package com.example.bookapp.books_class;

public class The_Quotes {

    String likes = "" ;
    String user = "" ;
    String quotes_text = "" ;
    String tag = "" ;


    public The_Quotes(String likes, String user, String quotes_text, String tag) {
        this.likes = likes;
        this.user = user;
        this.quotes_text = quotes_text;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "The_Quotes{" +
                "likes='" + likes + '\'' +
                ", user='" + user + '\'' +
                ", quotes_text='" + quotes_text + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public The_Quotes() {
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuotes_text() {
        return quotes_text;
    }

    public void setQuotes_text(String quotes_text) {
        this.quotes_text = quotes_text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
