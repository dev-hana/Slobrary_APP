package com.example.autobrary.wish;

public class WishInfo {
    private String user;
    private String bookTitle;
    private String bookOwner;
    private String bookPublish;

    WishInfo(String user, String bookTitle, String bookOwner, String bookPublish){
        this.user = user;
        this.bookTitle = bookTitle;
        this.bookOwner = bookOwner;
        this.bookPublish = bookPublish;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookOwner() {
        return bookOwner;
    }

    public void setBookOwner(String bookOwner) {
        this.bookOwner = bookOwner;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

}
