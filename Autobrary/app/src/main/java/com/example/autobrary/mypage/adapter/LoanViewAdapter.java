package com.example.autobrary.mypage.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.LoanBookInfo;
import com.example.autobrary.info.book.LoanBookInfo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoanViewAdapter extends RecyclerView.Adapter<LoanViewAdapter.ItemViewHolder> {
    private ArrayList<LoanBookInfo> listViewItemList = new ArrayList<LoanBookInfo>() ;

    // 버튼 관련 //
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<LoanBookInfo> data;
    /////

    //버튼 클릭//
    public LoanViewAdapter(Context context, ArrayList<LoanBookInfo> data){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public LoanViewAdapter() {

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_loan_item, parent, false);

        BootstrapButton diBtn;
        diBtn = view.findViewById(R.id.diBtn);

        int b = 1;

        diBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("내 일기",String.valueOf(b));
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
    TextView title, author, publisher, loan_date, return_view, return_date;
    ImageView image;
    BootstrapButton scBtn;

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
        scBtn = (BootstrapButton) itemView.findViewById(R.id.scBtn);

        int a = 10;

        scBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("평점 버튼",String.valueOf(a)); //오류 x
            }
        });

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

    private OnItemClickListener mListener = null ;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

}
