package com.example.autobrary.notice;

import com.example.autobrary.auth.LoginInfo;
import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Notice {
    public boolean execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "Login.php";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, new HashMap());
            task.start();
            task.join();
            HttpResponse resp = task.getResult();
            rawData = resp.getEntity();
            result = EntityUtils.toString(rawData);

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
