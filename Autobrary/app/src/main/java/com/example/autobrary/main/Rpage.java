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
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;

import com.example.autobrary.R;
import com.example.autobrary.auth.LoginActivity;
import com.example.autobrary.auth.SignUpActivity;
import com.example.autobrary.mail.Sender;

public class Rpage extends AppCompatActivity {
    DrawerLayout drawer;
    TextView signIn, signUp;
    Button home, myPage, notice, info, reco, wish, qna, slo;

    RelativeLayout lay;
    View layout;
    LayoutInflater inflater;

    ImageView open;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpage);
        open = findViewById(R.id.open);

        home = findViewById(R.id.mHome);
        myPage = findViewById(R.id.mMypage);
        notice = findViewById(R.id.mNotice);
        info = findViewById(R.id.mInfo);
        reco = findViewById(R.id.mReco);
        wish = findViewById(R.id.mWish);
        qna = findViewById(R.id.mQnA);
        slo = findViewById(R.id.mSlo);

        lay = findViewById(R.id.lay);

        drawer = (DrawerLayout) findViewById(R.id.drawer);


        open.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);

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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_home, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_mypage, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "공지사항", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_notice, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                new Sender("test","ttteestt","dev_sang@naver.com");
                Toast.makeText(getApplicationContext(), "이용안내", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_info, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "추천도서", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_reco, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "도서 신청", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_wish, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "QnA", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_qna, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

        slo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }
                Toast.makeText(getApplicationContext(), "SLO", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_slo, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });
    }
}