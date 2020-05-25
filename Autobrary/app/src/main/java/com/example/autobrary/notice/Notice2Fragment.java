package com.example.autobrary.notice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.NoticeFragment;


public class Notice2Fragment extends Fragment {
    Rpage activity;
    Button noLBtn;
    public static Notice2Fragment newInstance() {
        return new Notice2Fragment();
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
        View root = inflater.inflate(R.layout.fragment_notice2, container, false);
        String title, id, owner, contents, date;
        title=id=owner=contents=date="";
        if(getArguments() != null) {
            title = getArguments().getString("title");
            id = getArguments().getString("id");
            owner = getArguments().getString("owner");
            contents = getArguments().getString("contents");
            date = getArguments().getString("date");
        }

        TextView titleView = root.findViewById(R.id.noTitle);
        TextView ownerView = root.findViewById(R.id.noAuth);
        TextView dateView = root.findViewById(R.id.noCal);
        TextView innerView = root.findViewById(R.id.content);

        titleView.setText(title);
        ownerView.setText(owner);
        dateView.setText(date);
        innerView.setText(Html.fromHtml(contents));

        noLBtn = root.findViewById(R.id.noLBtn);
        noLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rpage)getActivity()).replaceFragment(NoticeFragment.newInstance());
            }
        });
        return root;
    }
}
