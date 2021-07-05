package com.example.bookapp.fin;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.books_class.The_REVIEWS;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class The_book_Fragment_overview extends Fragment {

    The_Book selected_book = new The_Book();

    The_REVIEWS the_reviews = new The_REVIEWS();

    recycler_Adpter_REVIEWS adpterHORIZONTAL1;

    LinearLayoutManager layoutManager;



    ArrayList<The_REVIEWS> the_reviewsArrayList = new ArrayList<>();


    // layout

    LottieAnimationView ratting;
    RecyclerView recyclerView_REVIEWS;

    TextView title,year,description,len,pagecount,info,authors, category , ratting_button, country;
    ImageView book_image;

    Single_one single_one = Single_one.getInstance();



    public The_book_Fragment_overview() {
        // Required empty public constructor
    }

    public The_book_Fragment_overview(The_Book selected_book) {
        this.selected_book = selected_book;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_the_book__overview, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        single_one = Single_one.getInstance();
        if (single_one.getNow_drow().equals("home")){
            single_one.setNow_drow("overview_home");

        }else {
            single_one.setNow_drow("overview_search");
        }
        title = view.findViewById(R.id.title_overview);
        year = view.findViewById(R.id.year_overview);
        description = view.findViewById(R.id.des_overview);
        pagecount = view.findViewById(R.id.pagecount_overview);
        info = view.findViewById(R.id.moreinfo_overview);
        recyclerView_REVIEWS = view.findViewById(R.id.recyclerview_REVIEWS_overview);
        authors = view.findViewById(R.id.wirtes_overview);
        //category =view.findViewById(R.id.);
        ratting = view.findViewById(R.id.ratting_overview_top);
        ratting_button = view.findViewById(R.id.ratting_button_overview);
        len = view.findViewById(R.id.len_overview);
        country = view.findViewById(R.id.country_overview);

        book_image = view.findViewById(R.id.book_image_front_overview);


        des_back_overview des_back_overview = new des_back_overview();
        des_back_overview.execute();

    }


    private class des_back_overview extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            // code here

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    start_overview();


                }
            });
            return null;

        }
        private void start_overview() {

            layoutManager = new LinearLayoutManager(
                    getActivity(),LinearLayoutManager.HORIZONTAL
                    ,false);



            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);

                Log.d("click_url_ready", "start_overview: " + selected_book.getClick_URL());

                String Url = selected_book.getClick_URL();

                if (Url.contains("?from_search=true")) {
                    String pp[] = Url.split("from_search=true");
                    Url = pp[0];
                    Url = Url.replace("?", "");
                    selected_book.setClick_URL(Url);
                }
                Log.d("click_url_ready", "start_overview: " + Url);


                org.jsoup.nodes.Document doc = Jsoup.connect(Url)
                        .timeout(6000).get();

                Log.d("HTML1", "start_overview: " + doc.outerHtml());


                org.jsoup.select.Elements body = doc.select("div.leftContainer");

                String title_overview = body.select("h1.gr-h1").text();
                String dsc_overview = body.select("div.readable span").text();
                Elements ratting_over_view = body.select("div.col div.stacked span");
                String people_count_ratting = body.select("a.gr-hyperlink meta").attr("content");
                Elements len_count = body.select("div.infoBoxRowItem");
                Elements acu_list = body.select("div.leftContainer div.authorName__container span");
                Log.d("acu_over", "start_overview: " + acu_list.outerHtml());

                String data = "";
                for (Element a : acu_list) {

                    Log.d("acu_yes1", "start_overview: " + a.attr("href"));

                    if (a.attr("itemprop").equals("name")) {
                        Log.d("acu_yes", "start_overview: " + a.text());
                        if (data.equals("")) {
                            data += "by " + a.text() + "";
                        } else {
                            data += a.text() + " , ";

                        }

                    }
                }
                selected_book.setAuthors(data);

                Log.d("len_over", "start_overview: " + ratting_over_view.text());

                String p_ratting = ratting_over_view.get(6).text().toString();
                Log.d("TAG", "start_overview: " + p_ratting);

                Elements num_pages_el = body.select("div.uitext div.row");

                String pages_count_from_el = "";
                String len_st = "";


                for (int i = 0; i < num_pages_el.size(); i++) {
                    Element f = num_pages_el.get(i);


                    if (i == 0) {
                        String wow[] = f.text().split(",");
                        Log.d("gtenumber_page", "start_overview: " + wow[1]);
                        String page_amount = wow[1];
                        selected_book.setPageCount(page_amount);
                        Log.d("gtenumber_page", "start_overview: " + page_amount);


                    }
                    if (i == 1) {
                        Log.d("gtenumber_page", "start_overview: " + f.text());
                        selected_book.setPublishedDate(f.text());
                    }
                }
                selected_book.setInfoLink(Url);


                //loop
                for (Element el : len_count) {
                    if (el.attr("itemprop").equals("inLanguage")) {
                        len_st = el.text();

                        Log.d("wegood", "start_overview: " + len_st);
                    }
                }


                selected_book.setAverageRating(p_ratting);
                selected_book.setLanguage(len_st);
                selected_book.setPeople_ratting(people_count_ratting);
                selected_book.setDescription(dsc_overview);


                Log.d("over_get_title", "onCreate: book  : " + title_overview + " stars : " + " des +++" + dsc_overview);


                // the size
                Log.d("qqq", "onCreate: " + body.size());
                //Log.d("qqq", "onCreate: " + body.select("div.coverWrapper img")
                //.attr("alt"));

