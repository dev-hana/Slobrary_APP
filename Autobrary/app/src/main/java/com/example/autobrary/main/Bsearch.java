package com.example.autobrary.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.autobrary.R;

public class Bsearch extends AppCompatActivity {
    Spinner spin1, spin2, spin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsearch);

        final String[] blist1 = {"국내외분류", "분류1", "분류2"};
        final String[] blist2 = {"도서분류", "분류11", "분류22", "분류33"};
        final String[] blist3 = {"신착자료검색", "분류111", "분류222", "분류333"};

        spin1 = findViewById(R.id.spin1);
        spin2 = findViewById(R.id.spin2);
        spin3 = findViewById(R.id.spin3);

        ArrayAdapter<String> adap1, adap2, adap3;
        adap1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,blist1);
        adap2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,blist2);
        adap3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,blist3);

        spin1.setAdapter(adap1);
        spin2.setAdapter(adap2);
        spin3.setAdapter(adap3);
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

        switch(item.getItemId()){
            case R.id.bSearch:
                Intent intent = new Intent(Bsearch.this, Bsearch.class);
                startActivity(intent);
                break;
            case R.id.bNotice:

                break;
            case R.id.bRequest:

                break;
        }
        return true;
    }*/
}