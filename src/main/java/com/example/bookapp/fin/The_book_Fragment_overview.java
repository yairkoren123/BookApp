package com.example.bookapp.fin;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Random;


public class The_book_Fragment_overview extends Fragment {

    The_Book selected_book = new The_Book();


    // layout
    TextView title,year,description,len,pagecount,info,authors, category,ratting , ratting_button, country;
    ImageView book_image;



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


        title = view.findViewById(R.id.title_overview);
        year = view.findViewById(R.id.year_overview);
        description = view.findViewById(R.id.des_overview);
        pagecount = view.findViewById(R.id.pagecount_overview);
        info = view.findViewById(R.id.moreinfo_overview);
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



            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);

                Log.d("click_url_ready", "start_overview: " + selected_book.getClick_URL());
                org.jsoup.nodes.Document doc = Jsoup.connect(selected_book.getClick_URL())
                        .timeout(6000).get();


                org.jsoup.select.Elements body = doc.select("div.leftContainer");

                String title_overview = body.select("h1.gr-h1").text();
                String dsc_overview = body.select("div.readable span").text();
                String ratting_over_view = body.select("a.gr-hyperlink meta").attr("content");
                String people_count_ratting = body.select("a.gr-hyperlink meta").attr("content");
                String len = body.select("div.infoBoxRowItem").text();



                selected_book.setPeople_ratting(people_count_ratting);

                Log.d("ratting_people", "start_overview: " + ratting_over_view);
                Log.d("len_over", "start_overview: " + len);




                selected_book.setDescription(dsc_overview);


                Log.d("over_get_title", "onCreate: book  : " +title_overview + " stars : "  + " des +++" + dsc_overview);







                // the size
                Log.d("qqq", "onCreate: " + body.size());
                //Log.d("qqq", "onCreate: " + body.select("div.coverWrapper img")
                //.attr("alt"));

//                for (Element e : body.select("div") ) {
//                for (int i = 0; i < body.select("div").size(); i++) {
            }catch (Exception e ){
                e.printStackTrace();
            }


            Log.d("Fwork", "onViewCreated: " + selected_book.getTitle());



            title.setText(selected_book.getTitle());
            year.setText(selected_book.getPublishedDate());
            description.setText(selected_book.getDescription());
            country.setText(selected_book.getCountry());

            String the_page_count = String.valueOf(selected_book.getPageCount());
            pagecount.setText(the_page_count);

            info.setText(selected_book.getInfoLink());

            Log.d("Fwiters", "onViewCreated: " + selected_book.getCategories());


            Log.d("Fwiters", "onViewCreated: " + selected_book.getAuthors());

            String theActors = selected_book.getAuthors();

            theActors = theActors.replace(",", " , ");
            theActors = theActors.replace("[", " ");
            theActors = theActors.replace("]", " ");


            authors.setText(theActors);

            Log.d("Fratting", "onViewCreated: " + selected_book.getAverageRating());

            String getratting = selected_book.getAverageRating();

            if (getratting.contains(".")) {
                getratting = (String.valueOf(getratting.charAt(0)));
                Log.d("Fratting", "onViewCreated: 1 : " + getratting);


            }


            int theRatting = 0;
            if (getratting.equals("")) {
                theRatting = 0;
            } else {
                theRatting = Integer.parseInt(getratting);
            }

            ratting.setText(theRatting + "");
            ratting_button.setText(selected_book.getAverageRating());

            if (ratting_button.getText().equals("")) {
                ratting_button.setVisibility(View.GONE);
            }
            len.setText(selected_book.getLanguage());


            // todo set the image

            String image;
            image = selected_book.getImagesfront();
            book_image.setVisibility(View.VISIBLE);
            Log.d("Fimage", "onViewCreated: " + image);

            //Glide.with(getContext()).load(image).placeholder(R.drawable.ic_baseline_book_24).dontAnimate().into(book_image);


            Picasso.get().load(image)
                    .centerCrop()
                    .fit()
                    .into(book_image);


        }
    }





    }