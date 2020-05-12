package com.example.autobrary.auth;

import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

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
        param.put("adress", info.getMem_address());
        param.put("email", info.getMem_email());

        HttpEntity rawData = null;
        BufferedInputStream bis = null;
        String result = "false";
        boolean validateResult = false;
        try {
            URLConnector task = new URLConnector(REQUEST_PAGE, param);
            task.start();
            task.join();
            HttpResponse resp = task.getResult();
            rawData = resp.getEntity();
            result = EntityUtils.toString(rawData);
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
