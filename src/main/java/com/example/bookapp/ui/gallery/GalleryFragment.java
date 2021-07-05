package com.example.bookapp.ui.gallery;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.databinding.FragmentGalleryBinding;
import com.example.bookapp.fin.Single_one;
import com.example.bookapp.fin.Splash_screen;
import com.example.bookapp.ui.home.HomeFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GalleryFragment extends Fragment {
    // search


    String URL = "https://www.goodreads.com/search?utf8=%E2%9C%93&q=java&search_type=books";
    String text_to_search = "";
    The_Book the_book = new The_Book();
    boolean is_there = false;

    Fragment myFragment;

    ImageButton imageButton;
    EditText to_search_view;

    Single_one single_one = Single_one.getInstance();


    ArrayList<The_Book> the_bookArrayList = new ArrayList<>();

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        single_one = Single_one.getInstance();
        single_one.setNow_drow("search");

        ImageButton imageButton = binding.imagebuttonSearch;
        EditText to_search_view = binding.editTextSearch;


        Log.d("2tag", "onCreate: "+ single_one.getNow_drow());



        myFragment = new Splash_screen();
        getActivity().getSupportFragmentManager()
                .beginTransaction().add(R.id.mail_countener4, myFragment)
                .addToBackStack(null).commit();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_to_search = to_search_view.getText().toString().trim();
                if (text_to_search.equals("")){
                    text_to_search = "python";
                }else {
                    if (text_to_search.contains(" ")){
                        text_to_search.replace(" ","+");
                    }
                    URL = "https://www.goodreads.com/search?utf8=%E2%9C%93&q=" + text_to_search + "&search_type=books";

                    Search_book_Fragment nextFrag = new Search_book_Fragment(URL);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mail_countener2, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();

                    hideKeyboard(getActivity());

                }
            }
        });




        // todo get text from search --
        text_to_search = "python";
        URL =  "https://www.goodreads.com/search?utf8=%E2%9C%93&q=" +text_to_search+ "&search_type=books";


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Search_book_Fragment nextFrag = new Search_book_Fragment(URL);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mail_countener2, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                Log.d("fag", "run: 1");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("fag2", "run: 1");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(myFragment).commit();
                    }
                },1000);

            }
        },1000);

        return root;


    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}