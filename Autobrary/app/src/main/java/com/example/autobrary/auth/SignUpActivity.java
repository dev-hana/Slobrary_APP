package com.example.autobrary.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.autobrary.R;
import java.util.Vector;

public class SignUpActivity extends AppCompatActivity {
    private RadioButton g_man, g_woman;
    private RadioGroup entGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signUpBt = (Button) findViewById(R.id.signUp);

        //라디오 버튼 클릭 리스너
        final RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        };
        //라디오 그룹 클릭 리스너
        final RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.g_man){

                }
                else if(i == R.id.g_woman){

                }
            }
        };

        signUpBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<EditText> allField = new Vector<EditText>();
                SignUpInfo infoOfUser = new SignUpInfo();

                EditText id = (EditText) findViewById(R.id.entId);
                EditText entPw = (EditText) findViewById(R.id.entPw);
                EditText entPw_r = (EditText) findViewById(R.id.entPw_re);
                EditText name = (EditText) findViewById(R.id.entName);
                EditText phone = (EditText) findViewById(R.id.entPhone);
                EditText addr= (EditText) findViewById(R.id.entAddr);
                EditText email= (EditText) findViewById(R.id.entEmail);
                infoOfUser.setMem_id(id.getText().toString());
                infoOfUser.setMem_pw(entPw.getText().toString());
                infoOfUser.setMem_pw_r(entPw_r.getText().toString());
                infoOfUser.setMem_name(name.getText().toString());
                infoOfUser.setMem_phone(phone.getText().toString());
                infoOfUser.setMem_address(addr.getText().toString());
                infoOfUser.setMem_email(email.getText().toString());


                // Log.i("PBKDF2 RESULT", infoOfUser.getMem_pw());
                // 빈칸 무결성 체크
                // TODO : 라디오박스 추가해야함, 각각 세분화된 로직이 필요함. 또한 signup으로 넘어가는 로직 추가해야함. => 이 함수는 미완성
                // activity_signup에서 라디오 그룹의 데이터 값을 받아서 allfield에 넣어줘야함

                // 라디오 버튼 설정
                g_man = (RadioButton) findViewById(R.id.g_man);
                g_woman = (RadioButton) findViewById(R.id.g_woman);
                g_man.setOnClickListener(radioButtonClickListener);
                g_woman.setOnClickListener(radioButtonClickListener);

                // 라디오 그룹 설정
                entGender = (RadioGroup) findViewById(R.id.entGender);
                entGender.setOnCheckedChangeListener(radioGroupButtonChangeListener);



                allField.add(id);
                allField.add(entPw);
                allField.add(entPw_r);
                allField.add(name);
                allField.add(phone);
                allField.add(addr);
                allField.add(email);


                for(int i = 0; i < allField.size(); i++){
                    if(allField.get(i).getText().toString().getBytes().length <= 0){

                        Toast.makeText(SignUpActivity.this, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if(!entPw.getText().toString().equals(entPw_r.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                }


//                for(int i = 0; i < allField.size(); i++){
//                    if(allField.get(i).getText().toString().getBytes().length <= 0){
//                        Toast.makeText(SignUpActivity.this, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                if(pw.getText() != reEnterPw.getText()){
//                    Toast.makeText(SignUpActivity.this, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }

}