//                for (Element e : body.select("div") ) {
//                for (int i = 0; i < body.select("div").size(); i++) {


                // todo get the REVIEWS ;


                Elements rev = body.select("div.friendReviews");

                Log.d("rev_size", "start_overview: " + rev.size());

                int rev_size = rev.size();

                if (rev_size >= 15) {
                    rev_size = 15;
                }


                for (int i = 0; i < rev_size; i++) {

                    Element rev_el = rev.get(i);
                    the_reviews = new The_REVIEWS();


                    String rev_stars = rev_el.select("span.notranslate").attr("title");
                    Log.d("rev_stars", "start_overview: " + rev_stars);
                    the_reviews.setStars(rev_stars);

                    String rev_name = rev_el.select("a.user").attr("title");
                    Log.d("rev_name", "start_overview: " + rev_name);
                    the_reviews.setName(rev_name);

                    String rev_des = rev_el.select("span.readable span").text();
                    Log.d("rev_des", "start_overview: " + rev_des);
                    the_reviews.setDescription(rev_des);

                    String rev_likes = rev_el.select("span.likesCount").text();
                    Log.d("rev_likes", "start_overview: " + rev_likes);
                    the_reviews.setLikes(rev_likes);

                    String rev_date = rev_el.select("a.reviewDate").text();
                    Log.d("rev_date", "start_overview: " + rev_date);
                    the_reviews.setDate(rev_date);



                    the_reviewsArrayList.add(the_reviews);




                }






            }catch (Exception e ){
                e.printStackTrace();
            }


            Log.d("Fwork", "onViewCreated: " + selected_book.getTitle());


            if (selected_book.getTitle().contains("(")){
                final String pp[] = selected_book.getTitle().split("\\(");
                title.setText(pp[0]);
            }else {
                title.setText(selected_book.getTitle());
            }


            //Published May 20th 2021
            //Published August 6th 2018 by Elle Kennedy Inc. (first published
            if (selected_book.getPublishedDate().contains(".")) {
                final String pp[] = selected_book.getPublishedDate().split("\\. ");
                year.setText(pp[0]);

            }else if (selected_book.getPublishedDate().contains("(")){
                final String pp[] = selected_book.getPublishedDate().split("\\(");
                year.setText(pp[0]);


            }else {
                year.setText(selected_book.getPublishedDate());
            }




            description.setText(selected_book.getDescription());
            country.setText(selected_book.getCountry());

            String the_page_count = String.valueOf(selected_book.getPageCount());
            pagecount.setText(the_page_count);

            info.setText("More Info here : " + selected_book.getInfoLink());

            Log.d("Fwiters", "onViewCreated: " + selected_book.getCategories());


            Log.d("Fwiters", "onViewCreated: " + selected_book.getAuthors());

            String theActors = selected_book.getAuthors();




            authors.setText(theActors);

            Log.d("Fratting", "onViewCreated: " + selected_book.getAverageRating());

            String getratting = selected_book.getAverageRating();

            if (getratting.contains(".")) {
                getratting = (String.valueOf(getratting.charAt(0)));
                Log.d("Fratting", "onViewCreated: 1 : " + getratting);

                String rat = getratting;



                if (rat.equals("5")){
                    ratting.setAnimation(R.raw.stars_5);

                }else if (rat.equals("4")){
                    ratting.setAnimation(R.raw.stars_4);

                }else if (rat.equals("3")){
                    ratting.setAnimation(R.raw.stars_3);

                }else if (rat.equals("2")){
                    ratting.setAnimation(R.raw.stars_2);

                }else if (rat.equals("1")){
                    ratting.setAnimation(R.raw.stars_1);

                }else {
                    // if == 0
                    ratting.setAnimation(R.raw.stars_0);


                }
                ratting.playAnimation();

            }



            ratting_button.setText(selected_book.getAverageRating());

            if (ratting_button.getText().equals("")) {
                ratting_button.setVisibility(View.GONE);
            }
            len.setText(selected_book.getLanguage());



            String image;
            image = selected_book.getImagesfront();
            book_image.setVisibility(View.VISIBLE);
            Log.d("Fimage", "onViewCreated: " + image);

            //Glide.with(getContext()).load(image).placeholder(R.drawable.ic_baseline_book_24).dontAnimate().into(book_image);


            Picasso.get().load(image)
                    .centerCrop()
                    .fit()
                    .into(book_image);


            //

            Single_one single_one = Single_one.getInstance();
            single_one.setNow_in_overview(selected_book);

            // set the recycler view

            Collections.shuffle(the_reviewsArrayList);


            recyclerView_REVIEWS.setLayoutManager(layoutManager);
            recyclerView_REVIEWS.setItemAnimator(new DefaultItemAnimator());

            adpterHORIZONTAL1 = new recycler_Adpter_REVIEWS(the_reviewsArrayList,getContext(), getActivity());
            recyclerView_REVIEWS.setAdapter(adpterHORIZONTAL1);

        }


    }





    }