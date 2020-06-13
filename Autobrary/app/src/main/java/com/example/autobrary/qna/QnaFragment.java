package com.example.autobrary.qna;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.main.SloFragment;


public class QnaFragment extends Fragment {
    private Rpage activity;
    private Context context;
    public static QnaFragment newInstance() {
        return new  QnaFragment();
    }
    LinearLayout qna1, qna2, qna3;
    LinearLayout answer1, answer2, answer3;

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
        View root = inflater.inflate(R.layout.fragment_qna, container, false);

        qna1 = root.findViewById(R.id.qna1);
        answer1 = root.findViewById(R.id.answer1);
        qna2 = root.findViewById(R.id.qna2);
        answer2 = root.findViewById(R.id.answer2);
        qna3 = root.findViewById(R.id.qna3);
        answer3 = root.findViewById(R.id.answer3);


        qna1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setVisibility(answer1.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        qna2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer2.setVisibility(answer2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        qna3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer3.setVisibility(answer3.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        return root;
    }
}
