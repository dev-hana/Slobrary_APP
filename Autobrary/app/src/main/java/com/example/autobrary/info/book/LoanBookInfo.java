package com.example.autobrary.info.book;

public class LoanBookInfo extends BookInfo {
     public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    private String loanDate;
}
