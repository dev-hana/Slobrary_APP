package com.example.autobrary.mypage;

import android.widget.ImageView;

public class MpBookInfo {
    String image; // 책 표지
    String name; // 제목
    String author; // 작가
    //String userId; // 아이디 넘겨줘야 할 것 같음.

    public MpBookInfo(String image, String name, String author){
        this.image = image;
        this.name = name;
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
}