package com.example.autobrary.mypage.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.LoanBookInfo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoanListViewAdapter  extends RecyclerView.Adapter<LoanListViewAdapter.ItemViewHolder> {
    private ArrayList<LoanBookInfo> listViewItemList = new ArrayList<LoanBookInfo>() ;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_loan_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        try {
            holder.onBind(listViewItemList.get(position));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    TextView title, author, publisher, loan_date, return_view, return_date;
    ImageView image;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.bTitle);
        author = (TextView)itemView.findViewById(R.id.bAuth);
        publisher = (TextView)itemView.findViewById(R.id.bPub);
        loan_date = (TextView)itemView.findViewById(R.id.bLoan);
        image = (ImageView)itemView.findViewById(R.id.bCover);
        return_view = (TextView)itemView.findViewById(R.id.returnView);
        return_date = (TextView)itemView.findViewById(R.id.bReturn);
        return_date.setVisibility(View.GONE);
        return_view.setVisibility(View.GONE);
    }
    void onBind(LoanBookInfo data) throws ExecutionException, InterruptedException {
        title.setText(data.getName());
        author.setText(data.getAuthor());
        publisher.setText(data.getPublisher());
        loan_date.setText(data.getLoanDate());
        BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(data.getImage());
            bucket.execute().get();
        image.setImageBitmap(bucket.getBitmap());
    }
}
    public void addItem(LoanBookInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
