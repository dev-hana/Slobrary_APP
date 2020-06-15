package com.example.autobrary.mypage.info;

public class InterestBookInfo extends BookInfo {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status; //책 상태값
}
