package com.example.autobrary.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.main.Rpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class MypageFragment extends Fragment {

    private LoanListViewAdapter loanadapter;
    private ReturnListViewAdapter returnadapter;
    private Vector<BookInfo> loanBookInfo;
    private Vector<BookInfo> returnBookInfo;
    private Context context;
    private GetLoanBook loanBook = new GetLoanBook();
    private GetReturnBook returnBook = new GetReturnBook();
    private RecyclerView loanlistView;
    private RecyclerView returnlistView;

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
        view.setNestedScrollingEnabled(true);
        LinearLayoutManager loanLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager returnLayoutManager = new LinearLayoutManager(context);
        loanlistView.setLayoutManager(loanLayoutManager);
        returnlistView.setLayoutManager(returnLayoutManager);
        loanadapter = new LoanListViewAdapter();
        returnadapter = new ReturnListViewAdapter();
        loanlistView.setAdapter(loanadapter);
        returnlistView.setAdapter(returnadapter);
        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        profileImg = root.findViewById(R.id.profileImg);
        try {
            MypageInfo info = new Mypage().execute();
            BucketConnector bucket = new BucketConnector();
            bucket.setObjectName(info.profileImg);
            bucket.start();
            bucket.join();
            name.setText(info.getName());
            email.setText(info.getEmail());
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
                for (BookInfo info : loanBookInfo) {
                    loanadapter.addItem(info);
                }
                for (BookInfo info : returnBookInfo) {
                    returnadapter.addItem(info);
                }
                loanadapter.notifyDataSetChanged();
                returnadapter.notifyDataSetChanged();
            }

        }
    }
    private boolean getLoanBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        loanadapter.clearItem();
        loanBookInfo = loanBook.execute();
        if (loanBookInfo.isEmpty()) {
            Toast.makeText(context, "인터넷연결이 불안정합니다.", Toast.LENGTH_LONG).show();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    private boolean getReturnBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        returnadapter.clearItem();
        returnBookInfo = returnBook.execute();
        result = !returnBookInfo.isEmpty();
        return result;
    }
}