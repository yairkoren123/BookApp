package com.example.bookapp.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.bookapp.MainActivity;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.databinding.FragmentHomeBinding;
import com.example.bookapp.fin.Loadding_book_activity;
import com.example.bookapp.fin.Single_one;
import com.example.bookapp.fin.Splash_screen;
import com.example.bookapp.fin.The_book_Fragment_overview;
import com.example.bookapp.fin.recycler_Adpter_HORIZONTAL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    // main

    private HomeViewModel homeViewModel;
    // count the recycler view
    int rec_now = 1;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    RecyclerView recyclerView5;

    recycler_Adpter_HORIZONTAL adpterHORIZONTAL1;
    recycler_Adpter_HORIZONTAL adpterHORIZONTAL2;
    recycler_Adpter_HORIZONTAL adpterHORIZONTAL3;
    recycler_Adpter_HORIZONTAL adpterHORIZONTAL4;
    recycler_Adpter_HORIZONTAL adpterHORIZONTAL5;

    LinearLayoutManager layoutManager;




    The_Book the_book = new The_Book();

    boolean is_first = true;

    RequestQueue requestQueue;
    RequestQueue requestQueue2;





    String URL_JSON = "";
    String the_AVG_now = "";

    private Single_one single_one;

    ArrayList<The_Book> COMPUTERS_array = new ArrayList<>();

    ArrayList<The_Book> DESIGN_array = new ArrayList<>();

    ArrayList<String> the_remove_headline = new ArrayList<>();

    String last_value = "";
    Splash_screen myFragment;



    ArrayList<String> Subject_Headings = new ArrayList();




    private FragmentHomeBinding binding;

    String the_random = "DESIGN";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        Log.d("meme", "onCreateView: ");



        recyclerView1 = binding.recyclerView1;
        recyclerView2 = binding.recyclerView2;
        recyclerView3 = binding.recyclerView3;
        recyclerView4 = binding.recyclerView4;
        recyclerView5 = binding.recyclerView5;


        layoutManager = new LinearLayoutManager(
                getActivity(),LinearLayoutManager.HORIZONTAL
                ,false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });



//        des_back des_back = new des_back();
//        des_back.execute();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Single_one single_one = Single_one.getInstance();
        single_one.setSavedInstanceState(savedInstanceState);






