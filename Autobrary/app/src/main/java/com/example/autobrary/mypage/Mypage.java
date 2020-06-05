package com.example.autobrary.mypage;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.session.SessionManager;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;

public class Mypage {
    public MypageInfo execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        MypageInfo mypage = null;
        String REQUEST_PAGE = "Member_info.php";

        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("mem_id", SessionManager.getAttribute("login"));

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            result = task.getData();
            JSONObject jsonResult = new JSONObject(result);
            if(jsonResult.getString("success").equals("true")) {
                    String name = jsonResult.getString("name");
                    String email = jsonResult.getString("email");
                    String profileImage = jsonResult.getString("profile_img");
                    MypageInfo fetchMypage = new MypageInfo(name, email, profileImage);
                    mypage = fetchMypage;
            }else{
                Log.e("Mypage Error", "Mypage fetch failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Mypge Error", "Mypage fetch failed");
            mypage = null;
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return mypage;
    }
}