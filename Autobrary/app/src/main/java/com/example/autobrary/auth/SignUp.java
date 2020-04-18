package com.example.autobrary.auth;

import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SignUp{

    private SignUpInfo info;
    public String SignUp(SignUpInfo info) throws InvalidKeySpecException, NoSuchAlgorithmException {
        this.info = info;
        this.info.setMem_pw(PBKDF2_Encryption.createHash(info.getMem_pw()));
        return insertDatabase();
    }

    /*
     * TODO : 매 요청에 따른 요청 주소의 편집이 필요함.
     * */
    private String insertDatabase(){
        URLConnector task = new URLConnector("insertMember.do");
        task.start();
        try{
            task.join();
        }
        catch(InterruptedException e){

        }
       return task.getResult();
    }
}
