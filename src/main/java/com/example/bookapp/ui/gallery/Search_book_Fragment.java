package com.example.bookapp.ui.gallery;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.fin.The_book_Fragment_overview;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Search_book_Fragment extends Fragment {

    String text_to_search ="";
    String URL = "";
    ArrayList<The_Book> the_bookArrayList =  new ArrayList<>();
    The_Book the_book = new The_Book();
    boolean is_there = false;
    ListAdapter customAdapter;

    LottieAnimationView anim_stars;


    int search_size = 15;
    boolean stars_there[] = new boolean[search_size];


    public Search_book_Fragment() {
        // Required empty public constructor
    }

    public Search_book_Fragment(String URL) {
        this.URL = URL;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_book_, container, false);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("2tag", "onCreate: " + URL);

        des_back des_back = new des_back();
        des_back.execute();
    }
    private class des_back extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                        get_by_search(URL);
                }
            });

            return null;
        }
    }

    private void get_by_search(String url) {

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);


            org.jsoup.nodes.Document doc = Jsoup.connect(url)
                    .timeout(6000).get();


            org.jsoup.select.Elements body = doc.select("table.tableList tr");

            Log.d("2size", "get_by_search: " + body.size());


            for (int i = 0; i < search_size; i++) {
                is_there = false;
                the_book = new The_Book();
                Element g = body.get(i);
                Log.d("2now", "get_by_search: " + g.select("a.bookTitle span").text());
                String title = g.select("a.bookTitle span").text();

                for (The_Book title_in_array : the_bookArrayList) {
                    if (title_in_array.getTitle().equals(title)) {
                        is_there = true;
                        continue;
                    }
                }
                if (is_there == false) {

                    the_book.setTitle(title);

                    String click_url = "https://www.goodreads.com" + g.select("a.bookTitle").attr("href");
                    Log.d("2url", "get_by_search: " + click_url);
                    the_book.setClick_URL(click_url);

                    String img = g.select("img.bookCover").attr("src");
                    Log.d("2image", "get_by_search: " + img);

                    if (img.contains("._SX50_")){
                        img = img.replace("._SX50_","");
                    }else if(img.contains("._SY75_")){
                        img = img.replace("._SY75_","");
                    }

                    the_book.setImagesfront(img);

                    Elements avg_ratting_el = g.select("span.minirating");
                    Log.d("2avg", "get_by_search: " + avg_ratting_el.size());
                    Log.d("2avg2", "get_by_search: " + avg_ratting_el.text());
                    String the_ratting = avg_ratting_el.text();

                    if (the_ratting.contains(" — ")) {
                        String qq[] = avg_ratting_el.text().split(" — ");
                        the_ratting = qq[0].toString();

                        String the_count_ratting = qq[1];
                        the_book.setPeople_ratting(the_count_ratting);
                    } else {
                        the_ratting = "";
                    }

                    the_book.setAverageRating(the_ratting);


                    String the_authors = g.select("a.authorName span").text();
                    Log.d("2authors", "get_by_search: " + the_authors);
                    the_book.setAuthors(the_authors);

                    String date = g.select("span.uitext").text();
                    Log.d("2date", "get_by_search: " + date);
                    if (date.contains("published")) {
                        String pp[] = date.split(" — ");
                        if (pp.length >= 2) {
                            date = pp[2];
                        } else {
                            date = "";
                        }
                    } else {
                        date = "";
                    }


                    // add book to the list
                    the_bookArrayList.add(the_book);
                } else {
                    continue;
                }

            }
            Log.d("2array", "get_by_search: " + the_bookArrayList.size());

            ListView yourListView = (ListView) getView().findViewById(R.id.itemListView_book);

// get data from the table by the ListAdapter
            customAdapter = new ListAdapter(getContext(),R.layout.search_item,the_bookArrayList);

            yourListView.setAdapter(customAdapter);

            yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("s_pos", "onItemClick: " + position);

                    The_Book send_book =  the_bookArrayList.get(position);

                    The_book_Fragment_overview nextFrag = new The_book_Fragment_overview(send_book);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mail_countener33, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });




        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public class ListAdapter extends ArrayAdapter<The_Book> {

        private int resourceLayout;
        private Context mContext;



        public ListAdapter(Context context, int resource, ArrayList<The_Book> items) {
            super(context, resource, items);
            this.resourceLayout = resource;
            this.mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;





            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(resourceLayout, null);
            }

            The_Book p = getItem(position);

            Log.d("adpter_2_p", "getView: " + p);

            if (p != null) {
                TextView book_name = (TextView) v.findViewById(R.id.book_name_s);
                TextView ratting_book = (TextView) v.findViewById(R.id.ratting_s);
                TextView by_book = (TextView) v.findViewById(R.id.by_s);
                ImageView image_front = v.findViewById(R.id.image_front_s);
                LottieAnimationView anim_stars = v.findViewById(R.id.stars_s);



                book_name.setText(p.getTitle());
                ratting_book.setText(p.getAverageRating());
                by_book.setText(p.getDescription());

                Picasso.get().load(p.getImagesfront())
                        .centerCrop()
                        .fit()
                        .into(image_front);

                // todo the stars
                String ratting_for_stars = p.getAverageRating();

                Log.d("wowow", "getView: " + ratting_for_stars.charAt(0));
                String rat = String.valueOf(ratting_for_stars.charAt(0));




                if (rat.equals("5")){
                    anim_stars.setAnimation(R.raw.stars_5);

                }else if (rat.equals("4")){
                    anim_stars.setAnimation(R.raw.stars_4);

                }else if (rat.equals("3")){
                    anim_stars.setAnimation(R.raw.stars_3);

                }else if (rat.equals("2")){
                    anim_stars.setAnimation(R.raw.stars_2);

                }else if (rat.equals("1")){
                    anim_stars.setAnimation(R.raw.stars_1);

                }else {
                    // if == 0
                    anim_stars.setAnimation(R.raw.stars_0);


                }
                anim_stars.playAnimation();

                for (boolean str : stars_there){
                    if (str == true){

                    }else {
                        stars_there[position] = true;
                        anim_stars.playAnimation();


                    }
                }


            }

            return v;
        }

    }




}