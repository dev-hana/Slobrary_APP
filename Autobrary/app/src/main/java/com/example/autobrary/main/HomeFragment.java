package com.example.autobrary.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;

public class HomeFragment extends Fragment {
    Rpage activity;

    @Override
    public void onAttach(Context context) { //플래그먼트 호출시
        super.onAttach(context);
        activity = (Rpage) getActivity();
    }

    @Override
    public void onDetach() { //플래그먼트 Detach시
        super.onDetach();
        activity = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home , container, false); //레이아웃 참조

        return rootView;
    }
}
