package com.example.autobrary.mypage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;

import java.util.ArrayList;

public class ReturnListViewAdapter  extends RecyclerView.Adapter<ReturnListViewAdapter.ItemViewHolder> {
    private ArrayList<BookInfo> listViewItemList = new ArrayList<BookInfo>() ;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_loan_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listViewItemList.get(position));
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listViewItemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title, author, publisher, loan_date, return_date;
        ImageView image;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.bTitle);
            author = (TextView)itemView.findViewById(R.id.bAuth);
            publisher = (TextView)itemView.findViewById(R.id.bPub);
            loan_date = (TextView)itemView.findViewById(R.id.bLoan);
            return_date = (TextView)itemView.findViewById(R.id.bReturn);
            image = (ImageView)itemView.findViewById(R.id.bCover);
        }
        void onBind(BookInfo data) {
            title.setText(data.getName());
            author.setText(data.getAuthor());
            publisher.setText(data.getPublisher());
            loan_date.setText(data.getLoanDate());
            return_date.setText(data.getReturnDate());
            BucketConnector bucket = new BucketConnector();
            try {
                bucket.setObjectName(data.getImage());
                bucket.start();
                bucket.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(bucket.getBitmap());
        }
    }
    public void addItem(BookInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
