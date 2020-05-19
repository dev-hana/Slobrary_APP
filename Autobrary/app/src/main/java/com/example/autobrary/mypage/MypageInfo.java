package com.example.autobrary.mypage;

public class MypageInfo {
    //유저
    String name; // 이름
    String email; //이메일

    public MypageInfo(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
