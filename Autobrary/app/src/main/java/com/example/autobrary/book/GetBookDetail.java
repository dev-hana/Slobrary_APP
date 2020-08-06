package com.example.autobrary.book;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.DetailBookInfo;
import com.example.autobrary.info.book.DetailBookInfo;
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

public class GetBookDetail {
    public Vector<DetailBookInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<DetailBookInfo> book = new Vector<>();
        String REQUEST_PAGE = "Wishlist.jsp";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            HashMap param = new HashMap();
            param.put("id_num", SessionManager.getAttribute(null));
            // 책 아이디 보내주기
            //param.put("mem_id", SessionManager.getAttribute("login"));
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
//                    String bookType, String objectInfo, String isbnInfo, String sortSign, String languageInfo, int recommentScore
                    String bookType = new JSONObject(jsonResult.getString(j)).getString("bookType");
                    String objectInfo = new JSONObject(jsonResult.getString(j)).getString("objectInfo");
                    String isbnInfo = new JSONObject(jsonResult.getString(j)).getString("isbnInfo");
                    String sortSign = new JSONObject(jsonResult.getString(j)).getString("sortSign");
                    String languageInfo = new JSONObject(jsonResult.getString(j)).getString("languageInfo");
                    String recommentScore = new JSONObject(jsonResult.getString(j)).getString("recommentScore");
                    DetailBookInfo fetchDetailBook = new DetailBookInfo(j, bookType, objectInfo, isbnInfo, sortSign, languageInfo, recommentScore);
                    book.add(fetchDetailBook);
                }
           // }else{
           //     Log.e("Wish Error", "Wish fetch failed");
           // }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Book List Error", "Book fetch failed");
            book.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return book;
    }
}
