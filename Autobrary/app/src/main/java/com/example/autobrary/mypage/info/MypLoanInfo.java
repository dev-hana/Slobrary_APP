package com.example.autobrary.mypage.info;

public class MypLoanInfo {
    String name; // 제목
    String author; // 작가
    String publisher; // 출판사
    Object image; // 표지
    String loan_id; // 대출하면 부여되는 고유 넘버
    String mem_id; // 책 빌린 사람 아이디?
    String id_num; // 책 아이디?
    int ex_num; //??
    String loan_date; // 빌린 날짜
    String return_date; // 반납 날짜

    public MypLoanInfo(String name, String author, String publisher, Object image, String loan_date, String return_date){
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
        this.loan_date = loan_date;
        this.return_date = return_date;
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

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public int getEx_num() {
        return ex_num;
    }

    public void setEx_num(int ex_num) {
        this.ex_num = ex_num;
    }

    public String getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(String loan_date) {
        this.loan_date = loan_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}