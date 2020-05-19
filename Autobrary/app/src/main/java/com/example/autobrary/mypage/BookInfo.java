package com.example.autobrary.mypage;

public class BookInfo {
    String name; // 제목
    String author; // 작가
    String publisher; // 출판사
    Object image; // 표지

    public BookInfo(String name, String author, String publisher, Object image){
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
