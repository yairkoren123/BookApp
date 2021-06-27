package com.example.bookapp.books_class;

public class The_Book {

    String title = "";
    String publishedDate = "";
    String language = "";
    String country = "";
    String description = "";
    int pageCount = 0;
    String categories = "";
    String averageRating = "";
    String imagesfront = "";
    String imageback = "";
    String infoLink = "";
    String authors = "";
    String people_ratting = "";
    String click_URL = "";

    public The_Book() {
    }

    @Override
    public String toString() {
        return "The_Book{" +
                "title='" + title + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", categories='" + categories + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", imagesfront='" + imagesfront + '\'' +
                ", imageback='" + imageback + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }

    public The_Book(String title, String publishedDate, String language, String country,
                    String description, int pageCount, String categories, String averageRating,
                    String imagesfront, String imageback, String infoLink, String authors) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.language = language;
        this.country = country;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.imagesfront = imagesfront;
        this.imageback = imageback;
        this.infoLink = infoLink;
        this.authors = authors;
    }

    // get and set


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getImagesfront() {
        return imagesfront;
    }

    public void setImagesfront(String imagesfront) {
        this.imagesfront = imagesfront;
    }

    public String getImageback() {
        return imageback;
    }

    public void setImageback(String imageback) {
        this.imageback = imageback;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getClick_URL() {
        return click_URL;
    }

    public void setClick_URL(String click_URL) {
        this.click_URL = click_URL;
    }

    public String getPeople_ratting() {
        return people_ratting;
    }

    public void setPeople_ratting(String people_ratting) {
        this.people_ratting = people_ratting;
    }
}
