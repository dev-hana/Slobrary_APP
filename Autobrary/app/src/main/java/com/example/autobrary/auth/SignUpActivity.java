package com.example.autobrary.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autobrary.R;

import java.util.Vector;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signUpBt = (Button) findViewById(R.id.signUp);
        signUpBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = (EditText) findViewById(R.id.edtId);
                EditText pw = (EditText) findViewById(R.id.edtPw);
                EditText reEnterPw = (EditText) findViewById(R.id.edtReEnterPw);
                EditText userName = (EditText) findViewById(R.id.userName);
                EditText phoneNv = (EditText) findViewById(R.id.phoneNv);
                EditText homeAddr= (EditText) findViewById(R.id.homeAddr);
                EditText mail= (EditText) findViewById(R.id.email);

                // 빈칸 무결성 체크
                // TODO : 라디오박스 추가해야함, 각각 세분화된 로직이 필요함. 또한 signup으로 넘어가는 로직 추가해야함. => 이 함수는 미완성
                Vector<EditText> allField = new Vector<EditText>();
                allField.add(id);
                allField.add(pw);
                allField.add(reEnterPw);
                allField.add(userName);
                allField.add(phoneNv);
                allField.add(homeAddr);
                allField.add(mail);
                for(int i = 0; i < allField.size(); i++){
                    if(allField.get(i).getText().toString().getBytes().length <= 0){
                        Toast.makeText(SignUpActivity.this, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(pw.getText() != reEnterPw.getText()){
                    Toast.makeText(SignUpActivity.this, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
