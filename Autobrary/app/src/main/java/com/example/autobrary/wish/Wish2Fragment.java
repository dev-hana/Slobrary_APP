package com.example.autobrary.wish;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.mypage.BookInfo;
import com.example.autobrary.session.SessionManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Wish2Fragment extends Fragment {
    Rpage activity;
    private Context context;
    BootstrapEditText bookTitle;
    BootstrapEditText bookAuthor;
    BootstrapEditText publish;
    public static Wish2Fragment newInstance() {
        return new Wish2Fragment();
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
        final View root = inflater.inflate(R.layout.fragment_wish2, container, false);
        context = container.getContext();
        bookTitle = root.findViewById(R.id.book_name);
        bookAuthor = root.findViewById(R.id.book_author);
        publish = root.findViewById(R.id.publish);
        Button applyButton = root.findViewById(R.id.wishApply);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishInfo info = new WishInfo(SessionManager.getAttribute("login"),bookTitle.getText().toString(),bookAuthor.getText().toString(),publish.getText().toString());
                try {
                    if(new WishApply(info).execute()){
                        Toast.makeText(context, "정상적으로 처리되었습니다.", Toast.LENGTH_LONG).show();
                        ((Rpage)getActivity()).replaceFragment(WishFragment.newInstance());
                    }else{
                        Toast.makeText(context, "인터넷 연결이 불안정 하거나, 알수없는 오류가 발생하혔습니다.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}
