package com.example.bookapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.databinding.FragmentHomeBinding;
import com.example.bookapp.fin.Single_one;
import com.example.bookapp.fin.recycler_Adpter_HORIZONTAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    // count the recycler view
    int rec_now = 1;

    RecyclerView recyclerView1;




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



    ArrayList<String> Subject_Headings = new ArrayList();


    private FragmentHomeBinding binding;

    String the_random = "DESIGN";


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






//        int randomNumber=r.nextInt(Subject_Headings.length);
//        String the_random = Subject_Headings[randomNumber];


        Subject_Headings.add("ART");
        Subject_Headings.add("COMPUTERS");
        Subject_Headings.add("HISTORY");
        Subject_Headings.add("HUMOR");
        Subject_Headings.add("EDUCATION");
        Subject_Headings.add("FICTION");
        Subject_Headings.add("DESIGN");
        Subject_Headings.add("HOBBIES");

        getBooks();


    }

    private void getBooks() {

//
//        the_random = Subject_Headings.get(r.nextInt(COMPUTERS_array.size()));
//        Subject_Headings.remove(the_random);


        //URL_JSON = single_one.getJsonURL();
        // todo the random movie
//        Random r=new Random();
//        int randomNumber=r.nextInt(Subject_Headings.length);
//        the_random = Subject_Headings[randomNumber];
//
//        the_random = "COMPUTERS";
//        URL_JSON = "https://www.googleapis.com/books/v1/volumes?q=subject:"+the_random+"&maxResults=10";

        //https://www.googleapis.com/books/v1/volumes?q=subject:COMPUTERS&maxResults=10


        for (int i = 0; i < 5; i++) {

            Log.d("1forloop", "getBooks: " + 1);

//            Random r = new Random();
//            int randomNumber=r.nextInt(Subject_Headings.s);
//            the_random = Subject_Headings[randomNumber];


//            the_random = Subject_Headings.get(r.nextInt(COMPUTERS_array.size()));
//            Subject_Headings.remove(the_random);


            Random r = new Random();


            int index = new Random().nextInt(Subject_Headings.size()) ;

            the_random = Subject_Headings.get(index);

            Log.d("1random", "getBooks: " + the_random);


            URL_JSON = "https://www.googleapis.com/books/v1/volumes?q=subject:" + the_random + "&maxResults=10";

            Subject_Headings.remove(index);
            the_remove_headline.add(the_random);

            Log.d("1sizes", "getBooks: " + Subject_Headings.size());


            the_book = new The_Book();






            //the_random = "Computers";




            Log.d("URLS", "getBooks: " + URL_JSON);



            requestQueue = Volley.newRequestQueue(getContext());

            JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, URL_JSON, null,
                    // The third parameter Listener overrides the method onResponse() and passes
                    //JSONObject as a parameter
                    new Response.Listener<JSONObject>() {

                        // Takes the response from the JSON request
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                // make new the book :

                                the_book = new The_Book();

                                JSONObject jsonObject = new JSONObject();
                                JSONArray jsonArray = response.getJSONArray("items");
                                Log.d("fromurl", "onResponse: " + jsonArray.length());



                                // use jsonArray.length to get all
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    the_book = new The_Book();





                                    jsonObject = jsonArray.getJSONObject(i);

                                    Log.d("fromurlget", "onResponse: " + jsonObject.getString("etag"));

                                    JSONObject thevolumeInfo = jsonObject.getJSONObject("volumeInfo");

                                    // get title
                                    String the_title = "";
                                    try {
                                        the_title = thevolumeInfo.getString("title");
                                        Log.d("1title", "onResponse: " + the_title);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    // get authors
                                    String the_authors = "";
                                    try {
                                        String data = "";
                                        String the_count_authors = thevolumeInfo.getString("authors");
                                        // todo you know what ... ["Harvey M. Deitel","Paul J. Deitel"] ... split the names . with for loop??
                                        Log.d("1countaut", "onResponse: " + the_count_authors);

                                    } catch (Exception e) {
                                        the_authors = "none";
                                        e.printStackTrace();
                                    }

                                    // get publishedDate
                                    String the_publishedDate = "";
                                    try {
                                        the_publishedDate = thevolumeInfo.getString("publishedDate");
                                        Log.d("1date", "onResponse: " + the_publishedDate);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    // getting the images
                                    String the_image_front = "";
                                    String the_image_back = "";
                                    try {
                                        JSONObject images_links = thevolumeInfo.getJSONObject("imageLinks");
                                        Log.d("1imageLinks1", "onResponse: " + images_links.getString("smallThumbnail"));
                                        the_image_front = images_links.getString("smallThumbnail");
                                        Log.d("1imageLinks2", "onResponse: " + images_links.getString("smallThumbnail"));
                                        the_image_back = images_links.getString("thumbnail");
                                    } catch (Exception e) {
                                        // todo the image if he found no image
                                        the_image_front = "https://www.eduprizeschools.net/wp-content/uploads/2016/06/No_Image_Available.jpg";
                                        the_image_back = "https://www.eduprizeschools.net/wp-content/uploads/2016/06/No_Image_Available.jpg";
                                        e.printStackTrace();
                                    }

                                    // get language
                                    String the_len = "";
                                    try {
                                        the_len = thevolumeInfo.getString("language");
                                        Log.d("1len", "onResponse: " + the_len);
                                    } catch (Exception e) {
                                        the_len = "8080";
                                        e.printStackTrace();
                                    }

                                    // get pageCount
                                    int the_pageCount = 0;
                                    try {
                                        the_pageCount = thevolumeInfo.getInt("pageCount");
                                        Log.d("1page", "onResponse: " + the_pageCount);
                                    } catch (Exception e) {
                                        the_pageCount = 8080;
                                        e.printStackTrace();
                                    }

                                    // get description

                                    String the_description = "";
                                    try {
                                        the_description = thevolumeInfo.getString("description");
                                        Log.d("1description", "onResponse: " + the_description);
                                    } catch (Exception e) {
                                        the_description = "none";
                                        e.printStackTrace();
                                    }

                                    // get country
                                    String the_country = "";
                                    try {
                                        JSONObject jsonObject1 = jsonObject.getJSONObject("saleInfo");
                                        the_country = jsonObject1.getString("country");
                                        Log.d("1country", "onResponse: " + the_country);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    // get categories
                                    String the_categories = "";
                                    try {
                                        // todo you know what ... ["Computers"] ... split the names . with for loop??
                                        the_categories = thevolumeInfo.getString("categories");
                                        Log.d("1categories", "onResponse: " + the_categories);
                                    } catch (Exception e) {
                                        the_categories = "none";
                                        e.printStackTrace();
                                    }


                                    // get averageRating
//                                 int the_averageRating = thevolumeInfo.getInt("averageRating");
//                                 the_AVG_now = String.valueOf(the_averageRating);
//
//                                Log.d("1averageRating", "onResponse: "+the_averageRating);


                                    // get ratingsCount
//                                int the_ratingsCount = thevolumeInfo.getInt("ratingsCount");
//                                Log.d("1ratingsCount", "onResponse: "+ the_ratingsCount);

                                    // get infoLink
                                    // todo try and cahte
                                    String the_infoLink = "";
                                    try {
                                        the_infoLink = thevolumeInfo.getString("infoLink");
                                        Log.d("1infoLink", "onResponse: " + the_infoLink);
                                    } catch (Exception e) {
                                        the_infoLink = "none";
                                        e.printStackTrace();
                                    }


                                    JSONArray industryIdentifiers = new JSONArray();

                                    String the_get_ISBN_13 = "";
                                    try {
                                        industryIdentifiers = thevolumeInfo.getJSONArray("industryIdentifiers");
                                        the_get_ISBN_13 = industryIdentifiers.getJSONObject(0).getString("identifier");
                                        Log.d("1industryIdentifiers", "onResponse: " + the_get_ISBN_13);
                                    } catch (Exception e) {
                                        the_get_ISBN_13 = "9780465094639";
                                        e.printStackTrace();
                                    }


                                    //String the_isbn13 = "9780316337977";
                                    // get the avg


                                    String JSON_URL = "https://www.goodreads.com/book/review_counts.json?key={apikey}&isbns=9780316337977";

                                    JSON_URL = "https://www.goodreads.com/book/review_counts.json?key=&isbns=" + the_get_ISBN_13;

                                    Log.d("the_avg_url", "onResponse: " + JSON_URL);

                                    try {

                                        requestQueue2 = Volley.newRequestQueue(getContext());

                                        JsonObjectRequest obreq2 = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject();
                                                            JSONArray jsonArray = response.getJSONArray("books");
                                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                                jsonObject = jsonArray.getJSONObject(i);
                                                                String the_isbn13 = jsonObject.getString("isbn13");

                                                                // get the avg
                                                                String the_avg2 = jsonObject.getString("average_rating");

                                                                Log.d("1avg1", "onResponse: " + the_avg2);
                                                                the_AVG_now = the_avg2;

                                                                the_book.setAverageRating(the_AVG_now);
                                                            }
                                                        } catch (JSONException e) {
                                                            // If an error occurs, this prints the error to the log
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            // Handles errors that occur due to Volley
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("Volley", "Error");
                                            }
                                        }
                                        );
                                        requestQueue2.add(obreq2);

                                        Log.d("1AVG_now", "onResponse: " + the_AVG_now);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (the_AVG_now.equals("")) {
                                        try {

                                            int the_averageRating = thevolumeInfo.getInt("averageRating");
                                            the_AVG_now = String.valueOf(the_averageRating);
                                            Log.d("1averageRating", "onResponse: " + the_averageRating);
                                        }catch (Exception e){
                                            if (the_AVG_now.equals("")) {
                                                the_AVG_now = "8080";
                                                the_book.setAverageRating(the_AVG_now);
                                                e.printStackTrace();
                                            }
                                        }
                                    }



                                    // set to the book Class (setAverageRating is set above in volley )


                                    if (is_first == true){



                                            Log.d("1is_first", "onResponse: " + the_remove_headline.get(rec_now-1));
                                            the_book.setTitle(the_remove_headline.get(rec_now-1));
                                            the_book.setPublishedDate("");
                                            the_book.setLanguage("");
                                            the_book.setCategories("");
                                            the_book.setCountry("");
                                            the_book.setDescription("");
                                            the_book.setPageCount(0);
                                            the_book.setImagesfront("");
                                            the_book.setImageback("");
                                            the_book.setInfoLink("");
                                            the_book.setAuthors("");
                                            COMPUTERS_array.add(the_book);

                                    }
                                    the_book = new The_Book();




                                    the_book.setTitle(the_title);
                                    the_book.setPublishedDate(the_publishedDate);
                                    the_book.setLanguage(the_len);
                                    the_book.setCategories(the_categories);
                                    the_book.setCountry(the_country);
                                    the_book.setDescription(the_description);
                                    the_book.setPageCount(the_pageCount);
                                    the_book.setImagesfront(the_image_front);
                                    the_book.setImageback(the_image_back);
                                    the_book.setInfoLink(the_infoLink);
                                    the_book.setAuthors(the_authors);



                                    COMPUTERS_array.add(the_book);
                                    is_first = false;



                                }
                                // call the recycler view and the adapter
                                call_rec();
                            } catch (JSONException e) {
                                // If an error occurs, this prints the error to the log
                                e.printStackTrace();
                            }

                        }
                    },
                    // The final parameter overrides the method onErrorResponse() and passes VolleyError
                    //as a parameter
                    new Response.ErrorListener() {
                        @Override
                        // Handles errors that occur due to Volley
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "Error");
                        }
                    }
            );

            requestQueue.add(obreq);
        }

    }

    private void call_rec(){
        Log.d("1switch", "call_rec: now in " + rec_now);

        LinearLayoutManager layoutManager;
        recycler_Adpter_HORIZONTAL adpterHORIZONTAL;


        switch (rec_now){
            case 1:
                recyclerView1 = getView().findViewById(R.id.recycler_view_1);

                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());

                adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(COMPUTERS_array,getContext());
                recyclerView1.setAdapter(adpterHORIZONTAL);


                Log.d("1switch", "call_rec: now in switch 1 ");

                break;
            case 2:
                recyclerView1 = getView().findViewById(R.id.recycler_view_2);


                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());

                adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(COMPUTERS_array,getContext());
                recyclerView1.setAdapter(adpterHORIZONTAL);


                Log.d("1switch", "call_rec: now in switch  2");

                break;
            case 3:
                Log.d("1switch", "call_rec: now in switch  3");

                recyclerView1 = getView().findViewById(R.id.recycler_view_3);
                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());

                adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(COMPUTERS_array,getContext());
                recyclerView1.setAdapter(adpterHORIZONTAL);
                break;

            case 4:
                Log.d("1switch", "call_rec: now in switch  4");

                recyclerView1 = getView().findViewById(R.id.recycler_view_4);
                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());

                adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(COMPUTERS_array,getContext());
                recyclerView1.setAdapter(adpterHORIZONTAL);
                break;

            case 5:
                Log.d("1switch", "call_rec: now in switch  5");

                recyclerView1 = getView().findViewById(R.id.recycler_view_5);
                layoutManager = new LinearLayoutManager(
                        getActivity(),LinearLayoutManager.HORIZONTAL
                        ,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());

                adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(COMPUTERS_array,getContext());
                recyclerView1.setAdapter(adpterHORIZONTAL);
                break;

        }

        rec_now+= 1;
        COMPUTERS_array = new ArrayList<>();
        is_first = true;


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