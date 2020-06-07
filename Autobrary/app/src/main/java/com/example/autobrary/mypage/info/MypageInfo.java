package com.example.autobrary.mypage.info;

public class MypageInfo {
    //유저
    String name; // 이름
    String email; //이메일
    String profileImg; //프로필 이미지
    public MypageInfo(String name, String email, String profileImg){
        this.name = name;
        this.email = email;
        this.profileImg = profileImg;
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

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}