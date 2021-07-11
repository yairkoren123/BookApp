package com.example.bookapp.fin;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Quotes;
import com.example.bookapp.frags_tabs.recycler_Adpter_Quotes;
import com.example.bookapp.ui.gallery.Search_book_Fragment;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;

public class Fragment_Quotes_Search extends Fragment {

    String URL = "https://www.goodreads.com/quotes";
    // https://www.goodreads.com/search?utf8=%E2%9C%93&q=see2&search_type=quotes
    String need_to_tag = "popular";
    String get_tag_search = "good";


    Fragment myFragment;

    String chip_tag = "";
    String a = "0";


    The_Quotes the_quotes = new The_Quotes();

    RecyclerView recyclerView1;

    recycler_Adpter_Quotes adpterVAR1;


    LinearLayoutManager layoutManager;

    ArrayList<The_Quotes> the_quotesArrayList = new ArrayList<>();


    public Fragment_Quotes_Search() {
        // Required empty public constructor
    }

    public Fragment_Quotes_Search(String chip_tag,String need_to_tag,String a) {
        this.chip_tag = chip_tag;
        this.need_to_tag = chip_tag;
        this.a = chip_tag;

    }

    public Fragment_Quotes_Search(String need_to_tag, String get_tag_search ) {
        this.need_to_tag = need_to_tag;
        this.get_tag_search = get_tag_search;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__quotes__search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (need_to_tag.equals("popular")){
            URL = "https://www.goodreads.com/quotes";
        }else if (need_to_tag.equals("search")){
            URL = "https://www.goodreads.com/search?utf8=%E2%9C%93&q="
                    +get_tag_search +"&search_type=quotes";
        }else if (!chip_tag.equals("")){
            URL = "https://www.goodreads.com/quotes/tag/" + chip_tag;
        }else {
            URL = "https://www.goodreads.com/quotes";
        }
        Log.d("theurl", "onViewCreated: " + URL);

        recyclerView1 = view.findViewById(R.id.recycler_quo);

        layoutManager = new LinearLayoutManager(
                getActivity(),LinearLayoutManager.VERTICAL
                ,false);

        Splash_screen_2 myFragment = new Splash_screen_2();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mail_countener8, myFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
        Log.d("fag", "run: 1");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("fag2", "run: 1");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(myFragment).commit();
                    }
                },1000);

            }
        },1000);



        des_back_Quotes des_back_quotes = new des_back_Quotes();
        des_back_quotes.execute();
        // code here

    }

    private class des_back_Quotes extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            // code here

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    start_tags();


                }
            });
            return null;

        }

        private void start_tags() {

            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);

                org.jsoup.nodes.Document doc = Jsoup.connect(URL)
                        .timeout(6000).get();

                Log.d("HTML1", "start_overview: " + doc.outerHtml());


                org.jsoup.select.Elements body = doc.select("div.leftContainer");


                Elements quo = body.select("div.quote");

                if (need_to_tag.equals("search")){
                    quo = body.select("table.tableList tr");
                }

                Log.d("tag_size", "start_tags: " + quo.size());
                int x;
                if (quo.size() >= 20){
                    x = 20;
                }else {
                    x = quo.size();
                }

                for (int i = 0; i < x; i++) {
                    the_quotes = new The_Quotes();
                    Element quote = quo.get(i);


                    String text_quo = quote.select("div.quoteText").text().toString();
                    Log.d("quo_text", "start_tags: " + text_quo);
                    the_quotes.setQuotes_text(text_quo);


                    String user = "";
                    if (text_quo.contains(" — ")) {
                        String pp[] = text_quo.split(" — ");
                        user = pp[1];
                        Log.d("quo_user", "start_tags:  1 " + user);

                    }else {
                        user = quote.select("span.authorOrTitle").text();
                        Log.d("quo_user", "start_tags: 2 " + user);
                    }


                    the_quotes.setUser(user);


                    String likes = quote.select("div.right a").text();
                    Log.d("quo_likes", "start_tags: " + likes);
                    the_quotes.setLikes(likes);



                    the_quotesArrayList.add(the_quotes);


                }
                Single_one single_one = Single_one.getInstance();

                Collections.shuffle(the_quotesArrayList);


                if (need_to_tag.equals("search")){


                    recyclerView1.setLayoutManager(layoutManager);
                    recyclerView1.setItemAnimator(new DefaultItemAnimator());

                    adpterVAR1 = new recycler_Adpter_Quotes(the_quotesArrayList,getContext(), getActivity(),get_tag_search);
                    recyclerView1.setAdapter(adpterVAR1);




                    single_one.setQuote_search_now(get_tag_search);
                }else {
                    single_one.setQuote_search_now("");

                    recyclerView1.setLayoutManager(layoutManager);
                    recyclerView1.setItemAnimator(new DefaultItemAnimator());

                    adpterVAR1 = new recycler_Adpter_Quotes(the_quotesArrayList,getContext(), getActivity());
                    recyclerView1.setAdapter(adpterVAR1);
                }




            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}