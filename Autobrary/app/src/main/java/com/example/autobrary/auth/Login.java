package com.example.autobrary.auth;

import com.example.autobrary.database.URLConnector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpResponse;

public class Login {
    private LoginInfo info;

    public Login(LoginInfo info) {
        this.info = info;
    }

    public String execute() throws IOException {
        String REQUEST_PAGE = "login.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("ID", info.getLoginId());

        InputStream rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            HttpResponse resp = task.execute();
            rawData = resp.getEntity().getContent();
            bis = new BufferedInputStream(rawData);
            byte[] buffer = new byte[1024];
            while ((bis.read(buffer)) != -1) {
                result = new String(buffer, "utf-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bis != null) bis.close();
                if(rawData != null) rawData.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return result;
    }
}
