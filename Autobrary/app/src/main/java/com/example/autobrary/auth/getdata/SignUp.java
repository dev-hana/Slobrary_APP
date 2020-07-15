package com.example.autobrary.auth.getdata;

import com.example.autobrary.info.auth.SignUpInfo;
import com.example.autobrary.externalConnecter.URLConnector;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import cz.msebera.android.httpclient.HttpEntity;

public class SignUp{
    private SignUpInfo info;

    public SignUp(SignUpInfo info) {
        this.info = info;
    }

    public boolean execute() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String REQUEST_PAGE = "Register.php";
        HashMap<String, String> param = new HashMap<>();

        // 파라미터 입력
        param.put("mem_id", info.getMem_id());
        param.put("passwd", info.getMem_pw());
        param.put("name", info.getMem_name());
        param.put("gender", info.getMem_gender());
        param.put("phone", info.getMem_phone());
        param.put("birth", info.getMem_birth());
        param.put("address", info.getMem_address());
        param.put("email", info.getMem_email());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.execute().get();
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
