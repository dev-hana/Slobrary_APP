package com.example.autobrary.notice;

import android.os.Debug;
import android.util.Log;

import com.example.autobrary.auth.LoginInfo;
import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

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

public class Notice {
    public Vector<NoticeInfo> execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Vector<NoticeInfo> notice = new Vector<>();
        String REQUEST_PAGE = "Notice_all.php";

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, new HashMap());
            task.start();
            task.join();
            result = task.getData();
            JSONObject jsonResult = new JSONObject(result);
            if(jsonResult.getString("success").equals("true")) {
                ArrayList<String> jsonKeyList = new ArrayList<>();
                Iterator i = jsonResult.keys();
                while (i.hasNext()) {
                    String b = i.next().toString();
                    jsonKeyList.add(b);
                }
                jsonKeyList.remove(0); //성공여부 배열 지우기
                for(String j : jsonKeyList){
                    String title = new JSONObject(jsonResult.getString(j)).getString("name");
                    String date = new JSONObject(jsonResult.getString(j)).getString("date");
                    String owner = new JSONObject(jsonResult.getString(j)).getString("admin_id");
                    String content = new JSONObject(jsonResult.getString(j)).getString("content");
                    NoticeInfo fetchNotice = new NoticeInfo(j, title, date, owner, content);
                    notice.add(fetchNotice);
                }
            }else{
                Log.e("Notice Error", "Notice fetch failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Notice Error", "Notice fetch failed");
            notice.clear();
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return notice;
    }
}
