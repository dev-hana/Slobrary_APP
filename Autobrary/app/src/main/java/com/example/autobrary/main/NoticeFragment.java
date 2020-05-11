package com.example.autobrary.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.autobrary.R;
import com.example.autobrary.notice.Notice;
import com.example.autobrary.notice.NoticeAdapter;
import com.example.autobrary.notice.NoticeInfo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Vector;


public class NoticeFragment extends ListFragment {
    NoticeAdapter adapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_notice);

        adapter = new NoticeAdapter() ;
        setListAdapter(adapter) ;
        try {
            this.Initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void Initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        Notice notice = new Notice();
        Vector<NoticeInfo> getNotice = notice.execute();
        for (NoticeInfo info : getNotice){
            adapter.addItem(info);
        }
    }
}
