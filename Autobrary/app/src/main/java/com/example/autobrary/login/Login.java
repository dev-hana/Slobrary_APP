package com.example.autobrary.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobrary.R;
import com.example.autobrary.database.URLConnector;

public class Login extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLogIn, btnSUp;
    String POST = "http://localhost/MediumServer/SelectAllPost.php";
    URLConnector task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        Button loginButton = (Button)findViewById(R.id.btnLogIn);
        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText idField = (EditText)findViewById(R.id.edtId);
                EditText pwField = (EditText)findViewById(R.id.edtPw);
                if(idField.getText().toString().getBytes().length <= 0 || pwField.getText().toString().getBytes().length <= 0){
                    Toast.makeText(Login.this, "아이디 와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
