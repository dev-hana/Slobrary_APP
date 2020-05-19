package com.example.autobrary.mypage;

public class MpBookInfo {
    String imgae; // 책 표지
    String name; // 제목
    String author; // 작가

    public MpBookInfo(String image, String name, String author){
        this.imgae = image;
        this.name = name;
        this.author = author;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
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
}