package com.example.autobrary.book;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.DetailBookInfo;
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

public class BookDetailDB {
    public Vector<DetailBookInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<DetailBookInfo> bookdb = new Vector<>();
        String REQUEST_PAGE = "Wishlist.jsp"; // 수정하기

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            HashMap param = new HashMap();
            param.put("mem_id", SessionManager.getAttribute("login")); // 수정하기 - 책 id로
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
                for(String j : jsonKeyList){ // 수정하기 책 상세페이지 정보로
                    String bookType = new JSONObject(jsonResult.getString(j)).getString("bookType");
                    String publishInfo = new JSONObject(jsonResult.getString(j)).getString("publishInfo");
                    String objectInfo = new JSONObject(jsonResult.getString(j)).getString("objectInfo");
                    String isbnNv = new JSONObject(jsonResult.getString(j)).getString("isbnNv");
                    String sortSign = new JSONObject(jsonResult.getString(j)).getString("sortSign");
                    String language = new JSONObject(jsonResult.getString(j)).getString("language");
                    int recommentScore = new JSONObject(jsonResult.getString(j)).getInt("recommentScore");
                    DetailBookInfo fetchBookDB = new DetailBookInfo(bookType, publishInfo, objectInfo, isbnNv, sortSign, language, recommentScore);
                    bookdb.add(fetchBookDB);
                }
           // }else{
           //     Log.e("Wish Error", "Wish fetch failed");
           // }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error", "fetch failed");
            bookdb.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return bookdb;
    }
}
