package com.example.autobrary.wish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.autobrary.R;
import com.example.autobrary.info.wish.WishInfo;

import java.util.ArrayList;

public class WishAdapter extends BaseAdapter { //implements View.OnClickListener
    // 버튼 관련 //
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<WishInfo> data;
    /////

    public WishAdapter(){}

    private ArrayList<WishInfo> listViewItemList = new ArrayList<WishInfo>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public WishInfo getItem(int position) {
        return listViewItemList.get(position);
    }

    //버튼 클릭//
    public WishAdapter(Context context, ArrayList<WishInfo> data){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

//    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의 ///
//    public interface ListBtnClickListener {
//        void onListBtnClick(int position);
//    }
//    // 생성자로부터 전달된 resource id 값을 저장.
//    int resourceId;
//    // 생성자로부터 전달된 ListBtnClickListener  저장.
//    private ListBtnClickListener listBtnClickListener;
//
//    // ListViewBtnAdapter 생성자. 마지막에 ListBtnClickListener 추가.
//    WishAdapter(Context context, int resource, ArrayList<WishInfo> list, ListBtnClickListener clickListener) {
//        super();
//
//        // resource id 값 복사. (super로 전달된 resource를 참조할 방법이 없음.)
//        this.resourceId = resource;
//
//        this.listBtnClickListener = clickListener;
//    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_wish_item, parent, false);
        }
        TextView wishTitle = (TextView)convertView.findViewById(R.id.bTitle);
        TextView wishAuthor = (TextView)convertView.findViewById(R.id.bAuth);
        TextView wishPublish = (TextView)convertView.findViewById(R.id.bPub);
        TextView wishDate = (TextView)convertView.findViewById(R.id.bDate);
        TextView applyStatus = (TextView)convertView.findViewById(R.id.applyStatus);

        applyStatus.setText(listViewItemList.get(position).getStatus());
        wishTitle.setText(listViewItemList.get(position).getBookTitle());
        wishAuthor.setText(listViewItemList.get(position).getBookAuthor());
        wishPublish.setText(listViewItemList.get(position).getBookPublish());
        wishDate.setText(listViewItemList.get(position).getBookDate());

        String title = wishTitle.getText().toString();

        BootstrapButton canBtn = convertView.findViewById(R.id.canBtn);

        canBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "취소 :  "+title, Toast.LENGTH_LONG).show();
            }
        });


//        canBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, (CharSequence) wishTitle, Toast.LENGTH_LONG).show();
//            }
//        });
//        canBtn.setTag(position);
//        canBtn.setOnClickListener(this);

        return convertView;
    }
    public void addItem(WishInfo info) {
        listViewItemList.add(info);
    }
    public void clearItem() {
        listViewItemList.clear();
    }

//    @Override
//    public void onClick(View v) {
//        if (this.listBtnClickListener != null){
//            this.listBtnClickListener.onListBtnClick((int)v.getTag());
//        }
//    }
}

