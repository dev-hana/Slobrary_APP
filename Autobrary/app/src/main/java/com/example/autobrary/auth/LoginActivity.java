package com.example.autobrary.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobrary.R;
import com.example.autobrary.database.URLConnector;
import com.example.autobrary.encryption.PBKDF2_Encryption;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLogIn, btnSUp;
    String POST = "http://localhost/MediumServer/SelectAllPost.php";
    URLConnector task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button)findViewById(R.id.btnLogIn);
        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginInfo info = new LoginInfo();
                EditText idField = (EditText)findViewById(R.id.edtId);
                EditText pwField = (EditText)findViewById(R.id.edtPw);
                info.setLoginId(idField.getText().toString());
                info.setLoginPw(pwField.getText().toString());
                if(info.getLoginId().getBytes().length <= 0 || info.getLoginPw().getBytes().length <= 0){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login(info);
                    try {
                        if(login.execute()){
                            Toast.makeText(LoginActivity.this, "pass", Toast.LENGTH_SHORT).show();
                            //TODO : 로그인 성공 로직 입력.
                        }else{
                            Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button signUp = (Button) findViewById(R.id.btnSUp);
        signUp.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signUpAct = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpAct);
            }
        });

    }
}