//        int randomNumber=r.nextInt(Subject_Headings.length);
//        String the_random = Subject_Headings[randomNumber];


        String[] cat = new String[]
                {"Fantasy","History","Horror","Music","Mystery","Sports","Travel"};

        for (String head : cat){
            Subject_Headings.add(head);
        }

        //getBooks();

    }

    @Override
    public void onResume() {
        Log.d("resu", "onResume: 1" );

        Subject_Headings = new ArrayList<>();

        String[] cat = new String[]
                {"Fantasy","History","Horror","Music","Mystery","Sports","Travel"};

        for (String head : cat){
            Subject_Headings.add(head);
        }

        rec_now = 1;

        myFragment = new Splash_screen();
        getActivity().getSupportFragmentManager()
                .beginTransaction().add(R.id.mail_countener1, myFragment)
                .addToBackStack(null).commit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                des_back des_back = new des_back();
                des_back.execute();
                Log.d("fag1", "run: 1");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("fag22", "run: 1");
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .remove(myFragment).commit();

                    }
                },1000);

            }
        },2000);

        super.onResume();

    }

    private class des_back extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {





//            //org.jsoup.nodes.Document doc = null;
//            try {
//                org.jsoup.nodes.Document doc = Jsoup.connect("https://www.imdb.com/chart/top")
//                        .get();
//                org.jsoup.select.Elements body = doc.select("tbody.lister-list");
//
//                // the size
//                //Log.d("ppp", "onCreate: " + body.select("tr").size());
//
//                for (Element e : body.select("tr") ){
//
//                    String img = e.select("td.posterColumn img").attr("src");
//                    String title = e.select("td.posterColumn img").attr("alt");
//
//
//                    Log.d("ppp", "onCreate: " + title);
//
//
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            // getting from search text and click
//            try {
//                org.jsoup.nodes.Document doc = Jsoup.connect("https://www.goodreads.com/search?utf8=%E2%9C%93&q=java&search_type=books&search%5Bfield%5D=on")
//                        .get();
//                org.jsoup.select.Elements body = doc.select("table.tableList");
//
//
//                // the size
//                Log.d("ppp", "onCreate: " + body.select("tr").size());
//
//                for (Element e : body.select("tr") ){
//
//                    String img = e.select("td a").attr("title");
//                    //String title = e.select("td.posterColumn img").attr("alt");
//
//
//
//                    String url_click = e.select("td a").attr("href");
//                    get_by_click(url_click);
//
//                    Log.d("ppp", "onCreate: " + img);
//
//                    Log.d("ppp", "onCreate123: https://www.goodreads.com/" + url_click);
//
//
//
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {


                    for (int i = 0; i < 4; i++) {


                        Random r = new Random();


                        int index = new Random().nextInt(Subject_Headings.size());
                        Log.d("1random", "getBooks: " + index + " u " +Subject_Headings.size());

                        the_random = Subject_Headings.get(index);

                        Log.d("1random", "getBooks: " + the_random);
                        String url = "https://www.goodreads.com/genres/most_read/"+the_random;

                        get_by_cat(url,the_random);

                        Subject_Headings.remove(index);
                    }


                }
            });

            return null;
        }

        private void get_by_click(String url){

            Log.d("click1", "get_by_click: " + url);

        }

        private void get_by_cat(String url,String random){

            try {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);


                org.jsoup.nodes.Document doc = Jsoup.connect(url)
                        .timeout(6000).get();


                org.jsoup.select.Elements body = doc.select("div.bigBoxBody");


                // the size
                Log.d("qqq", "onCreate: " + body.size());
                //Log.d("qqq", "onCreate: " + body.select("div.coverWrapper img")
                //.attr("alt"));

//                for (Element e : body.select("div") ) {
//                for (int i = 0; i < body.select("div").size(); i++) {

                for (int i = 0; i < 15; i++) {

                    Element e = body.select("div").get(i);

                    recyclerView1 = binding.recyclerView1;

                    The_Book the_book = new The_Book();

                    String title = e.select("div.coverWrapper img").attr("alt");
                    boolean is_there = false;
                    Log.d("eee", "onCreate: " + title);


                    for (The_Book s : COMPUTERS_array){
                        if (s.getTitle().equals(the_book.getTitle())){
                            is_there = true;
                        }else if (last_value.equals(title)){
                            is_there = true;
                        }else if (title.equals("")){
                            is_there = true;
                        }
                    }
                    if (is_there != true){

                        //String title = e.select("td.posterColumn img").attr("alt");

                        String img = e.select("div.coverWrapper img").attr("src");
                        String g_click_url_item = e.select("div.coverWrapper a").attr("href");
                        String click_url_item = "https://www.goodreads.com/" + g_click_url_item;
                        Log.d("ddd", "onCreate: " + click_url_item);

                        the_book.setClick_URL(click_url_item);
                        the_book.setTitle(title);
                        the_book.setImagesfront(img);


                        Log.d("ppp", "onCreate: " + img);

                        last_value = title;


                        COMPUTERS_array.add(the_book);


                    }
                }


                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);

                Log.d("towaitch", "get_by_cat: " + rec_now);
                    switch (rec_now){
                        case 1:

                            ArrayList<The_Book> try1 = COMPUTERS_array;

                            recyclerView1.setLayoutManager(layoutManager);
                            recyclerView1.setItemAnimator(new DefaultItemAnimator());

                            adpterHORIZONTAL1 = new recycler_Adpter_HORIZONTAL(try1,getContext(),getActivity());
                            recyclerView1.setAdapter(adpterHORIZONTAL1);


                            Log.d("1switch", "call_rec: now in switch 1 ");

                            TextView textView1 = binding.textView1;


                            textView1.setText(random);
                            break;
                        case 2:

                            ArrayList<The_Book> try2 = COMPUTERS_array;

                            recyclerView2.setLayoutManager(layoutManager);
                            recyclerView2.setItemAnimator(new DefaultItemAnimator());

                            adpterHORIZONTAL2 = new recycler_Adpter_HORIZONTAL(try2,getContext(),getActivity());
                            recyclerView2.setAdapter(adpterHORIZONTAL2);


                            Log.d("1switch", "call_rec: now in switch 2 ");

                            TextView textView2 = binding.textView2;


                            textView2.setText(random);
                            break;

                        case 3:

                            ArrayList<The_Book> try3 = COMPUTERS_array;

                            recyclerView3.setLayoutManager(layoutManager);
                            recyclerView3.setItemAnimator(new DefaultItemAnimator());

                            adpterHORIZONTAL3 = new recycler_Adpter_HORIZONTAL(try3,getContext(),getActivity());
                            recyclerView3.setAdapter(adpterHORIZONTAL3);


                            Log.d("1switch", "call_rec: now in switch 3 ");

                            TextView textView3 = binding.textView3;


                            textView3.setText(random);
                            break;

                        case 4:

                            ArrayList<The_Book> try4 = COMPUTERS_array;

                            recyclerView4.setLayoutManager(layoutManager);
                            recyclerView4.setItemAnimator(new DefaultItemAnimator());

                            adpterHORIZONTAL4 = new recycler_Adpter_HORIZONTAL(try4,getContext(),getActivity());
                            recyclerView4.setAdapter(adpterHORIZONTAL4);


                            Log.d("1switch", "call_rec: now in switch 4 ");

                            TextView textView4 = binding.textView4;


                            textView4.setText(random);
                            break;

                        case 5:

                            ArrayList<The_Book> try5 = COMPUTERS_array;

                            recyclerView5.setLayoutManager(layoutManager);
                            recyclerView5.setItemAnimator(new DefaultItemAnimator());

                            adpterHORIZONTAL5 = new recycler_Adpter_HORIZONTAL(try5,getContext(),getActivity());
                            recyclerView5.setAdapter(adpterHORIZONTAL5);


                            Log.d("1switch", "call_rec: now in switch 5 ");

                            TextView textView5 = binding.textView5;


                            textView5.setText(random);
                            break;



                    }
                // after switch
                    rec_now++;
                    COMPUTERS_array = new ArrayList<>();


