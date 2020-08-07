package com.example.autobrary.wish;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.wish.WishInfo;
import com.example.autobrary.main.BAdapter;
import com.example.autobrary.main.Rpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

public class WishFragment extends Fragment {
    ListView listView;
    BAdapter adapter;
    private Vector<WishInfo> getWish;
    private WishList wishList = new WishList();
    // private BItem BItem;
    BootstrapButton wishbtn;
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

        //////////// 리스트 연결 /////////

        listView = root.findViewById(R.id.wishList);
        adapter = new BAdapter();
        listView.setAdapter(adapter);

        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        ///////////////////////////////////////

        wishbtn = root.findViewById(R.id.wishbtn);
        wishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rpage)getActivity()).replaceFragment(Wish2Fragment.newInstance());
            }
        });

        return root;
    }
    // OnCreateView 끝

    private void initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (getWish()) {
            for (WishInfo info : getWish) {
                adapter.addItem(info);
            }
            adapter.notifyDataSetChanged();
        }
    }

    private boolean getWish() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        adapter.clearItem();
        getWish = wishList.execute();
        if (getWish.isEmpty()) {
            Toast.makeText(context, "신청도서가 없거나 인터넷연결이 불안정합니다.", Toast.LENGTH_LONG).show();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}

