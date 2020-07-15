package com.example.autobrary.auth.getdata;

import android.util.Log;

import com.example.autobrary.info.auth.LoginInfo;
import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;
import com.example.autobrary.session.SessionManager;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;

public class Login  {
    private LoginInfo info;

    public Login(LoginInfo info) {
        this.info = info;
    }
    public Login(String info){
        this.info = new LoginInfo();
        this.info.setLoginId(info);
    }

    public boolean execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "Login.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("ID", info.getLoginId());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            result = task.getData();
            if (PBKDF2_Encryption.validatePassword(info.getLoginPw(), new JSONObject(result).getString("PASSWD"))) {
                validateResult = true;
                param.clear();
                param.put("mem_id", info.getLoginId());
                task = new URLConnector("Member_info.php", param);
                task.start();
                task.join();
                result = task.getData();
                JSONObject jsonResult = new JSONObject(result);
                if(jsonResult.getString("success").equals("true")) {
                   SessionManager.setAttribute("name", jsonResult.getString("name"));
                   SessionManager.setAttribute("email", jsonResult.getString("email"));
                   SessionManager.setAttribute("profile_img", jsonResult.getString("profile_img"));
                }else{
                    Log.e("Mypage Error", "Mypage fetch failed");
                }
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
    public boolean autoLogin() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "Member_info.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("mem_id", info.getLoginId());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            result = task.getData();
            JSONObject jsonResult = new JSONObject(result);
            if(jsonResult.getString("success").equals("true")) {
                SessionManager.setAttribute("name", jsonResult.getString("name"));
                SessionManager.setAttribute("email", jsonResult.getString("email"));
                SessionManager.setAttribute("profile_img", jsonResult.getString("profile_img"));
            }else{
                Log.e("Mypage Error", "Mypage fetch failed");
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
