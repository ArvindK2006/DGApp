package com.ebookfrenzy.tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ebookfrenzy.tablayoutdemo.databinding.FragmentSignupBinding;
import com.ebookfrenzy.tablayoutdemo.databinding.FragmentTab3Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {

    Button button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance(String param1, String param2) {
        Tab3Fragment fragment = new Tab3Fragment();
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

      //  setContentView(R.layout.fragment_tab3);
      /*  button = (Button) button.findViewById(R.id.signUpButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUpActivity();
            }
        }); */
    }

    public void openSignUpActivity(){
        Intent intent = new Intent(getActivity(),SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        // When the button is clicked, the fragment_signup screen is shown
        // however, the rest of the app is still visible and interactive

        /*
        Button btnFragment = (Button) view.findViewById(R.id.signUpButton);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new SignUpFragment());
                fr.commit();
            }
        }); */

        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tab3, container, false);
      /*  binding = FragmentTab3Binding.inflate(inflater, container, false);
        binding.signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.sendEmail,SignUpFragment);
                transaction.commit();
            }
        }); */

        /*View v = inflater.inflate(R.layout.fragment_tab3,container,false);
        Button btn = (Button) v.findViewById(R.id.signUpButton);
        btn.setOnClickListener((v) -> {
            Fragment fragment = new SignUpFragment();
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction().replace(R.)
        });*/
    }
}