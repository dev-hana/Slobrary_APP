package com.example.autobrary.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.autobrary.R;
import com.example.autobrary.info.wish.WishInfo;

import java.util.ArrayList;

public class BAdapter extends BaseAdapter {
    private ArrayList<WishInfo> wishInfos = new ArrayList<WishInfo>();

    public int getCount(){
        return wishInfos.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    public WishInfo getItem(int position){
        return wishInfos.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    public long getItemId(int position){
        return position;
    }

    public BAdapter(){

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_wish_item, parent, false);
        }

        TextView bTitle = convertView.findViewById(R.id.bTitle);
        TextView bAuth = convertView.findViewById(R.id.bAuth);
        TextView bPub = convertView.findViewById(R.id.bPub);
        TextView bDate = convertView.findViewById(R.id.bDate);
        TextView status = convertView.findViewById(R.id.applyStatus);

        WishInfo wishInfo = wishInfos.get(position);

        bTitle.setText(wishInfo.getBookTitle());
        bAuth.setText(wishInfo.getBookAuthor());
        bPub.setText(wishInfo.getBookPublish());
        bDate.setText(wishInfo.getBookDate());
        status.setText(wishInfo.getStatus());
        /*위젯에 대한 이벤트리스너를 지정하려면 여기*/
        return convertView;
    }

    public void addItem(String user, String title, String auth, String pub, String wdate, String applyStatus){
        WishInfo wishInfo = new WishInfo(user, title, auth, pub);
        wishInfo.setUser(user);
        wishInfo.setBookTitle(title);
        wishInfo.setBookAuthor(auth);
        wishInfo.setBookPublish(pub);
        wishInfo.setBookDate(wdate);
        wishInfo.setStatus(applyStatus);

        wishInfos.add(wishInfo);
    }

    public void addItem(WishInfo info) {
        wishInfos.add(info);
    }
    public void clearItem() {
        wishInfos.clear();
    }
}
