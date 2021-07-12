package com.example.bookapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
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
        single_one.setIn_search_book(false);





        if (!isconnected()) {
            // no internet
            Log.d("wifi", "onCreate: no ");
            alert_dialog();

//            // if you don't have WIFI/INTERNET
//            Intent intent = new Intent(this,Loadding_book_activity.class);
//            startActivity(intent);
//            finish();
        }else {
            Log.d("wifi", "onCreate: yes ");

        }


        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                // network available
                if (isconnected()){
                    Log.d("wifi", "onCreate: yes ");
                }

            }

            @Override
            public void onLost(Network network) {
                // network unavailable

                try {
                    alert_dialog();
                } catch (Exception e) {
                    Log.d("wifi", "Show Dialog: " + e.getMessage());
                }


//                new AlertDialog.Builder(getApplicationContext())
//                        .setIcon(R.drawable.ic_baseline_priority_high_24)
//                        .setTitle("no Internet")
//                        .setMessage("you don't have Internet connection do you want to retry ?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // close the app
//                                if (isconnected()){
//                                    dialog.cancel();
//                                }else {
//                                    // no internet
//                                }
//                            }
//
//                        })
//                        .setNegativeButton("No", null)
//                        .show();

                if (!isconnected()) {
                    Log.d("wifi", "onCreate: no ");
                }
            }
        };

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        } else {
            NetworkRequest request = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
            connectivityManager.registerNetworkCallback(request, networkCallback);
        }

    }
    public boolean isconnected(){
        boolean connected = false;
        ConnectivityManager connectivityManager1 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            return true;
        }
        else {
            // don't have internet


            return false;
        }
    }
    public void alert_dialog(){

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Error")
                .setMessage("Internet not available, Cross check your internet connectivity and try again later...")
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_wifi_off_24)
                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        if (isconnected()){
                            dialog.dismiss();

                        }else {
                            alert_dialog();
                        }
                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
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