package com.example.autobrary.reco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.BAdapter;
import com.example.autobrary.main.BItem;


public class RecoFragment extends Fragment {
    ListView listView1;
    BAdapter adapter;
    BItem item;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_reco, container, false);


        return root;
    }
}