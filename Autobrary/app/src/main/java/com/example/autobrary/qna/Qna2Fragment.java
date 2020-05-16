package com.example.autobrary.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.notice.NoticeFragment;


public class Qna2Fragment extends Fragment {
    Button noLBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_qna2, container, false);

        noLBtn = root.findViewById(R.id.noLBtn);
        noLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goList = new Intent(getActivity(), NoticeFragment.class);
                startActivity(goList);
            }
        });
        return root;
    }
}
