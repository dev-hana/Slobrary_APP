package com.example.autobrary.notice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.HomeFragment;
import com.example.autobrary.main.MainMenu;
import com.example.autobrary.main.Rpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class NoticeFragment extends Fragment {
    NoticeAdapter adapter ;
    Rpage activity;
    ListView listView;
    Vector<NoticeInfo> getNotice;
    private Context context;
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
        context = container.getContext();
        adapter = new NoticeAdapter();
        listView = (ListView) rootView.findViewById(R.id.noticeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title", getNotice.get(position).getTitle());
                bundle.putString("id", getNotice.get(position).getId());
                bundle.putString("owner", getNotice.get(position).getName());
                bundle.putString("contents", getNotice.get(position).getContents());
                bundle.putString("date", getNotice.get(position).getDate());
                ((Rpage)getActivity()).replaceFragment(Notice2Fragment.newInstance(), bundle);
            }
        });
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
        getNotice = notice.execute();
        if(getNotice.isEmpty()){
            Toast.makeText(context, "공지사항이 없거나 인터넷연결이 불안정합니다.", Toast.LENGTH_LONG).show();
            return;
        }
        for (NoticeInfo info : getNotice){
            adapter.addItem(info);
        }
    }

}
