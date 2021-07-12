package com.example.bookapp;

import android.graphics.Color;
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
import android.widget.Button;
import android.widget.ImageView;

import com.example.bookapp.fin.Single_one;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static com.github.mikephil.charting.animation.Easing.EaseInOutQuad;


public class Fragment_Pie extends Fragment {

    // layout

    ImageView back_button;

    private PieChart pieChart;

    Single_one single_one = Single_one.getInstance();

    ArrayList<PieEntry> entries = new ArrayList<>();

    ArrayList<Integer> pie_ArrayList_main =  new ArrayList<>();


    public Fragment_Pie(ArrayList<Integer> pie_ArrayList_main) {
        this.pie_ArrayList_main = pie_ArrayList_main;
    }

    public Fragment_Pie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment__pie, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        back_button = view.findViewById(R.id.button_close_pie);

        single_one = Single_one.getInstance();


        pieChart = view.findViewById(R.id.pie_main_piechart);
        setupPieChart();
        loadPieChartData();


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(Fragment_Pie.this).commit();
                single_one = Single_one.getInstance();
                if (single_one.isIn_search_book()) {

                } else {
                    getActivity().onBackPressed();
                }

            }
        });
    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Ratting Pie");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);    }

    private void loadPieChartData() {

        int total = 0;

        ArrayList<PieEntry> entries = new ArrayList<>();


        for (int i : pie_ArrayList_main) {
            Log.d("pie_num", "loadPieChartData: " + i);
            total += i;
        }

        double sum = 0.0; //if you use version earlier than java-8
//double sum = IntStream.of(keyList).sum(); //if you are using java-8
        for(int i = 0 ; i < pie_ArrayList_main.size() ; i++){
            sum += pie_ArrayList_main.get(i);
        }
        for(int i = 0 ; i < pie_ArrayList_main.size() ; i++){

            double x = pie_ArrayList_main.get(i)/sum;
            Log.d("cir", "loadPieChartData: " + x);
            String title = "";
            switch (i) {
                case 0:
                    title = "5 Stars";
                    break;
                case 1:
                    title = "4 Stars";
                    break;
                case 2:
                    title = "3 Stars";
                    break;
                case 3:
                    title = "2 Stars";
                    break;
                case 4:
                    title = "1 Stars";
                    break;
            }

            entries.add(new PieEntry((float) x,title));

        }


//        for (int i = 0; i < pie_ArrayList_main.size(); i++) {
//
//            float x = total%pie_ArrayList_main.get(i);
//
//            pic_values.add(x);}


//        entries.add(new PieEntry(0.2f,"400"));
//        entries.add(new PieEntry(0.15f,"300"));
//        entries.add(new PieEntry(0.10f,"200"));
//        entries.add(new PieEntry(0.25f,"100"));
//        entries.add(new PieEntry(0.3f,"600"));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color : ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for(int color : ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();


        // the time of anim
        pieChart.animateY(1400,EaseInOutQuad);

    }
}