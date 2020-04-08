package com.example.autobrary.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.autobrary.R;
import com.example.autobrary.database.MyDBHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {
    //SQLiteDatabase db;
    ListView noticeLV;

    public Frag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag2,container,false);

        noticeLV = v.findViewById(R.id.noticeLV);

//        MyDBHelper myHelper = new MyDBHelper(this);
//        String sql = "";
//        Cursor cursor = db.rawQuery(sql, null);
//        List<String> list = new ArrayList<>();



        return v;
    }
}
