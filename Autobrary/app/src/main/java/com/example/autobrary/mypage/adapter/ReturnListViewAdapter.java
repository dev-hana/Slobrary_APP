package com.example.autobrary.mypage.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand.DANGER;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.ReturnBookInfo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ReturnListViewAdapter  extends RecyclerView.Adapter<ReturnListViewAdapter.ItemViewHolder> {
    private ArrayList<ReturnBookInfo> listViewItemList = new ArrayList<ReturnBookInfo>() ;

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
        TextView title, author, publisher, loan_date, return_date;
        BootstrapButton bookStatus;
        ImageView image;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.bTitle);
            author = (TextView)itemView.findViewById(R.id.bAuth);
            publisher = (TextView)itemView.findViewById(R.id.bPub);
            loan_date = (TextView)itemView.findViewById(R.id.bLoan);
            return_date = (TextView)itemView.findViewById(R.id.bReturn);
            bookStatus = (BootstrapButton) itemView.findViewById(R.id.bookStatus);
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
        void onBind(ReturnBookInfo data) throws ExecutionException, InterruptedException {
            title.setText(data.getName());
            author.setText(data.getAuthor());
            publisher.setText(data.getPublisher());
            loan_date.setText(data.getLoanDate());
            return_date.setText(data.getReturnDate());
            bookStatus.setText("반납");
            bookStatus.setBootstrapBrand(DANGER);
            BucketConnector bucket = new BucketConnector();
                bucket.setObjectName(data.getImage());
                bucket.execute().get();
            image.setImageBitmap(bucket.getBitmap());
        }
    }
    public void addItem(ReturnBookInfo info) {
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
