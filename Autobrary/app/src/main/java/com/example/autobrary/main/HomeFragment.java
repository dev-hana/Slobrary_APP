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
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.BookInfo;
import com.example.autobrary.main.getdata.GetBestBook;
import com.example.autobrary.main.getdata.GetNewBook;
import com.example.autobrary.session.SessionManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

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

        Vector<ImageView> newBookListObject = new Vector<>();
        newBookListObject.add(rootView.findViewById(R.id.newBook1));
        newBookListObject.add(rootView.findViewById(R.id.newBook2));
        newBookListObject.add(rootView.findViewById(R.id.newBook3));

        Vector<BookInfo> getData1 = null;
        BucketConnector bucket1 = null;

        Vector<BookInfo> getData2 = null;
        BucketConnector bucket2 = null;


        try {
            getData1 = new GetBestBook().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            getData2 = new GetNewBook().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bestBookListObject.size(); i++){
                bucket1 = new BucketConnector();
                bucket1.setObjectName(getData1.get(i).getImage());
            try {
                bucket1.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bestBookListObject.get(i).setImageBitmap(bucket1.getBitmap());
        }

        for(int i = 0; i < newBookListObject.size(); i++){
            bucket2 = new BucketConnector();
            bucket2.setObjectName(getData2.get(i).getImage());
            try {
                bucket2.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newBookListObject.get(i).setImageBitmap(bucket2.getBitmap());
        }
        return rootView;
    }

}
