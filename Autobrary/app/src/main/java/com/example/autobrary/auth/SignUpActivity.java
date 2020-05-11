package com.example.autobrary.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.autobrary.R;
import com.example.autobrary.mail.Sender;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Vector;

public class SignUpActivity extends AppCompatActivity {
    private RadioButton g_man, g_woman;
    private RadioGroup entGender;
    private boolean validateIdFlag = false;
    private String randResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signUpBt = (Button) findViewById(R.id.signUp);
        final Button emailSend = (Button) findViewById(R.id.emailSend);
        final Button emailChButton = (Button) findViewById(R.id.emailCheck);
        // Spinner
        Spinner yearSpinner = (Spinner)findViewById(R.id.spinner_year);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, R.array.date_year, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Spinner monthSpinner = (Spinner)findViewById(R.id.spinner_month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.date_month, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        Spinner daySpinner = (Spinner)findViewById(R.id.spinner_day);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.date_day, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        emailSend.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText email= (EditText) findViewById(R.id.entEmail);
                EditText name = (EditText) findViewById(R.id.entName);
                try {
                    if(email.getText().toString().getBytes().length <= 0){
                        Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        if (new ValidateEmail().ValidateEmail(email.getText().toString())) {
                            Toast.makeText(SignUpActivity.this, "이미 가입되어있는 이메일입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (name.getText().toString().getBytes().length <= 0) {
                                Toast.makeText(SignUpActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else {
                                Random generator = new Random();
                                String randChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                String first = Character.toString(randChar.charAt(generator.nextInt(randChar.length())));
                                int rand = generator.nextInt(999999);
                                randResult = first + rand;
                                String title = "SL:O 회원가입 이메일 인증번호 입니다.";
                                String content = name.getText() + " 사용자님, <br>" +
                                        "귀하의 이메일 주소를 사용해 SL:O 회원가입 요청을 하였습니다. <br>" +
                                        name.getText() + "님의 인증번호는 다음과 같습니다. <br>" +
                                        "<h1>" + randResult + "</h1>" +
                                        "<br>회원가입을 요청하지 않았다면, 이 메일은 무시하셔도 됩니다.<br>" +
                                        "<b>누구에게도 이 인증번호를 알려주면 안됩니다.</b><br>" +
                                        "다른 궁금한 사항이 있을경우 하단의 메일로 연락 주세요.<br>" +
                                        "SL:O 팀";
                                new Sender(title, content, email.getText().toString());
                                Toast.makeText(SignUpActivity.this, "입력하신 메일로 인증번호가 발송되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        emailChButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText emailValidate = (EditText) findViewById(R.id.entEmailN);
                if(emailValidate.getText().toString().equals(randResult)){
                    EditText email= (EditText) findViewById(R.id.entEmail);
                    EditText name = (EditText) findViewById(R.id.entName);
                    emailSend.setEnabled(false);
                    emailChButton.setEnabled(false);
                    email.setEnabled(false);
                    emailValidate.setEnabled(false);
                    name.setEnabled(false);
                    Toast.makeText(SignUpActivity.this, "확인 되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUpActivity.this, "인증번호가 다릅니다 인증번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        final Button btnIdCheck = findViewById(R.id.btnIdCheck);
        btnIdCheck.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                ValidateId chkId = new ValidateId();
                EditText idField = findViewById(R.id.entId);
                if(idField.getText().toString().getBytes().length <= 0) {
                    Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if(chkId.IdCheck(idField.getText().toString())){
                        validateIdFlag = false;
                        Toast.makeText(SignUpActivity.this, "이미 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        validateIdFlag = true;
                        Toast.makeText(SignUpActivity.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.entId).setEnabled(false);
                        btnIdCheck.setEnabled(false);
                    }

                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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

                //하단의 코드 순서는 바뀌면 안됨.
                for(int i = 0; i < allField.size(); i++){
                    if(allField.get(i).getText().toString().getBytes().length <= 0){
                        Toast.makeText(SignUpActivity.this, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!entPw.getText().toString().equals(entPw_r.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //위에서 return이 일어나지 않을경우 (이상없음)
                SignUp register = new SignUp(infoOfUser);
                try {
                    if(validateIdFlag) {
                        if (register.execute()) {
                            //TODO : 회원가입 성공시 로직 입력
                        } else {
                            Toast.makeText(SignUpActivity.this, "회원가입 도중 오류가 발생하였습니다. 인터넷 연결상태 확인 및 나중에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
