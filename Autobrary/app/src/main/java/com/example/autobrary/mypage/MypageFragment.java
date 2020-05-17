package com.example.autobrary.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.NoticeFragment;


public class MypageFragment extends Fragment {
    TextView bMore1, bMore2;
    RelativeLayout lay;
    View layout;
    LayoutInflater inf;
    Rpage activity;
    public static MypageFragment newInstance() {
        return new MypageFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mypage , container, false);

        lay = root.findViewById(R.id.lay);

//        getFragmentManager().beginTransaction().add(R.id.mypageF, new MypageFragment()).commit();
//
//        bMore1 = root.findViewById(R.id.bMore1);
//        bMore2 = root.findViewById(R.id.bMore2);
//
//        bMore1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().add(R.id.mypageF, new Mypage2Fragment()).commit();
//            }
//        });
//        bMore2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().add(R.id.mypageF, new Mypage3Fragment()).commit();
//            }
//        });

        return root;
    }
}
