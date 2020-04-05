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
    }
}
