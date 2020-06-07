package com.example.autobrary.auth.info;

import com.example.autobrary.encryption.PBKDF2_Encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SignUpInfo {

    private String RFID;
    private String mem_id;
    private String mem_pw;
    private String mem_name;
    private String mem_gender;
    private String mem_phone;
    private String mem_birth;
    private String mem_address;
    private String mem_mail;
    private String loan_status;
    private String mem_date;
    public String getRFID() {
        return RFID;
    }
    public void setRFID(String rFID) {
        RFID = rFID;
    }
    public String getMem_id() {
        return mem_id;
    }
    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }
    public String getMem_name() {
        return mem_name;
    }
    public void setMem_name(String mem_name) throws UnsupportedEncodingException {
//        this.mem_name = mem_name;
      this.mem_name = URLEncoder.encode(mem_name,"UTF-8");
    }
    public String getMem_gender() {
        return mem_gender;
    }
    public void setMem_gender(String mem_gender) {
        this.mem_gender = mem_gender;
    }
    public String getMem_phone() {
        return mem_phone;
    }
    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }
    public String getMem_birth() {
        return mem_birth;
    }
    public void setMem_birth(String mem_birth) {
        this.mem_birth = mem_birth;
    }
    public String getMem_address() {
        return mem_address;
    }
    public void setMem_address(String mem_address) throws UnsupportedEncodingException {
    this.mem_address = URLEncoder.encode(mem_address,"UTF-8");
  //      this.mem_address = mem_address;
    }
    public String getMem_email() {
        return mem_mail;
    }
    public void setMem_email(String mem_mail) {
        this.mem_mail = mem_mail;
    }
    public String getLoan_status() {
        return loan_status;
    }
    public void setLoan_status(String loan_status) {
        this.loan_status = loan_status;
    }
    public String getMem_date() {
        return mem_date;
    }
    public void setMem_date(String mem_date) {
        this.mem_date = mem_date;
    }

    public String getMem_pw() {
        return mem_pw;
    }
    public void setMem_pw(String mem_pw) {
        try {
            this.mem_pw = PBKDF2_Encryption.createHash(mem_pw);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        };
    }
}
