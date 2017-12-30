package com.example.gongxingheng.spider.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gongxingheng.spider.MainActivity;
import com.example.gongxingheng.spider.R;
import com.example.gongxingheng.spider.UserDBHelper;


public class LoginFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button login;
    private EditText name;
    private EditText pass;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        login =(Button)getActivity().findViewById(R.id.bt_login);
        name =(EditText)getActivity().findViewById(R.id.et_name);
        pass = (EditText)getActivity().findViewById(R.id.et_pass);
        login.setOnClickListener(this);



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
        if(name.getText().toString().equals("")||pass.getText().toString().equals("")){
            login.setClickable(false);
        }else{
            login.setClickable(true);
        }
        String repass = UserDBHelper.getInstance(getContext()).serch(name.getText().toString());
        if(pass.getText().toString().equals(repass)){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(),"登陆成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"登陆失败",Toast.LENGTH_SHORT).show();
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
