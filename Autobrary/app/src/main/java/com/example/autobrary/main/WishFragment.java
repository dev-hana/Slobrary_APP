package com.example.autobrary.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;


public class WishFragment extends Fragment {
    ListView listView1;
    BAdapter adapter;
    BItem item;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_wish, container, false);

        adapter = new BAdapter();

        listView1 = root.findViewById(R.id.listview1);
        listView1.setAdapter(adapter);

        //(Drawable cover, String title, String auth, String pub, String wdate)
        adapter.addItem("책1권","작가1","출판사1","2020-05-01");

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (BItem) parent.getItemAtPosition(position);

                String title = item.getTitle();
                String auth = item.getAuth();
                String pub = item.getPublish();
                String wdate = item.getDate();
            }
        });

        return root;
    }
}
