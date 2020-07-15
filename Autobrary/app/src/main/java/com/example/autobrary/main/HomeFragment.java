package com.example.autobrary.main;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.session.SessionManager;

import java.util.Vector;

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
        LinearLayout forYouLayout;
        forYouLayout = rootView.findViewById(R.id.forYouLayout);
        if(SessionManager.getAttribute("login") == null){
            forYouLayout.setVisibility(View.GONE);
        }else{
            forYouLayout.setVisibility(View.VISIBLE);
        }
        Vector<ImageView> bestBookListObject = new Vector<>();
        bestBookListObject.add(rootView.findViewById(R.id.bestBook1));
        bestBookListObject.add(rootView.findViewById(R.id.bestBook2));
        bestBookListObject.add(rootView.findViewById(R.id.bestBook3));

        for(ImageView image : bestBookListObject){

        }

        return rootView;
    }

}
