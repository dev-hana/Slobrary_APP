package com.example.autobrary.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;


public class Mypage3Fragment extends Fragment {
    Rpage activity;
    Button mpBtn;

    public static Mypage2Fragment newInstance() {
        return new Mypage2Fragment();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Rpage) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mypage3, container, false);

        mpBtn = root.findViewById(R.id.mpBtn);

        mpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                ((Rpage)getActivity()).replaceFragment(MypageFragment.newInstance());
            }
        });

        return root;
    }
}