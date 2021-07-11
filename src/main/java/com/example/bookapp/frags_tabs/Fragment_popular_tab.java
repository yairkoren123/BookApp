package com.example.bookapp.frags_tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookapp.R;
import com.example.bookapp.fin.Fragment_Quotes_Search;
import com.example.bookapp.fin.Splash_screen;

import org.jetbrains.annotations.NotNull;


public class Fragment_popular_tab extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_tab, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment_Quotes_Search myFragment = new Fragment_Quotes_Search();
        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.mail_countener5, myFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // code here


//        Fragment_Quotes_Search myFragment = new Fragment_Quotes_Search();
//        getActivity().getSupportFragmentManager()
//                .beginTransaction().replace(R.id.mail_countener5, myFragment)
//                .addToBackStack(null).commit();

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
}
