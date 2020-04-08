package com.example.autobrary.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autobrary.R;

import java.util.ArrayList;

public class BAdapter {
    private ArrayList<BItem> bItems = new ArrayList<>();

    public int getCount(){
        return bItems.size();
    }

    public BItem getItem(int position){
        return bItems.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        ImageView bImage = convertView.findViewById(R.id.bImage);
        TextView bTitle = convertView.findViewById(R.id.bTitle);
        TextView bAuth = convertView.findViewById(R.id.bAuth);
        TextView bPos = convertView.findViewById(R.id.bPos);
        TextView bLoan = convertView.findViewById(R.id.bLoan);

        BItem bItem = getItem(position);

        bImage.setImageDrawable(bItem.getCover());
        bTitle.setText(bItem.getTitle());
        bAuth.setText(bItem.getAuth());
        bPos.setText(bItem.getPos());
        bLoan.setText(bItem.getLoan());

        /*위젯에 대한 이벤트리스너를 지정하려면 여기*/
        return convertView;
    }

    public void addItem(Drawable cover, String title, String auth, String pos, String loan){
        BItem bItem = new BItem();

        bItem.setCover(cover);
        bItem.setTitle(title);
        bItem.setAuth(auth);
        bItem.setPos(pos);
        bItem.setLoan(loan);

        bItems.add(bItem);
    }
}
