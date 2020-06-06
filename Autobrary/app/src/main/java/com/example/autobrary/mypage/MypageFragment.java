package com.example.autobrary.mypage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.NoticeFragment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class MypageFragment extends Fragment {
    TextView name, email;
    ImageView profileImg;
    Rpage activity;
    TextView bMore1, bMore2;
    private Context context;
    private Mypage notice = new Mypage();

    public static MypageFragment newInstance() {
        return new MypageFragment();
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);
        View view = inflater.inflate(R.layout.fragment_mypage,null);


        context = container.getContext();

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        profileImg = root.findViewById(R.id.profileImg);

//        bMore1 = root.findViewById(R.id.bMore1);
//        bMore2 = root.findViewById(R.id.bMore2);

        try {
            MypageInfo info = new Mypage().execute();
            BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(info.profileImg);
            bucket.start();
            bucket.join();
            name.setText(info.getName());
            email.setText(info.getEmail());
            profileImg.setImageBitmap(bucket.getBitmap());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


//        bMore1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                ((Rpage)getActivity()).replaceFragment(Mypage2Fragment.newInstance(), bundle);
//            }
//        });
//
//        bMore2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                ((Rpage)getActivity()).replaceFragment(Mypage3Fragment.newInstance(), bundle);
//            }
//        });

        return root;
    }
}