package com.example.autobrary.wish;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.BAdapter;
import com.example.autobrary.main.BItem;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.mypage.MypageFragment;
import com.example.autobrary.notice.Notice2Fragment;
import com.example.autobrary.notice.NoticeFragment;


public class WishFragment extends Fragment {
    ListView listView1;
    BAdapter adapter;
    BItem item;
    Button wishbtn;
    private Rpage activity;
    private Context context;
    public static WishFragment newInstance() {
        return new  WishFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_wish, container, false);
        context = container.getContext();



        wishbtn = root.findViewById(R.id.wishbtn);
        wishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rpage)getActivity()).replaceFragment(Wish2Fragment.newInstance());
            }
        });

        return root;
    }
}
