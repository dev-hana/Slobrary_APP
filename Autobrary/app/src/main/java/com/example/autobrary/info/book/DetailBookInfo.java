package com.example.autobrary.info.book;

public class DetailBookInfo extends BookInfo {
    private String bookType; //자료유형
    private String objectInfo; //형태사항
    private String isbnInfo; //ISBN 번호
    private String sortSign; //분류기호
    private String languageInfo; //언어
    private String recommentScore; //평점

    public DetailBookInfo(String id, String bookType, String objectInfo, String isbnInfo, String sortSign, String languageInfo, String recommentScore){
        this.id = id;
        this.bookType = bookType;
        this.objectInfo = objectInfo;
        this.isbnInfo = isbnInfo;
        this.sortSign = sortSign;
        this.languageInfo = languageInfo;
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

    public String getIsbnInfo() {
        return isbnInfo;
    }

    public void setIsbnInfo(String isbnInfo) {
        this.isbnInfo = isbnInfo;
    }

    public String getSortSign() {
        return sortSign;
    }

    public void setSortSign(String sortSign) {
        this.sortSign = sortSign;
    }

    public String getLanguageInfo() {
        return languageInfo;
    }

    public void setLanguageInfo(String languageInfo) {
        this.languageInfo = languageInfo;
    }

    public String getRecommentScore() {
        return recommentScore;
    }

    public void setRecommentScore(String recommentScore) {
        this.recommentScore = recommentScore;
    }
}
