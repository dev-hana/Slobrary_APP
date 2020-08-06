package com.example.autobrary.book;

import android.util.Log;

import com.example.autobrary.externalConnecter.URLConnector;
import com.example.autobrary.info.book.BookMoreInfo;
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

public class BookList {
    public Vector<BookMoreInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<BookMoreInfo> book = new Vector<>();
        String REQUEST_PAGE = "Wishlist.jsp";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            HashMap param = new HashMap();
            param.put("id_num", SessionManager.getAttribute(null)); //param.put("mem_id", SessionManager.getAttribute("login"));
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
//                    String id_num, String book_symbol, String collector, String stauts, String return_date
                    String book_symbol = new JSONObject(jsonResult.getString(j)).getString("book_symbol");
                    String collector = new JSONObject(jsonResult.getString(j)).getString("collector");
                    String status = new JSONObject(jsonResult.getString(j)).getString("status");
                    String return_date = new JSONObject(jsonResult.getString(j)).getString("return_date");
                    BookMoreInfo fetchBook = new BookMoreInfo(j, book_symbol, collector, status, return_date);
                    book.add(fetchBook);
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
