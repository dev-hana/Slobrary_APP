package com.example.autobrary.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;

import java.util.ArrayList;

public class MpBookAdapter extends BaseAdapter {
    private ArrayList<MpBookInfo> listViewItemList = new ArrayList<MpBookInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MpBookInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_mpbook_item, parent, false);
        }
        TextView mpbookImage = (TextView) convertView.findViewById(R.id.bCover); // String으로 바꾸면 됨. // MpBookInfo도 String으로 바꾸기
        TextView mpbookTitle = (TextView)convertView.findViewById(R.id.bTitle);
        TextView mpbookAuthor = (TextView)convertView.findViewById(R.id.bAuth);

        mpbookImage.setText(listViewItemList.get(position).getImage()); // String으로 바꾸면 됨.
        mpbookTitle.setText(listViewItemList.get(position).getName());
        mpbookAuthor.setText(listViewItemList.get(position).getAuthor());
        return convertView;
    }
    public void addItem(MpBookInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
