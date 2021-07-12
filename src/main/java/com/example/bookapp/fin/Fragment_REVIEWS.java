package com.example.bookapp.fin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.books_class.The_REVIEWS;

import org.jetbrains.annotations.NotNull;


public class Fragment_REVIEWS extends Fragment {

    // layout

    ImageView back_imageview;

    The_REVIEWS the_reviews =  new The_REVIEWS();

    The_Book selected_book;

    LottieAnimationView ratting_of_book;

    LottieAnimationView ratting_of_rev;

    Single_one single_one = Single_one.getInstance();


    public Fragment_REVIEWS() {
        // Required empty public constructor
    }

    public Fragment_REVIEWS(The_REVIEWS the_reviews) {
        this.the_reviews = the_reviews;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view =  inflater.inflate(R.layout.fragment__r_e_v_i_e_w_s, container, false);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // code here

        Single_one single_one = Single_one.getInstance();
        selected_book = single_one.getNow_in_overview();

        back_imageview = view.findViewById(R.id.back_imageview_rev);
        TextView name_book = view.findViewById(R.id.one_bookName);
        TextView date = view.findViewById(R.id.one_date);
        TextView rev_name = view.findViewById(R.id.one_rev_name);

        rev_name.setText("Review By : " + the_reviews.getName());


        String tt = the_reviews.getDescription();

        tt = tt.replace(". ","\n");



        TextView des = view.findViewById(R.id.one_des);

        name_book.setText(selected_book.getTitle());
        des.setText(tt);
        date.setText(the_reviews.getDate());

        // anim
        ratting_of_book = view.findViewById(R.id.stars_of_the_book_rev);



        // set the anim of the stars of the book
        String getratting = selected_book.getAverageRating();

        if (getratting.contains(".")) {
            getratting = (String.valueOf(getratting.charAt(0)));
            Log.d("Fratting", "onViewCreated: 1 : " + getratting);

            String rat = getratting;

            if (rat.equals("5")){
                ratting_of_book.setAnimation(R.raw.stars_5);

            }else if (rat.equals("4")){
                ratting_of_book.setAnimation(R.raw.stars_4);

            }else if (rat.equals("3")){
                ratting_of_book.setAnimation(R.raw.stars_3);

            }else if (rat.equals("2")){
                ratting_of_book.setAnimation(R.raw.stars_2);

            }else if (rat.equals("1")){
                ratting_of_book.setAnimation(R.raw.stars_1);

            }else {
                // if == 0
                ratting_of_book.setAnimation(R.raw.stars_0);
            }
            ratting_of_book.playAnimation();
        }

        // set the stars of the rev anim

        Log.d("stars_by_rev", "onViewCreated: " + the_reviews.getStars());

        ratting_of_rev = view.findViewById(R.id.stars_of_the_book_by_name);


        String star_rev = the_reviews.getStars();

        if (star_rev.equals("it was amazing")){
            // its 5
            ratting_of_rev.setAnimation(R.raw.stars_5);

        }else if (star_rev.equals("really liked it")){
            // its 4
            ratting_of_rev.setAnimation(R.raw.stars_4);

        } else if (star_rev.equals("liked it")) {
            // its 3
            ratting_of_rev.setAnimation(R.raw.stars_3);

        }else if (star_rev.equals("it was ok")){

            // its 2
            ratting_of_rev.setAnimation(R.raw.stars_2);


        }else if (star_rev.equals("did not like it")){
            // its 1
            ratting_of_rev.setAnimation(R.raw.stars_1);

        }else {
            // its 0
            ratting_of_rev.setAnimation(R.raw.stars_0);
        }
        ratting_of_rev.playAnimation();

        // click on back button
        back_imageview.setOnClickListener(v -> callclose());


    }

    private void callclose(){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(Fragment_REVIEWS.this).commit();
        single_one = Single_one.getInstance();
        if (single_one.isIn_search_book()) {

        } else {
            getActivity().onBackPressed();
        }

    }
}