package com.example.autobrary.book;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.autobrary.R;
import com.example.autobrary.info.book.BookMoreInfo;
import com.example.autobrary.info.book.DetailBookInfo;
import com.example.autobrary.info.wish.WishInfo;
import com.example.autobrary.main.BAdapter;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.wish.Wish2Fragment;
import com.example.autobrary.wish.WishFragment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;


public class BookDetailFragment  extends Fragment {

    private Rpage activity;
    private Context context;
    private Vector<BookMoreInfo> getBook;
    private ListView listView;
    private BookMoreAdapter adapter; // 리스트뷰 어댑터
    private BookList bookList = new BookList();

//    BAdapter adapter;
//    private Vector<WishInfo> getWish;
//    private WishList wishList = new WishList();



    public static BookDetailFragment newInstance() {
        return new BookDetailFragment();
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_book_detail, container, false);
        context = container.getContext();

        //////////// 리스트 연결 /////////

        listView = root.findViewById(R.id.bookCollectInfoList);
        adapter = new BookMoreAdapter();
        listView.setAdapter(adapter);

        try {
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
    // OnCreateView 끝

    private void initialize() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (getBook()) {
            for (BookMoreInfo info : getBook) {
                adapter.addItem(info);
            }
            adapter.notifyDataSetChanged();
        }
    }

    private boolean getBook() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        boolean result;
        adapter.clearItem();
        getBook = bookList.execute();
        if (getBook.isEmpty()) {
            Toast.makeText(context, "신청도서가 없거나 인터넷연결이 불안정합니다.", Toast.LENGTH_LONG).show();
            result = false;
        } else {
            result = true;
        }
        return result;
    }

}
