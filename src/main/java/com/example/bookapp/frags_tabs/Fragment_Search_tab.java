package com.example.bookapp.frags_tabs;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bookapp.R;
import com.example.bookapp.fin.Fragment_Quotes_Search;

import org.jetbrains.annotations.NotNull;


public class Fragment_Search_tab extends Fragment {

    String text = "";
    ImageButton imageButton;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__search_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // code here

        text = "life";
        Fragment_Quotes_Search myFragment = new Fragment_Quotes_Search("search",text);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.mail_countener6, myFragment)
                            .addToBackStack(null).commit();



        imageButton = view.findViewById(R.id.imageButton_search_qoutes);
        editText = view.findViewById(R.id.search_quote_se);

        editText.setText("");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = String.valueOf(editText.getText());
                if (!text.equals("") || !text.equals(" ")){
                    editText.setText("");
                    hideKeyboard(getActivity());
                    Fragment_Quotes_Search myFragment = new Fragment_Quotes_Search("search",text);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.mail_countener6, myFragment)
                            .addToBackStack(null).commit();
                }else {
                    Log.d("nonono", "onClick:  empty");
                    editText.setText("");

                    // msg class
                }

            }
        });
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
}