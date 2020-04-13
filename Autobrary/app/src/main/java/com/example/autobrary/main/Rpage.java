package com.example.autobrary.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.autobrary.R;

public class Rpage extends AppCompatActivity {
    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpage);
        ImageView open = findViewById(R.id.open);

        open.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout) findViewById(R.id.drawer);
                if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });
    }
}
