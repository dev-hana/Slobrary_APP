package com.example.autobrary.notice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class NoticeFragment extends Fragment {
    NoticeAdapter adapter ;
    Rpage activity;
    ListView listView;
    public static NoticeFragment newInstance() {
        return new NoticeFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notice , container, false);
        adapter = new NoticeAdapter();
        listView = (ListView) rootView.findViewById(R.id.noticeList);
        listView.setAdapter(adapter);
        try {
            Initialize();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        return rootView;
    }

    public void Initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        Notice notice = new Notice();
        Vector<NoticeInfo> getNotice = notice.execute();
        for (NoticeInfo info : getNotice){
            adapter.addItem(info);
        }
    }

}
