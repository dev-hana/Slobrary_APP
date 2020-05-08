package com.example.autobrary.notice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autobrary.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends BaseAdapter {
    ArrayList<NoticeInfo> sample;
    private ArrayList<NoticeInfo> listViewItemList = new ArrayList<NoticeInfo>() ;

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public NoticeInfo getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_notice_item, parent, false);
        }
        TextView contentNv = (TextView)convertView.findViewById(R.id.contentId);
        TextView notiveTitle = (TextView)convertView.findViewById(R.id.contentTitle);
        TextView noticeData = (TextView)convertView.findViewById(R.id.contentDate);

        contentNv.setText(sample.get(position).getId());
        notiveTitle.setText(sample.get(position).getTitle());
        noticeData.setText(sample.get(position).getDate());
        return convertView;
    }
    public void addItem(NoticeInfo info) {
        listViewItemList.add(info);
    }
}
