package com.example.autobrary.mypage.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    // 버튼 관련 //
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<InterestBookInfo> data;
    /////

    //버튼 클릭//
    public InterestListViewAdapter(Context context, ArrayList<InterestBookInfo> data){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }


    private ArrayList<InterestBookInfo> listViewItemList = new ArrayList<>();

    public InterestListViewAdapter() {

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_interest_item, parent, false);

        BootstrapButton revBtn = view.findViewById(R.id.revBtn);
        BootstrapButton recoBtn = view.findViewById(R.id.recoBtn);
        BootstrapButton delBtn = view.findViewById(R.id.delBtn);

        revBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "리뷰", Toast.LENGTH_LONG).show();
            }
        });

        recoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "기록", Toast.LENGTH_LONG).show();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "삭제", Toast.LENGTH_LONG).show();
            }
        });

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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    if(mListener !=null){
                        mListener.onItemClick(v,position);
                    }
                }
            }
        });
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

    private LoanViewAdapter.OnItemClickListener mListener = null ;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(LoanViewAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }
}
