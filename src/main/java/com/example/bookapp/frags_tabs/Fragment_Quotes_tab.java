package com.example.bookapp.frags_tabs;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.bookapp.R;
import com.example.bookapp.fin.Fragment_Quotes_Search;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment_Quotes_tab extends Fragment {

    private Chip chip1,chip2,chip3,chip4,chip5,chip6,chip7,chip8,chip9;

    private CompoundButton last_button ;

    ArrayList<String> selectedChipData = new ArrayList<>();

    public Fragment_Quotes_tab() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__qoutes_tab, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // code here
        chip1 = view.findViewById(R.id.chip_1);
        chip2 = view.findViewById(R.id.chip_2);
        chip3 = view.findViewById(R.id.chip_3);
        chip4 = view.findViewById(R.id.chip_4);
        chip5 = view.findViewById(R.id.chip_5);
        chip6 = view.findViewById(R.id.chip_6);
        chip7 = view.findViewById(R.id.chip_7);
        chip8 = view.findViewById(R.id.chip_8);
        chip9 = view.findViewById(R.id.chip_9);


        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){


                    String chip_tag = buttonView.getText().toString();
                    selectedChipData.add(chip_tag);
                    Log.d("chip_s", "onCheckedChanged: " + chip_tag);

                    Fragment_Quotes_Search myFragment = new Fragment_Quotes_Search(chip_tag,chip_tag,chip_tag);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.mail_countener7, myFragment)
                            .addToBackStack(null).commit();

                    if (last_button != null){
                        last_button.setTextColor(Color.parseColor("#ffffff"));
                        last_button = buttonView;
                    }else {
                        last_button = buttonView;
                    }

                    buttonView.setTextColor(Color.parseColor("#03dac5"));

                }else {
                    selectedChipData.remove(buttonView.getText().toString());
                }

            }
        };

        // click on chips

        chip1.setOnCheckedChangeListener(checkedChangeListener);
        chip2.setOnCheckedChangeListener(checkedChangeListener);
        chip3.setOnCheckedChangeListener(checkedChangeListener);
        chip4.setOnCheckedChangeListener(checkedChangeListener);
        chip5.setOnCheckedChangeListener(checkedChangeListener);
        chip6.setOnCheckedChangeListener(checkedChangeListener);
        chip7.setOnCheckedChangeListener(checkedChangeListener);
        chip8.setOnCheckedChangeListener(checkedChangeListener);
        chip9.setOnCheckedChangeListener(checkedChangeListener);


    }
}