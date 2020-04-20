package com.example.autobrary.auth;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Login  {
    private LoginInfo info;

    public Login(LoginInfo info) {
        this.info = info;
    }

    public boolean execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "Login.do";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("ID", info.getLoginId());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            HttpResponse resp = task.getResult();
            rawData = resp.getEntity();
            result = EntityUtils.toString(rawData);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        if(PBKDF2_Encryption.validatePassword(info.getLoginPw(),result)){
            return true;
        }else{
            return false;
        }

    }
}
