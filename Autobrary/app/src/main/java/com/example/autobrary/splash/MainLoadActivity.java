package com.example.autobrary.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;

public class MainLoadActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), Rpage.class);  // Intent 선언
                startActivity(intent);   // Intent 시작
                finish();
            }
        }, 1400);  // 로딩화면 시간
    }


}
