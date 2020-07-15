package com.example.autobrary.mypage.getdata;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.InterestBookInfo;
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

public class GetInterestBook {
    public Vector<InterestBookInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<InterestBookInfo> interestBook = new Vector<>();
        String REQUEST_PAGE = "MypageWatch.jsp";//TODO : 서버 경로 넣기.

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            HashMap param = new HashMap();
            param.put("mem_id", SessionManager.getAttribute("login"));
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
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
                    InterestBookInfo fetchBook = new  InterestBookInfo();
                    String id_num = new JSONObject(jsonResult.getString(j)).getString("id_num");
                    String name = new JSONObject(jsonResult.getString(j)).getString("name");
                    String author = new JSONObject(jsonResult.getString(j)).getString("author");
                    String publisher = new JSONObject(jsonResult.getString(j)).getString("publisher");
                    String status = new JSONObject(jsonResult.getString(j)).getString("status");
                    String image = new JSONObject(jsonResult.getString(j)).getString("image");
                    fetchBook.setId(id_num);
                    fetchBook.setName(name);
                    fetchBook.setAuthor(author);
                    fetchBook.setPublisher(publisher);
                    fetchBook.setStatus(status);
                    fetchBook.setImage(image);
                    interestBook.add(fetchBook);
                }
          //  }else{
            //    Log.e("Loan Book Error", "fetch failed");
          //  }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Interest Book Error", "fetch failed");
            interestBook.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return interestBook;
    }
}
