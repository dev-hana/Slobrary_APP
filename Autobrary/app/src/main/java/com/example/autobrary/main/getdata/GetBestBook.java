package com.example.autobrary.main.getdata;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.BookInfo;
import com.example.autobrary.info.notice.NoticeInfo;

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

public class GetBestBook {
    public Vector<BookInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<BookInfo> bestBook = new Vector<>();
        String REQUEST_PAGE = "BestsellerList.jsp";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, new HashMap());
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
                BookInfo fetchBook = new BookInfo();
                String id_num = new JSONObject(jsonResult.getString(j)).getString("book_id");
                String image = new JSONObject(jsonResult.getString(j)).getString("image");
                fetchBook.setId(id_num);
                fetchBook.setImage(image);
                bestBook.add(fetchBook);
            }
            //  }else{
            //    Log.e("Loan Book Error", "fetch failed");
            //  }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Best Book Error", "fetch failed");
            bestBook.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return bestBook;
    }
}
