package com.example.autobrary.wish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.autobrary.R;
import com.example.autobrary.info.wish.WishInfo;

import java.util.ArrayList;

public class WishAdapter extends BaseAdapter {
    private ArrayList<WishInfo> listViewItemList = new ArrayList<WishInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public WishInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_wish_item, parent, false);
        }
        TextView wishTitle = (TextView)convertView.findViewById(R.id.bTitle);
        TextView wishAuthor = (TextView)convertView.findViewById(R.id.bAuth);
        TextView wishPublish = (TextView)convertView.findViewById(R.id.bPub);
        TextView wishDate = (TextView)convertView.findViewById(R.id.bDate);
        TextView applyStatus = (TextView)convertView.findViewById(R.id.applyStatus);

        applyStatus.setText(listViewItemList.get(position).getStatus());
        wishTitle.setText(listViewItemList.get(position).getBookTitle());
        wishAuthor.setText(listViewItemList.get(position).getBookAuthor());
        wishPublish.setText(listViewItemList.get(position).getBookPublish());
        wishDate.setText(listViewItemList.get(position).getBookDate());
        return convertView;
    }
    public void addItem(WishInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