//                    recyclerView1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                            Log.d("4scrool", "onScrollChange:  " + scrollX + " old : " + oldScrollX);
//                            if (oldScrollX != 0) {
//                                The_Book book1 = new The_Book();
//                                book1 = try1.get(0);
//
//                                if (book1.getCategories().equals("")
//                                        && book1.getDescription().equals("")
//                                        && book1.getInfoLink().equals("")) {
//
//                                    try1.remove(book1);
//                                    recycler_Adpter_HORIZONTAL adpterHORIZONTAL4 = new recycler_Adpter_HORIZONTAL(try1, getContext(), getActivity());
//                                    recyclerView1.setAdapter(adpterHORIZONTAL4);
//                                    adpterHORIZONTAL4.notifyDataSetChanged();
//
//                                    final TextView textView4 = binding.textView1;
//
//                                    Animation animate = AnimationUtils.loadAnimation(getContext(),
//                                            R.anim.fade);
//                                    textView4.setVisibility(View.VISIBLE);
//                                    Log.d("animnow", "onClick: new");
//                                    textView4.startAnimation(animate);
//
////                                }
//                            }
//                        }
//                    });


                    //call_rec();

                    //Log.d("ppp", "onCreate123: https://www.goodreads.com/" + url_click);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void msg(String text){
        Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT)
                .show();
    }

}