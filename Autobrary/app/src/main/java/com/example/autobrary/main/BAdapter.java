package com.example.autobrary.main;

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

public class BAdapter extends BaseAdapter {
    private ArrayList<BItem> bItems = new ArrayList<BItem>();

    public int getCount(){
        return bItems.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    public BItem getItem(int position){
        return bItems.get(position);
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
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        //ImageView bCover = convertView.findViewById(R.id.bCover);
        TextView bTitle = convertView.findViewById(R.id.bTitle);
        TextView bAuth = convertView.findViewById(R.id.bAuth);
        TextView bPub = convertView.findViewById(R.id.bPub);
        TextView bDate = convertView.findViewById(R.id.bDate);

        BItem bItem = bItems.get(position);

        //bCover.setImageDrawable(bItem.getCover());
        bTitle.setText(bItem.getTitle());
        bAuth.setText(bItem.getAuth());
        bPub.setText(bItem.getPublish());
        bDate.setText(bItem.getDate());

        /*위젯에 대한 이벤트리스너를 지정하려면 여기*/
        return convertView;
    }

    //public void addItem(Drawable cover, String title, String auth, String pub, String wdate){
    public void addItem(String title, String auth, String pub, String wdate){
        BItem bItem = new BItem();

        //bItem.setCover(cover);
        bItem.setTitle(title);
        bItem.setAuth(auth);
        bItem.setPublish(pub);
        bItem.setDate(wdate);

        bItems.add(bItem);
    }
}
