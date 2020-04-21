package com.example.autobrary.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;


public class NoticeFragment extends Fragment {
    ListView noticeList;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notice, container, false);

        noticeList = root.findViewById(R.id.noticeList);
        final String[] notice = {"공지사항 1", "공지사항 2", "공지사항 3", "공지사항 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                notice
        );

        noticeList.setAdapter(adapter);

        return root;
    }
}
