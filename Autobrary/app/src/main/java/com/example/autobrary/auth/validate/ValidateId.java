package com.example.autobrary.auth.validate;

import com.example.autobrary.externalConnecter.URLConnector;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpEntity;

public class ValidateId {
    private String id;

    /**
     * 아이디의 중복여부를 리턴 합니다.
     *
     * @param   id    중복 검증할 아이디
     * @return   boolean    아이디가 존재하면 true, 존재하지 않으면 false를 리턴
     */
    public boolean IdCheck(String id) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        this.id = id;
        return execute();
    }
    private boolean execute() {

        String REQUEST_PAGE = "Join_check.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("mem_id", id);

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
        }finally {
            try{
                if(bis != null) bis.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return validateResult;
    }
}
