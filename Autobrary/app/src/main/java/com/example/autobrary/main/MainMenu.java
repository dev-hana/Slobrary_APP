package com.example.autobrary.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.autobrary.R;
import com.example.autobrary.auth.LoginActivity;

public class MainMenu extends AppCompatActivity {
    Button btnLog;
    Fragment frag1, frag2, frag3;
    FrameLayout fram;

    FragmentManager fragman;
    FragmentTransaction tran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //메뉴 바 색 지정

        fragman = getSupportFragmentManager();
        tran = fragman.beginTransaction();

        btnLog = findViewById(R.id.btnLog);
        frag1 = new Frag1(); //프래그먼트 객체 생성
        frag2 = new Frag2();
        frag3 = new Frag3();
        fram = findViewById(R.id.view);

        tran.add(R.id.view, frag1);
        //tran.replace(R.id.view, frag1);
        tran.commit();
        Button loginBt = (Button) findViewById(R.id.btnLog);
        loginBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginAct = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginAct);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflate = this.getMenuInflater();
        mInflate.inflate(R.menu.menu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        tran = fragman.beginTransaction();
        switch(item.getItemId()){
            case R.id.bSearch:
                tran.replace(R.id.view, frag1);
                tran.commit();
                break;
            case R.id.bNotice:
                Toast.makeText(getApplicationContext(), "공지사항", Toast.LENGTH_LONG).show();
                break;
            case R.id.bRequest:
                tran.replace(R.id.view, frag3);
                tran.commit();
                break;
        }
        return true;
    }*/

}
