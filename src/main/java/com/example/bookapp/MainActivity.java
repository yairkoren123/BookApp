package com.example.bookapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.WindowManager;

import com.example.bookapp.fin.Loadding_book_activity;
import com.example.bookapp.fin.Single_one;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bookapp.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

//        Intent intent = new Intent(MainActivity.this, Loadding_book_activity.class);
//        startActivity(intent);

        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        Single_one single_one = Single_one.getInstance();
        single_one.setNow_drow("home");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        Single_one single_one = Single_one.getInstance();

        String now = single_one.getNow_drow();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Log.d("wwww", "onBackPressed: " + now);


        if (now.equals("home")) {

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_baseline_priority_high_24)
                    .setTitle("EXIT")
                    .setMessage("Are you sure you want to EXIT ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the app
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }else {

            super.onBackPressed();

        }







    }
}