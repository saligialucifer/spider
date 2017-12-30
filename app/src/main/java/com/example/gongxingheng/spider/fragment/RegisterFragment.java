package com.example.gongxingheng.spider.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gongxingheng.spider.R;
import com.example.gongxingheng.spider.UserDBHelper;

import net.steamcrafted.loadtoast.LoadToast;

import static java.lang.Thread.sleep;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button register;
    private EditText name;
    private EditText pass;
    private EditText nickname;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        register =(Button)getActivity().findViewById(R.id.bt_register);
        name =(EditText)getActivity().findViewById(R.id.et_rename);
        pass = (EditText)getActivity().findViewById(R.id.et_repass);
        nickname = (EditText)getActivity().findViewById(R.id.nickname);
        register.setOnClickListener(this);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        register.setText("正在注册");
        try{
            sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean flag =UserDBHelper.getInstance(getActivity()).insert(name.getText().toString(),pass.getText().toString(),nickname.getText().toString());

        if(flag){
            register.setText("注册成功");
            register.setVisibility(View.GONE);
            //register.setClickable(false);
            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getContext(),"注册失败，大概是手机号重复╮(╯▽╰)╭",Toast.LENGTH_SHORT).show();
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
