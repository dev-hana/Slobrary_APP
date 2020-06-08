package com.example.autobrary.notice;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.autobrary.R;
import com.example.autobrary.main.Rpage;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends BaseAdapter {
    private ArrayList<NoticeInfo> listViewItemList = new ArrayList<NoticeInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public NoticeInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_notice_item, parent, false);
        }
        TextView noticeTitle = (TextView)convertView.findViewById(R.id.contentTitle);
        TextView noticeData = (TextView)convertView.findViewById(R.id.contentDate);

        noticeTitle.setText(listViewItemList.get(position).getTitle());
        noticeData.setText(listViewItemList.get(position).getDate());
        return convertView;
    }
    public void addItem(NoticeInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
