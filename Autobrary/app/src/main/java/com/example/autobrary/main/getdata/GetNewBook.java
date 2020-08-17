package com.example.autobrary.main.getdata;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.BookInfo;

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

public class GetNewBook {
    public Vector<BookInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<BookInfo> newBook = new Vector<>();
        String REQUEST_PAGE = "Newbook_main.jsp";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, new HashMap());
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
                BookInfo fetchBook = new BookInfo();
                String id_num = new JSONObject(jsonResult.getString(j)).getString("id_num");
                String image = new JSONObject(jsonResult.getString(j)).getString("image");
                fetchBook.setId(id_num);
                fetchBook.setImage(image);
                newBook.add(fetchBook);
            }
            //  }else{
            //    Log.e("Loan Book Error", "fetch failed");
            //  }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("New Book Error", "new fetch failed");
            newBook.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return newBook;
    }
}
