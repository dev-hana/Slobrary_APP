package com.example.autobrary.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.autobrary.R;

public class MainMenu extends AppCompatActivity {
    Button btnLog;
    LinearLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnLog = findViewById(R.id.btnLog);
        baseLayout = findViewById(R.id.baseLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflate = this.getMenuInflater();
        mInflate.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.bSearch:
                baseLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.bNotice:
                baseLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.bRequest:
                baseLayout.setBackgroundColor(Color.WHITE);
                break;
        }
        return true;
        /*
        *근데 저렇게 하면 웹이랑 디자인이 매칭이 안되지 않오?
        * 음? 뭔 디자인? 그 뭐라해야지 그
        * 매인화면 보면 탭있는데 저기에 숨어 있으면 밑에 콘텐츠가 넣을게 없어지지 않나..??
        * ...?;.....? 음.. 기다려봐 해볼게 근데 내 컴에서 잘돌아 갔는데..ㅇㅂㅇ
        * 아 너꺼에서는 돼? ㅇㅇ
        * 음... 그아러 며아 아아 그 잠만 내꺼 잠시마나
        * 근데 아까 tabs 오류 어찌 해결함 ? 나 아무것도 안만졋ㄴㄴ데? 그냥
        * ㄱㄷㄷ
       */

        /*일단은 저렇게 해놓긴 했거든? 페이지 바뀌게 하면 되지않을까...나? 음.////..*/
    }
}
