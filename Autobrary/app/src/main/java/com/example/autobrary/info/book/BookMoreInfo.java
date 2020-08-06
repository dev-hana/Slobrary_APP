package com.example.autobrary.info.book;

public class BookMoreInfo extends BookInfo{
    String id_num; //등록번호
    String book_symbol; // 청구기호
    String collector; // 소장처
    String stauts; // 도서상태
    String return_date; // 반납예정일

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getBook_symbol() {
        return book_symbol;
    }

    public void setBook_symbol(String book_symbol) {
        this.book_symbol = book_symbol;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}