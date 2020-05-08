package com.example.autobrary.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;


public class MypageFragment extends Fragment {
    Button bMore1, bMore2;
    LayoutInflater inf;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        getFragmentManager().beginTransaction().add(R.id.mypageF, new MypageFragment()).commit();

        bMore1 = root.findViewById(R.id.bMore1);
        bMore2 = root.findViewById(R.id.bMore2);

        bMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(R.id.mypageF, new Mypage2Fragment()).commit();
            }
        });

        bMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(R.id.mypageF, new Mypage3Fragment()).commit();
            }
        });

        return root;
    }
}
