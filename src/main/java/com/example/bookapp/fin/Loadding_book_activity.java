package com.example.bookapp.fin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookapp.MainActivity;
import com.example.bookapp.R;
import com.example.bookapp.ui.home.HomeFragment;

public class Loadding_book_activity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding_book);

        lottieAnimationView = findViewById(R.id.textView6_bbok);

        //getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Runnable runnable = new Runnable(){
            public void run() {
                lottieAnimationView.playAnimation();
                lottieAnimationView.loop(true);
            }
        };
        runnable.run();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lottieAnimationView.playAnimation();
                lottieAnimationView.loop(true);

                Log.d("fag1", "run: 1");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("fag22", "run: 1");
                        finish();
                    }
                }, 1400);

            }
        }, 0000);


    }
}