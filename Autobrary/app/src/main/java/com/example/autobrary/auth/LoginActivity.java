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
                EditText idField = (EditText)findViewById(R.id.edtId);
                EditText pwField = (EditText)findViewById(R.id.edtPw);
                if(idField.getText().toString().getBytes().length <= 0 || pwField.getText().toString().getBytes().length <= 0){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    LoginInfo info = new LoginInfo();
                    info.setLoginId(idField.getText().toString());
                    info.setLoginPw(pwField.getText().toString());
                    Login login = new Login(info);
                    String result = null;
                    try {
                        result = login.execute();
                        if(result.equals("false")){
                            Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            if(PBKDF2_Encryption.validatePassword(info.getLoginPw(), result)){
                                //TODO : 나중에 PASS 토스트 지울것.
                                Toast.makeText(LoginActivity.this, "pass", Toast.LENGTH_SHORT).show();

                                //TODO : 로그인 성공 로직 입력.

                            }else{
                                Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                            }
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
