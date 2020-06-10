package com.example.autobrary.common;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.Notice2Fragment;

public class LoadingFragment extends Fragment {

    Rpage activity;
    private Context context;
    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_loading , container, false); //레이아웃 참조
        context = container.getContext();
        ImageView rabbit = (ImageView) rootView.findViewById(R.id.gif_image);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(rabbit);
        Glide.with(context).load(R.drawable.loading).into(gifImage);
        return rootView;
    }

}
