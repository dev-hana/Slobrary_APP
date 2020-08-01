package com.example.autobrary.book;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;


public class BookDetailFragment  extends Fragment {
    private Rpage activity;
    private Context context;
    public static BookDetailFragment newInstance() {
        return new BookDetailFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_book_detail, container, false);
        context = container.getContext();
        
        return rootView;
    }
}
