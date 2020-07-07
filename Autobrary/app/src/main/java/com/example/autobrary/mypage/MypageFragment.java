package com.example.autobrary.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
import com.example.autobrary.mypage.info.InterestBookInfo;
import com.example.autobrary.mypage.info.LoanBookInfo;
import com.example.autobrary.mypage.info.ReturnBookInfo;
import com.example.autobrary.session.SessionManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

import afu.org.checkerframework.checker.oigj.qual.O;


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
                if(loanArrow.getRotation() == 0) {
                    loanArrow.setRotation(180);
                   loanlistView.setVisibility(View.GONE);
                }else{
                    loanArrow.setRotation(0);
                    loanlistView.setVisibility(View.VISIBLE);
                }
            }
        });

        returnArrow.setOnClickListener(new View.OnClickListener() { // 반납 화살표 리스너
            @Override
            public void onClick(View v) {
                if(returnArrow.getRotation() == 0){
                    returnArrow.setRotation(180);
                    returnlistView.setVisibility(View.GONE);
                }else{
                    returnArrow.setRotation(0);
                    returnlistView.setVisibility(View.VISIBLE);
                }
            }
        });

        foryouArrow.setOnClickListener(new View.OnClickListener() { //추천 화살표 리스너
            @Override
            public void onClick(View v) {
                if(foryouArrow.getRotation() == 0){
                    foryouArrow.setRotation(180);
                    interrestListView.setVisibility(View.GONE);
                }else{
                    foryouArrow.setRotation(0);
                    interrestListView.setVisibility(View.VISIBLE);
                }
            }
        });

        try {
            BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(SessionManager.getAttribute("profile_img"));
            bucket.start();
            bucket.join();
            name.setText(SessionManager.getAttribute("name"));
            email.setText(SessionManager.getAttribute("email"));
            profileImg.setImageBitmap(bucket.getBitmap());
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }
    private void initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (getLoanBook() ) {
            if (getReturnBook()) {
                if(getInterestBook()) {
                    for (LoanBookInfo info : loanBookInfo) {
                        loanadapter.addItem(info);
                    }
                    for (ReturnBookInfo info : returnBookInfo) {
                        returnadapter.addItem(info);
                    }
                    for (InterestBookInfo info : interestBookInfo) {
                        interestListViewAdapter.addItem(info);
                    }
                    loanadapter.notifyDataSetChanged();
                    returnadapter.notifyDataSetChanged();
                    interestListViewAdapter.notifyDataSetChanged();
                }
            }

        }
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