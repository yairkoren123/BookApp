package com.example.bookapp.fin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookapp.R;

import org.jetbrains.annotations.NotNull;


public class Splash_screen extends Fragment {

    // the main loading screen (with the book anime)


    // layout

    LottieAnimationView book_anim;

    LottieAnimationView loading_simple;

    Single_one single_one = Single_one.getInstance();

    public Splash_screen() {
        // Required empty public constructor
        Log.d("zzzz", "Splash_screen: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        book_anim = view.findViewById(R.id.book_anim_sp);
        loading_simple = view.findViewById(R.id.loading_anim_sp);

        single_one = Single_one.getInstance();

        //single_one.setNow_drow("load");



    }
}