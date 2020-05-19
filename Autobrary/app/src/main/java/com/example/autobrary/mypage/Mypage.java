package com.example.autobrary.mypage;

import android.util.Log;

import com.example.autobrary.database.URLConnector;
import com.example.autobrary.notice.NoticeInfo;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Mypage {
    public Vector<MypageInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<MypageInfo> mypage = new Vector<>();
        String REQUEST_PAGE = ".php";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, new HashMap());
            task.start();
            task.join();
            HttpResponse resp = task.getResult();
            rawData = resp.getEntity();
            result = EntityUtils.toString(rawData);
            JSONObject jsonResult = new JSONObject(result);
            if(jsonResult.getString("success").equals("true")) {
                ArrayList<String> jsonKeyList = new ArrayList<>();
                Iterator i = jsonResult.keys();
                while (i.hasNext()) {
                    String b = i.next().toString();
                    jsonKeyList.add(b);
                }
                for(int j = 1; j < jsonKeyList.size(); j++){
                    String name = new JSONObject(jsonResult.getString(Integer.toString(j))).getString("name");
                    String email = new JSONObject(jsonResult.getString(Integer.toString(j))).getString("email");
                    MypageInfo fetchMypage = new MypageInfo(name, email);
                    mypage.add(fetchMypage);
                }
            }else{
                Log.e("Mypage Error", "Mypage fetch failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Mypge Error", "Mypage fetch failed");
            mypage.clear();
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