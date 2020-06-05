package com.example.autobrary.auth;

import com.example.autobrary.externalConnecter.URLConnector;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;

public class ValidateEmail {
    private String email;

    /**
     * 아이디의 중복여부를 리턴 합니다.
     *
     * @param   email   중복 검증할 아이디
     * @return   boolean    아이디가 존재하면 true, 존재하지 않으면 false를 리턴
     */
    public boolean ValidateEmail(String email) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        this.email = email;
        return execute();
    }
    private boolean execute() {

        String REQUEST_PAGE = "Email_check.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("email", email);

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            result = task.getData();
            if (new JSONObject(result).getString("success").equals("true")) {
                validateResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return validateResult;
    }
}
