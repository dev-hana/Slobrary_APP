package com.example.autobrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLog;
    String POST = "http://localhost/MediumServer/SelectAllPost.php";
    URLConnector task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new URLConnector(POST);

        task.start();

        try{
            task.join();
            System.out.println("waiting... for result");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = task.getResult();
        System.out.println(result);

        Button loginButton = (Button)findViewById(R.id.btnLog);
        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText idField = (EditText)findViewById(R.id.edtId);
                EditText pwField = (EditText)findViewById(R.id.edtPw);
                if(idField.getText().toString().getBytes().length <= 0 || pwField.getText().toString().getBytes().length <= 0){
                    Toast.makeText(MainActivity.this, "아이디 와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
