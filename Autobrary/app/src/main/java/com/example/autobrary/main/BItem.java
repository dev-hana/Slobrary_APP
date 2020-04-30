package com.example.autobrary.main;

import android.graphics.drawable.Drawable;

public class BItem {
    //private Drawable cover;
    private String title;
    private String auth;
    private String publish;
    private String date;

//    public Drawable getCover() {
//        return cover;
//    }
//
//    public void setCover(Drawable cover) {
//        this.cover = cover;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
