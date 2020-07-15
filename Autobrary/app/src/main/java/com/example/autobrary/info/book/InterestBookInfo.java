package com.example.autobrary.info.book;

public class InterestBookInfo extends BookInfo {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status; //책 상태값
}
