package com.example.autobrary.externalConnecter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.InputStream;
import io.minio.MinioClient;



public class BucketConnector extends Thread {
    //********보안정보 취급 주의**********
    private final Integer PORT = 9000;
    private final String ENDPOINT = "http://slobrary.com";
    private final String ACCESS_KEY = "slo";
    private final String SECRET_KEY = "legoslo2020";
    private final String BUCKET_NAME = "slo";

    private String objectName = null;
    private Bitmap bitmap;
    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public void run() {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, PORT, ACCESS_KEY, SECRET_KEY);
            bitmap = BitmapFactory.decodeStream(minioClient.getObject(BUCKET_NAME, objectName));
        }catch (NullPointerException e){ //objectName이 정의 되지않고 실행 되었을때.
            Log.e("Bucket error", "Bucket objectname not defined error :" + e);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}