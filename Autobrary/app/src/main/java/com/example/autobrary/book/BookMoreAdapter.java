package com.example.autobrary.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.autobrary.R;
import com.example.autobrary.info.book.BookMoreInfo;

import java.util.ArrayList;

public class BookMoreAdapter extends BaseAdapter {
    private ArrayList<BookMoreInfo> listViewItemList = new ArrayList<BookMoreInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BookMoreInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_book_more, parent, false);
        }
        TextView id_num = (TextView)convertView.findViewById(R.id.id_num); // 등록번호
        TextView book_symbol = (TextView)convertView.findViewById(R.id.book_symbol); // 청구기호
        TextView collector = (TextView)convertView.findViewById(R.id.collector); // 소장처
        TextView status = (TextView)convertView.findViewById(R.id.status); // 도서상태
        TextView return_date = (TextView)convertView.findViewById(R.id.return_date); //반납예정일

        id_num.setText(listViewItemList.get(position).getId_num());
        book_symbol.setText(listViewItemList.get(position).getBook_symbol());
        collector.setText(listViewItemList.get(position).getCollector());
        status.setText(listViewItemList.get(position).getStauts());
        return_date.setText(listViewItemList.get(position).getReturn_date());

        return convertView;
    }
    public void addItem(BookMoreInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
