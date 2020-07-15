package com.example.autobrary.info.book;

import com.example.autobrary.info.book.BookInfo;

public class ReturnBookInfo extends BookInfo {

    String loanDate;
    String returnDate; //도서 반납일
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

}
