package com.example.autobrary.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.NoticeAdapter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class MypageFragment extends Fragment {

    private LoanListViewAdapter adapter;
    private Vector<BookInfo> BookInfo;
    private Context context;
    private GetLoanBook loanBook = new GetLoanBook();
    private ListView listView;

    TextView name, email;
    ImageView profileImg;
    Rpage activity;
    private Mypage notice = new Mypage();

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
        listView = (ListView) root.findViewById(R.id.loanList);
        adapter = new LoanListViewAdapter();
        listView.setAdapter(adapter);
        context = container.getContext();

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
        if (getLoanBook()) {
            for (BookInfo info : BookInfo) {
                adapter.addItem(info);
            }
        }
    }
    private boolean getLoanBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        adapter.clearItem();
        BookInfo = loanBook.execute();
        if (BookInfo.isEmpty()) {
            Toast.makeText(context, "인터넷연결이 불안정합니다.", Toast.LENGTH_LONG).show();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}