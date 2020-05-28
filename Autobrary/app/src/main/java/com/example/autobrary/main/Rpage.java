package com.example.autobrary.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.autobrary.R;
import com.example.autobrary.auth.LoginActivity;
import com.example.autobrary.auth.SignUpActivity;
import com.example.autobrary.mypage.MypageFragment;
import com.example.autobrary.notice.NoticeFragment;
import com.example.autobrary.qna.QnaFragment;
import com.example.autobrary.reco.RecoFragment;
import com.example.autobrary.session.SessionManager;
import com.example.autobrary.wish.WishFragment;

public class Rpage extends AppCompatActivity {
    DrawerLayout drawer;
    TextView signIn, signUp, name, title, logout;
    Button home, myPage, notice, info, reco, wish, qna, slo;
    RelativeLayout lay;
    View layout;
    LayoutInflater inflater;

    ImageView open;

    NoticeFragment noticeFrag;
    HomeFragment MainFrag;
    MypageFragment mypageFrag;
    WishFragment wishFrag;
    SloFragment sloFrag;
    QnaFragment qnaFrag;
    InfoFragment infoFrag;
    RecoFragment recoFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpage);
        open = findViewById(R.id.open);
        name = findViewById(R.id.mName);
        home = findViewById(R.id.mHome);
        myPage = findViewById(R.id.mMypage);
        notice = findViewById(R.id.mNotice);
        info = findViewById(R.id.mInfo);
        reco = findViewById(R.id.mReco);
        wish = findViewById(R.id.mWish);
        qna = findViewById(R.id.mQnA);
        slo = findViewById(R.id.mSlo);
        title = findViewById(R.id.title);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        logout = findViewById(R.id.logout);

        lay = findViewById(R.id.lay);
        noticeFrag = new NoticeFragment();
        MainFrag = new HomeFragment();
        mypageFrag = new MypageFragment();
        wishFrag = new WishFragment();
        sloFrag = new SloFragment();
        qnaFrag = new QnaFragment();
        infoFrag = new InfoFragment();
        recoFrag = new RecoFragment();
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        if(SessionManager.getAttribute("login") == null){
            name.setText(R.string.appPrettyName);
            signIn.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }else{
            signIn.setVisibility(View.GONE);
            signUp.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            name.setText(SessionManager.getAttribute("login") + "님 안녕하세요.");
        }

        //************* 기본 레이아웃 설정 *****************//
        lay.removeAllViews(); //보이는 레이아웃 초기화
        getSupportFragmentManager().beginTransaction().replace(R.id.lay, MainFrag).commit(); //기본화면 설정
        ///////////////////////////////////////////////////////

        open.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                SessionManager.invalidSession();
                name.setText(R.string.appPrettyName);
                signIn.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
                logout.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, MainFrag).commit();
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginAct = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginAct);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signAct = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signAct);
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, MainFrag).commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, MainFrag).commit();
            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                if(sessionCheck()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.lay, mypageFrag).commit();
                }
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, noticeFrag).commit();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, infoFrag).commit();
            }
        });

        reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, recoFrag).commit();
            }
        });

        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                if(sessionCheck()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.lay, wishFrag).commit();
                }
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, qnaFrag).commit();
            }
        });

        slo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.lay, sloFrag).commit();
            }
        });
    }
    private boolean sessionCheck(){
        boolean result = false;
        if(SessionManager.getSessionId().equals("")){
            Toast.makeText(getApplicationContext(), "로그인후 이용해주세요.", Toast.LENGTH_LONG).show();
        }else if (SessionManager.validSession(SessionManager.getSessionId())){
            result = true;
        }else{
            Toast.makeText(getApplicationContext(), "로그인 세션이 만료되었습니다. 다시 로그인 해주세요.", Toast.LENGTH_LONG).show();
        }
        return result;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lay, fragment).commit();
    }
    public void replaceFragment(Fragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.lay, fragment).commit();
    }
    private void controlDrawer(){
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
        }
    }
}