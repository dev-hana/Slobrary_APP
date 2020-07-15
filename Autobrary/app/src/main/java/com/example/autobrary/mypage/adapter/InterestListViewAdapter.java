package com.example.autobrary.mypage.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.InterestBookInfo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand.DANGER;
import static com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand.WARNING;

public class InterestListViewAdapter extends RecyclerView.Adapter<InterestListViewAdapter.ItemViewHolder> {
    private ArrayList<InterestBookInfo> listViewItemList = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_interest_item, parent, false);
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
    TextView title, author, publisher;
    ImageView image;
    BootstrapButton bookStatus;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        bookStatus = (BootstrapButton) itemView.findViewById(R.id.bookStatus);
        title = (TextView)itemView.findViewById(R.id.bTitle);
        author = (TextView)itemView.findViewById(R.id.bAuth);
        publisher = (TextView)itemView.findViewById(R.id.bPub);
        image = (ImageView)itemView.findViewById(R.id.bCover);
    }
    void onBind(InterestBookInfo data) throws ExecutionException, InterruptedException {
        title.setText(data.getName());
        author.setText(data.getAuthor());
        publisher.setText(data.getPublisher());
        bookStatus.setText(data.getStatus());
        if(data.getStatus().equals("대출중")){
            bookStatus.setBootstrapBrand(DANGER);
        }else if(data.getStatus().equals("예약중")){
            bookStatus.setBootstrapBrand(WARNING);
        }
        BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(data.getImage());
            bucket.execute().get();
        image.setImageBitmap(bucket.getBitmap());
    }
}
    public void addItem(InterestBookInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }
}
