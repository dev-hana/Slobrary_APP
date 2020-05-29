package com.example.autobrary.mypage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;

import java.util.ArrayList;

public class MpBookAdapter extends RecyclerView.Adapter<MpBookAdapter.ViewHolder> {
    private ArrayList<MpBookInfo> listViewItemList = new ArrayList<MpBookInfo>() ;

    @NonNull
    @Override
    public MpBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MpBookAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ArrayList<MpBookInfo> listitem = new ArrayList<MpBookInfo>();

        public ViewHolder(View item){
            super(item);

            //listitem = item.findViewById(R.id.mpBookList);
        }
    }
}
