package com.example.bookapp.fin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookapp.R;


public class Splash_screen_2 extends Fragment {
    // like loading text in fackbook


    public Splash_screen_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragmen
        View view =  inflater.inflate(R.layout.fragment_splash_screen_2, container, false);
        return  view;
    }
}