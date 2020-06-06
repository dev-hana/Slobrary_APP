package com.example.autobrary.mypage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;

import java.util.ArrayList;

public class LoanListViewAdapter  extends BaseAdapter {
    private ArrayList<BookInfo> listViewItemList = new ArrayList<BookInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BookInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_loan_item, parent, false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.bTitle);
        TextView author = (TextView)convertView.findViewById(R.id.bAuth);
        TextView publisher = (TextView)convertView.findViewById(R.id.bPub);
        TextView loan_date = (TextView)convertView.findViewById(R.id.bLoan);
        ImageView image = (ImageView)convertView.findViewById(R.id.bCover);

        title.setText(listViewItemList.get(position).getName());
        author.setText(listViewItemList.get(position).getAuthor());
        publisher.setText(listViewItemList.get(position).getPublisher());
        loan_date.setText(listViewItemList.get(position).getLoanDate());
        BucketConnector bucket = new BucketConnector();
        try {
        bucket.setObjectName(listViewItemList.get(position).getImage());
        bucket.start();
        bucket.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(bucket.getBitmap());
        return convertView;
    }
    public void addItem(BookInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
