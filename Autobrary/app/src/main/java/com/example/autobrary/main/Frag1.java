package com.example.autobrary.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.autobrary.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {
    EditText edtSch;
    ImageButton btnSch;
    //Spinner spin1, spin2, spin3;
    Button btnRe, btnS;
    private Context context;
    String all="";
    String search = "";
    String text1, text2, text3;


    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag1,container,false);



//        final String[] blist1 = {"국내외분류", "분류1", "분류2"};
//        final String[] blist2 = {"도서분류", "분류11", "분류22", "분류33"};
//        final String[] blist3 = {"신착자료검색", "분류111", "분류222", "분류333"};

        edtSch = v.findViewById(R.id.edtSch);

//        spin1 = v.findViewById(R.id.spin1);
//        spin2 = v.findViewById(R.id.spin2);
//        spin3 = v.findViewById(R.id.spin3);

        btnRe = v.findViewById(R.id.btnRe);
        btnS = v.findViewById(R.id.btnS);

//        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,blist1);
//        ArrayAdapter<String> adap2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,blist2);
//        ArrayAdapter<String> adap3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,blist3);

//        spin1.setAdapter(adap1);
//        spin2.setAdapter(adap2);
//        spin3.setAdapter(adap3);

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = container.getContext();
                edtSch.setText(null);
//                spin1.setSelection(0);
//                spin2.setSelection(0);
//                spin3.setSelection(0);
                Toast.makeText(context,"초기화", Toast.LENGTH_LONG).show();
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = container.getContext();
                search = edtSch.getText().toString();
//                text1 = spin1.getSelectedItem().toString();
//                text2 = spin2.getSelectedItem().toString();
//                text3 = spin3.getSelectedItem().toString();
                all = "검색 : "+search+", 1. "+text1+", 2. "+text2+", 3. "+text3;
                Toast.makeText(context, all, Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
