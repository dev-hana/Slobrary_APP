package com.example.autobrary.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.autobrary.R;
import com.example.autobrary.auth.validate.ValidateEmail;
import com.example.autobrary.auth.validate.ValidateId;
import com.example.autobrary.auth.getdata.SignUp;
import com.example.autobrary.auth.info.SignUpInfo;
import com.example.autobrary.mail.Sender;
import com.example.autobrary.main.Rpage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private RadioButton g_man, g_woman;
    private RadioGroup entGender;
    private boolean validateIdFlag = false;
    private boolean validateEmailFlag = false;
    private String randResult;
    private String gender = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        BootstrapButton signUpBt = (BootstrapButton) findViewById(R.id.signUp);
        final BootstrapButton emailSend = (BootstrapButton) findViewById(R.id.emailSend);
        final BootstrapButton emailChButton = (BootstrapButton) findViewById(R.id.emailCheck);

        emailSend.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                BootstrapEditText email= (BootstrapEditText) findViewById(R.id.entEmail);
                BootstrapEditText name = (BootstrapEditText) findViewById(R.id.entName);
                try {
                    if(email.getText().toString().getBytes().length <= 0){
                        Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }else if(isValidEmailId(email.getText().toString())) {
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
                                email.setEnabled(false);
                                Toast.makeText(SignUpActivity.this, "입력하신 메일로 인증번호가 발송되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(SignUpActivity.this, "이메일형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
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
                BootstrapEditText emailValidate = (BootstrapEditText) findViewById(R.id.entEmailN);
                if(emailValidate.getText().toString().equals(randResult)){
                    BootstrapEditText email= (BootstrapEditText) findViewById(R.id.entEmail);
                    BootstrapEditText name = (BootstrapEditText) findViewById(R.id.entName);
                    emailSend.setEnabled(false);
                    emailChButton.setEnabled(false);
                    email.setEnabled(false);
                    emailValidate.setEnabled(false);
                    name.setEnabled(false);
                    validateEmailFlag = true;
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
                    gender = "M";
                }
                else if(i == R.id.g_woman){
                    gender = "F";
                }
            }
        };
        final BootstrapButton btnIdCheck = findViewById(R.id.btnIdCheck);
        btnIdCheck.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                ValidateId chkId = new ValidateId();
                BootstrapEditText idField = findViewById(R.id.entId);
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
                Vector<BootstrapEditText> allField = new Vector<BootstrapEditText>();
                SignUpInfo infoOfUser = new SignUpInfo();

                BootstrapEditText id = (BootstrapEditText) findViewById(R.id.entId);
                BootstrapEditText entPw = (BootstrapEditText) findViewById(R.id.entPw);
                BootstrapEditText entPw_r = (BootstrapEditText) findViewById(R.id.entPw_re);
                BootstrapEditText name = (BootstrapEditText) findViewById(R.id.entName);
                BootstrapEditText birth = (BootstrapEditText) findViewById(R.id.entBirth);
                BootstrapEditText phone = (BootstrapEditText) findViewById(R.id.entPhone);
                BootstrapEditText addr= (BootstrapEditText) findViewById(R.id.entAddr);
                BootstrapEditText email= (BootstrapEditText) findViewById(R.id.entEmail);

                infoOfUser.setMem_birth(birth.getText().toString());
                infoOfUser.setMem_gender(gender);
                infoOfUser.setMem_id(id.getText().toString());
                infoOfUser.setMem_pw(entPw.getText().toString());
                try {
                    infoOfUser.setMem_name(name.getText().toString());
                    infoOfUser.setMem_address(addr.getText().toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                infoOfUser.setMem_phone(phone.getText().toString());
                infoOfUser.setMem_email(email.getText().toString());
                // Log.i("pass", infoOfUser.getMem_birth() + infoOfUser.getMem_gender() + infoOfUser.getMem_id() + infoOfUser.getMem_pw() + infoOfUser.getMem_name() + infoOfUser.getMem_phone() + infoOfUser.getMem_address() + infoOfUser.getMem_email());
                // Log.i("PBKDF2 RESULT", infoOfUser.getMem_pw());
                // 빈칸 무결성 체크

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
                allField.add(birth);

                //하단의 코드 순서는 바뀌면 안됨.
                for(int i = 0; i < allField.size(); i++){
                    if(allField.get(i).getText().toString().getBytes().length <= 0){
                        Toast.makeText(SignUpActivity.this, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!isValidPhoneNv(phone.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "핸드폰번호가 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!isValidBirth(birth.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "생년월일이 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!entPw.getText().toString().equals(entPw_r.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isValidPassword(entPw.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "비밀번호는 숫자, 대/소문자 포함 8글자 이상 입력해야합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!validateEmailFlag) {
                    Toast.makeText(SignUpActivity.this, "이메일을 인증해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //위에서 return이 일어나지 않을경우 (이상없음)
                SignUp register = new SignUp(infoOfUser);
                try {

                    if(validateIdFlag) {
                        if (register.execute()) {
                            Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent mainAct = new Intent(getApplicationContext(), Rpage.class);
                            mainAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            mainAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(mainAct);
                        } else {
                            Toast.makeText(SignUpActivity.this, "회원가입 도중 오류가 발생하였습니다. 인터넷 연결상태 확인 및 나중에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    private boolean isValidPassword(String password){
        return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(password).matches();
    }
    private  boolean isValidPhoneNv(String number){
        return Pattern.compile("^\\d{3}\\d{4}\\d{4}$").matcher(number).matches();
    }
    private  boolean isValidBirth(String number){
        return Pattern.compile("^\\d{8}$").matcher(number).matches();
    }
}
