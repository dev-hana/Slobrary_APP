package com.example.autobrary.wish;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.wish.WishInfo;
import com.example.autobrary.session.SessionManager;

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

public class WishList {
    public Vector<WishInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<WishInfo> wish = new Vector<>();
        String REQUEST_PAGE = "Wishlist.jsp";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            HashMap param = new HashMap();
            param.put("mem_id", SessionManager.getAttribute("login"));
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.execute().get();
            result = task.getData();
            JSONObject jsonResult = new JSONObject(result);
           // if(jsonResult.getString("success").equals("true")) {
                ArrayList<String> jsonKeyList = new ArrayList<>();
                Iterator i = jsonResult.keys();
                while (i.hasNext()) {
                    String b = i.next().toString();
                    jsonKeyList.add(b);
                }

               // jsonKeyList.remove(0); //성공여부 배열 지우기
                for(String j : jsonKeyList){
                    String title = new JSONObject(jsonResult.getString(j)).getString("book_name");
                    String author = new JSONObject(jsonResult.getString(j)).getString("book_author");
                    String publish = new JSONObject(jsonResult.getString(j)).getString("publish");
                    String bDate = new JSONObject(jsonResult.getString(j)).getString("wish_date");
                    String applyStatus = new JSONObject(jsonResult.getString(j)).getString("status");
                    WishInfo fetchWish = new WishInfo(j, title, author, publish, bDate, applyStatus);
                    wish.add(fetchWish);
                }
           // }else{
           //     Log.e("Wish Error", "Wish fetch failed");
           // }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Wish Error", "Wish fetch failed");
            wish.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return wish;
    }
}
