package com.example.autobrary.auth;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.autobrary.R;

import java.util.List;

public class NoticeAdapter {
    private Context context;
    private List<Notice> noticeList;
    public NoticeAdapter(Context context, List<Notice> noticeList){
        this.context = context;
        this.noticeList = noticeList;
    }

    public int getCount(){ //리스트뷰 총 갯수
        return noticeList.size();
    }

    public Object getItem(int position){ //해당 위치의 값을 리스트뷰에 뿌려줌
        return noticeList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

}
