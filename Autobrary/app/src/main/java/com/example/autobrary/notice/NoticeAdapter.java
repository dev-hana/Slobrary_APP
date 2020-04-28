package com.example.autobrary.notice;

import android.content.Context;

import java.util.List;

public class NoticeAdapter {
    private Context context;
    private List<NoticeInfo> noticeList;
    public NoticeAdapter(Context context, List<NoticeInfo> noticeList){
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
