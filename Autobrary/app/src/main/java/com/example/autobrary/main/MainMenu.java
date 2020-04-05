package com.example.autobrary.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.autobrary.R;

import java.util.BitSet;

public class MainMenu extends AppCompatActivity {
    Button btnLog;
    //LinearLayout baseLayout;
    //FrameLayout fLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));

        btnLog = findViewById(R.id.btnLog);
        //baseLayout = findViewById(R.id.baseLayout);
        //fLayout = findViewById(R.id.fLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflate = this.getMenuInflater();
        mInflate.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.bSearch:
//                Intent intent = new Intent(MainMenu.this, Bsearch.class);
//                startActivity(intent);
                //getFragmentManager().beginTransaction().replace(R.id.frags,new TabFrag_1()).commit();
                break;
            case R.id.bNotice:
                //baseLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.bRequest:
                //baseLayout.setBackgroundColor(Color.WHITE);
                break;
        }
        return true;
    }

}
