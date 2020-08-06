package com.example.autobrary.book;

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
import androidx.fragment.app.Fragment;
import com.example.autobrary.R;
import com.example.autobrary.externalConnecter.BucketConnector;
import com.example.autobrary.info.book.BookInfo;
import com.example.autobrary.info.book.BookMoreInfo;
import com.example.autobrary.info.book.DetailBookInfo;
import com.example.autobrary.info.wish.WishInfo;
import com.example.autobrary.main.BAdapter;
import com.example.autobrary.main.Rpage;
import com.example.autobrary.main.getdata.GetBestBook;
import com.example.autobrary.wish.Wish2Fragment;
import com.example.autobrary.wish.WishFragment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;
import java.util.concurrent.ExecutionException;


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

        //////////  도서 위쪽 정보  //////////

        TextView bookTypeT = root.findViewById(R.id.bookType); // 자료유형
        String bookType = bookTypeT.getText().toString();

        TextView authorT = root.findViewById(R.id.book_author); // 작가
        String author = authorT.getText().toString();

        TextView pubInfoT = root.findViewById(R.id.publishInfo); // 발행사항
        String pub = pubInfoT.getText().toString();

        TextView objInfoT = root.findViewById(R.id.objectInfo); // 형태사항
        String objInfo = objInfoT.getText().toString();

        TextView isbnInfoT = root.findViewById(R.id.isbnInfo); // isbn 번호
        String isbnInfo = isbnInfoT.getText().toString();

        TextView sortSignT = root.findViewById(R.id.sortSign); // 분류기호
        String sortSign = sortSignT.getText().toString();

        TextView lanugageT = root.findViewById(R.id.languageInfo); //언어
        String language = lanugageT.getText().toString();

        TextView recoScoreT = root.findViewById(R.id.recommendScore); //평점
        String recoScore = recoScoreT.getText().toString();

        Vector<String> bookDetailList = new Vector<>();
        bookDetailList.add(bookType);
        bookDetailList.add(author);
        bookDetailList.add(pub);
        bookDetailList.add(objInfo);
        bookDetailList.add(isbnInfo);
        bookDetailList.add(sortSign);
        bookDetailList.add(language);
        bookDetailList.add(recoScore);

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

        Vector<DetailBookInfo> getData = null;
        BucketConnector bucket = null;
        try {
            getData = new GetBookDetail().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bookDetailList.size(); i++){
            bucket = new BucketConnector();
            bucket.setObjectName(getData.get(i).getImage());
            try {
                bucket.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bookDetailList.get(i);
            //            bookDetailList.get(i).setImageBitmap(bucket.getBitmap());
        }

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
