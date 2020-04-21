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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.autobrary.R;
import com.example.autobrary.auth.LoginActivity;
import com.example.autobrary.auth.SignUpActivity;

public class Rpage extends AppCompatActivity {
    DrawerLayout drawer;
    TextView signIn, signUp;
    Button home, myPage, notice, info, reco, hope, qna, slo;

    RelativeLayout lay;
    View layout;
    LayoutInflater inflater;

//    Fragment fmain, fmy, fno;//, fin, fre, fho, fq, fs;
//
//    FragmentManager fragman;
//    FragmentTransaction tran;



//    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpage);
        ImageView open = findViewById(R.id.open);

        home = findViewById(R.id.mHome);
        myPage = findViewById(R.id.mMypage);
        notice = findViewById(R.id.mNotice);
        info = findViewById(R.id.mInfo);
        reco = findViewById(R.id.mReco);
        hope = findViewById(R.id.mHope);
        qna = findViewById(R.id.mQnA);
        slo = findViewById(R.id.mSlo);

        lay = findViewById(R.id.lay);

//        fmain = new HomeFragment();
//        fmy = new MypageFragment();
//        fno = new NoticeFragment();
//
//        fragman = getSupportFragmentManager();
//        tran = fragman.beginTransaction();
//
//        tran.add(R.id.lay, fmain);
//        tran.commit();

        open.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout) findViewById(R.id.drawer);
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
                Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_LONG).show();
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.fragment_mypage, null);
                lay.removeAllViews();
                lay.addView(layout);
            }
        });

//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_Mypage, R.id.nav_Notice, R.id.nav_Info, R.id.nav_Reco, R.id.nav_Hope, R.id.nav_QnA, R.id.nav_Slo)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }
}