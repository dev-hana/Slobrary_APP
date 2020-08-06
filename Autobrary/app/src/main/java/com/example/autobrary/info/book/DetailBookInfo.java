package com.example.autobrary.info.book;

public class DetailBookInfo extends BookInfo {
    private String bookType; //자료유형
    private String objectInfo; //형태사항
    private String isbnNv; //ISBN 번호
    private String sortSign; //분류기호
    private String language; //언어
    private int recommentScore; //평점

    public DetailBookInfo(String bookType, String objectInfo, String isbnNv, String sortSign, String language, int recommentScore){
        this.bookType = bookType;
        this.objectInfo = objectInfo;
        this.isbnNv = isbnNv;
        this.sortSign = sortSign;
        this.language = language;
        this.recommentScore = recommentScore;
    }

    public DetailBookInfo(){}


    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(String objectInfo) {
        this.objectInfo = objectInfo;
    }

    public String getIsbnNv() {
        return isbnNv;
    }

    public void setIsbnNv(String isbnNv) {
        this.isbnNv = isbnNv;
    }

    public String getSortSign() {
        return sortSign;
    }

    public void setSortSign(String sortSign) {
        this.sortSign = sortSign;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRecommentScore() {
        return recommentScore;
    }

    public void setRecommentScore(int recommentScore) {
        this.recommentScore = recommentScore;
    }
}
