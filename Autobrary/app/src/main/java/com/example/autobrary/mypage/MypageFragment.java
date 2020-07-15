package com.example.autobrary.mypage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.mypage.adapter.InterestListViewAdapter;
import com.example.autobrary.mypage.adapter.LoanListViewAdapter;
import com.example.autobrary.mypage.adapter.ReturnListViewAdapter;
import com.example.autobrary.mypage.getdata.GetInterestBook;
import com.example.autobrary.mypage.getdata.GetLoanBook;
import com.example.autobrary.mypage.getdata.GetReturnBook;
import com.example.autobrary.info.book.InterestBookInfo;
import com.example.autobrary.info.book.LoanBookInfo;
import com.example.autobrary.info.book.ReturnBookInfo;
import com.example.autobrary.session.SessionManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class MypageFragment extends Fragment {

    private LoanListViewAdapter loanadapter;
    private ReturnListViewAdapter returnadapter;
    private InterestListViewAdapter interestListViewAdapter;
    private Vector<LoanBookInfo> loanBookInfo;
    private Vector<ReturnBookInfo> returnBookInfo;
    private Vector<InterestBookInfo> interestBookInfo;
    private Context context;
    private GetLoanBook loanBook = new GetLoanBook();
    private GetReturnBook returnBook = new GetReturnBook();
    private GetInterestBook interestBook = new GetInterestBook();
    private RecyclerView loanlistView;
    private RecyclerView returnlistView;
    private RecyclerView interrestListView;
    private ImageView loanArrow, returnArrow, foryouArrow;
    private LinearLayout returnTab, loanTab, interestTab;
    TextView name, email;
    ImageView profileImg;
    Rpage activity;
    public static MypageFragment newInstance() {
        return new MypageFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Rpage) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);
        View view = inflater.inflate(R.layout.fragment_mypage,null);
        context = container.getContext();
        loanlistView = (RecyclerView) root.findViewById(R.id.loanList);
        returnlistView = (RecyclerView) root.findViewById(R.id.returnBookList);
        interrestListView = (RecyclerView) root.findViewById(R.id.interestBookList);

        returnlistView.setVisibility(View.GONE);
        loanlistView.setVisibility(View.GONE);
        interrestListView.setVisibility(View.GONE);

        loanArrow = (ImageView) root.findViewById(R.id.arrow_down_loan);
        returnArrow = (ImageView) root.findViewById(R.id.arrow_down_return);
        foryouArrow = (ImageView) root.findViewById(R.id.arrow_down_interest);
        returnTab = (LinearLayout) root.findViewById(R.id.return_tab);
        loanTab = (LinearLayout) root.findViewById(R.id.loan_tab);
        interestTab = (LinearLayout) root.findViewById(R.id.interest_tab);

        view.setNestedScrollingEnabled(true);
        LinearLayoutManager loanLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager returnLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager interestLayoutManager = new LinearLayoutManager(context);

        loanlistView.setLayoutManager(loanLayoutManager);
        returnlistView.setLayoutManager(returnLayoutManager);
        interrestListView.setLayoutManager(interestLayoutManager);

        loanadapter = new LoanListViewAdapter();
        returnadapter = new ReturnListViewAdapter();
        interestListViewAdapter = new InterestListViewAdapter();

        loanlistView.setAdapter(loanadapter);
        returnlistView.setAdapter(returnadapter);
        interrestListView.setAdapter(interestListViewAdapter);

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        profileImg = root.findViewById(R.id.profileImg);

        loanArrow.setOnClickListener(new View.OnClickListener() { //대출 화살표 리스너
            @Override
            public void onClick(View v) {
                clickTab(loanArrow, loanlistView);
            }
        });

        loanTab.setOnClickListener(new View.OnClickListener() { //대출 뷰 클릭 리스너
            @Override
            public void onClick(View view) {
                clickTab(loanArrow, loanlistView);
            }
        });

        returnArrow.setOnClickListener(new View.OnClickListener() { // 반납 화살표 리스너
            @Override
            public void onClick(View v) {
                clickTab(returnArrow, returnlistView);
            }
        });

        returnTab.setOnClickListener(new View.OnClickListener() { //반납 뷰 클릭 리스너
            @Override
            public void onClick(View view) {
                clickTab(returnArrow, returnlistView);
            }
        });

        foryouArrow.setOnClickListener(new View.OnClickListener() { //관심 화살표 리스너
            @Override
            public void onClick(View v) {
                clickTab(foryouArrow, interrestListView);
            }
        });
        interestTab.setOnClickListener(new View.OnClickListener() { //관심 뷰 클릭 리스너
            @Override
            public void onClick(View view) {
                clickTab(foryouArrow, interrestListView);
            }
        });

        try {
            BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(SessionManager.getAttribute("profile_img"));
            bucket.execute().get();
            name.setText(SessionManager.getAttribute("name"));
            email.setText(SessionManager.getAttribute("email"));
            profileImg.setImageBitmap(bucket.getBitmap());
            initialize();
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }

    private void clickTab(ImageView img, RecyclerView view){
        if(img.getRotation() == 0) {
            img.setRotation(180);
            view.setVisibility(View.GONE);
        }else{
            img.setRotation(0);
            view.setVisibility(View.VISIBLE);
        }
    }

    private void initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (getLoanBook() ) {
            for (LoanBookInfo info : loanBookInfo) {
                loanadapter.addItem(info);
            }
            if (getReturnBook()) {
                for (ReturnBookInfo info : returnBookInfo) {
                    returnadapter.addItem(info);
                }
                if(getInterestBook()) {
                    for (InterestBookInfo info : interestBookInfo) {
                        interestListViewAdapter.addItem(info);
                    }
                }
            }

        }
        loanadapter.notifyDataSetChanged();
        returnadapter.notifyDataSetChanged();
        interestListViewAdapter.notifyDataSetChanged();
    }
    private boolean getLoanBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        loanadapter.clearItem();
        loanBookInfo = loanBook.execute();
        result = !loanBookInfo.isEmpty();
        return result;
    }
    private boolean getReturnBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        returnadapter.clearItem();
        returnBookInfo = returnBook.execute();
        result = !returnBookInfo.isEmpty();
        return result;
    }
    private boolean getInterestBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        interestListViewAdapter.clearItem();
        interestBookInfo = interestBook.execute();
        result = !interestBookInfo.isEmpty();
        return result;
    }
}