package com.example.autobrary.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.example.autobrary.R;
import com.google.android.material.tabs.TabLayout;


public class Tab extends AppCompatActivity {
    Button tbtnLog;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tbtnLog = findViewById(R.id.tbtnLog);
        tabs = findViewById(R.id.tab);

        tabs.addTab(tabs.newTab().setText("도서검색"));
        tabs.addTab(tabs.newTab().setText("공지사항"));
        tabs.addTab(tabs.newTab().setText("희망도서신청"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        final TabAdapter tabadapter = new TabAdapter(getSupportFragmentManager(),3);
        viewpager.setAdapter(tabadapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
}
