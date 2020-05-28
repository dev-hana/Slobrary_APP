package com.example.autobrary.wish;

import com.example.autobrary.auth.LoginInfo;
import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;

public class WishApply {
    WishInfo info;
    public WishApply(WishInfo info) {
        this.info = info;
    }

    public boolean execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "PutWish.php"; //TODO : PHP 경로 입력
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("bookTitle", info.getBookTitle());
        param.put("bookAuthor", info.getBookOwner());
        param.put("bookPublish", info.getBookPublish());
        param.put("applyUser", info.getUser());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            result = task.getData();
            if (new JSONObject(result).getString("success").equals("true")) {
                validateResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return validateResult;
    }
}
