package com.example.autobrary.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.autobrary.R;
import com.example.autobrary.auth.getdata.Login;
import com.example.autobrary.info.auth.LoginInfo;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.session.SessionManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CheckBox autologin = findViewById(R.id.autoLoginChkBox);
        BootstrapButton loginButton = (BootstrapButton)findViewById(R.id.btnLogIn);

        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginInfo info = new LoginInfo();
                BootstrapEditText idField = (BootstrapEditText)findViewById(R.id.entId);
                BootstrapEditText pwField = (BootstrapEditText)findViewById(R.id.entPw);
                info.setLoginId(idField.getText().toString());
                info.setLoginPw(pwField.getText().toString());
                SharedPreferences pref = getSharedPreferences("slo",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(info.getLoginId().getBytes().length <= 0 || info.getLoginPw().getBytes().length <= 0){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login(info);
                    try {
                        if(login.execute()){
                            if(autologin.isChecked()){

                                editor.putString("login", info.getLoginId());
                                editor.commit();
                            }else{
                                editor.putString("login", "");
                                editor.commit();
                            }
                            SessionManager.setAttribute("login", info.getLoginId());
                            Intent mainAct = new Intent(getApplicationContext(), Rpage.class);
                            mainAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            mainAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(mainAct);
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
        BootstrapButton signUp = (BootstrapButton) findViewById(R.id.btnSUp);
        signUp.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signUpAct = new Intent(getApplicationContext(), SignUpActivity.class);
                signUpAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                signUpAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(signUpAct);
            }
        });

    }
}
