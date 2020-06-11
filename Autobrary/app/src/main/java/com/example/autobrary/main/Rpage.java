package com.example.autobrary.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.autobrary.common.LoadingFragment;
import com.example.autobrary.mypage.MypageFragment;
import com.example.autobrary.notice.Notice2Fragment;
import com.example.autobrary.notice.NoticeFragment;
import com.example.autobrary.qna.QnaFragment;
import com.example.autobrary.reco.RecoFragment;
import com.example.autobrary.session.SessionManager;
import com.example.autobrary.wish.WishFragment;
import com.google.android.material.snackbar.Snackbar;

public class Rpage extends AppCompatActivity {
    DrawerLayout drawer;
    TextView signIn, signUp, name, title, logout;
    Button home, myPage, notice, info, reco, wish, qna, slo;
    RelativeLayout lay;
    View layout;
    LayoutInflater inflater;

    ImageView open;

    NoticeFragment noticeFrag;
    Notice2Fragment notice2Frag;
    HomeFragment MainFrag;
    MypageFragment mypageFrag;
    WishFragment wishFrag;
    SloFragment sloFrag;
    QnaFragment qnaFrag;
    InfoFragment infoFrag;
    RecoFragment recoFrag;
    LoadingFragment loadFrag;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    Fragment fragment;
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
        if(fragment.equals(MainFrag)) {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressedTime;

            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
            {
                android.os.Process.killProcess(android.os.Process.myPid());
               // super.onBackPressed();
            }
            else
            {
                backPressedTime = tempTime;
                Snackbar.make(lay, "한번 더 뒤로가기 누르면 확 꺼버릴꺼에요.", Snackbar.LENGTH_LONG).show();
                // Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 꺼버린다.", Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onBackPressed();
        }
    }


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
        notice2Frag = new Notice2Fragment();
        MainFrag = new HomeFragment();
        mypageFrag = new MypageFragment();
        wishFrag = new WishFragment();
        sloFrag = new SloFragment();
        qnaFrag = new QnaFragment();
        infoFrag = new InfoFragment();
        recoFrag = new RecoFragment();
        loadFrag = new LoadingFragment();
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
                replaceFragment(MainFrag);
                Snackbar.make(lay, "로그아웃 되었습니다.", Snackbar.LENGTH_LONG).show();
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
                replaceFragment(MainFrag);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(MainFrag)) {
                    replaceFragment(MainFrag);
                }
            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(mypageFrag)) {
                if(sessionCheck()){
                    replaceFragment(mypageFrag);
                }
                }
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(fragment instanceof Notice2Fragment) {
                    onBackPressed();
                }else if(!fragment.equals(noticeFrag)) {
                    replaceFragment(noticeFrag);
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(infoFrag)) {
                    replaceFragment(infoFrag);
                }
            }
        });

        reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(recoFrag)) {
                    replaceFragment(recoFrag);
                }
            }
        });

        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(wishFrag)) {
                    if(sessionCheck()){
                        replaceFragment(wishFrag);
                    }
                }

            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(qnaFrag)) {
                    replaceFragment(qnaFrag);
                }
            }
        });

        slo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDrawer();
                fragment = getSupportFragmentManager().findFragmentById(R.id.lay);
                if(!fragment.equals(sloFrag)) {
                    replaceFragment(sloFrag);
                }
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
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.lay, fragment);
        fragmentTransaction.commit();
    }
    public void replaceFragment(Fragment fragment, Bundle bundle) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.lay, fragment);
        fragmentTransaction.commit();
    }
    public void secondReplaceFragment(Fragment fragment, Bundle bundle) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.lay, fragment);
        fragmentTransaction.commit();
    }
    private void controlDrawer(){
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
        }
    }
}