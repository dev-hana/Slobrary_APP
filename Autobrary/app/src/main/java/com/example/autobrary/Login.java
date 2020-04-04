package com.example.autobrary;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLog;
    String POST = "http://localhost/MediumServer/SelectAllPost.php";
    URLConnector task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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

    }
}
