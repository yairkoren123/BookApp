package com.example.bookapp.frags_tabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Quotes;
import com.example.bookapp.books_class.The_REVIEWS;
import com.example.bookapp.fin.Fragment_REVIEWS;
import com.example.bookapp.fin.Single_one;
import com.example.bookapp.fin.recycler_Adpter_REVIEWS;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class recycler_Adpter_Quotes extends RecyclerView.Adapter<recycler_Adpter_Quotes.ViewHolder> {



    ArrayList<The_Quotes> the_quotesArrayList;
    Context context;
    Activity activity;
    String get_tag_search = "";



    public recycler_Adpter_Quotes(ArrayList<The_Quotes> the_quotesArrayList, Context context, Activity activity, String get_tag_search) {
        this.the_quotesArrayList = the_quotesArrayList;
        this.context = context;
        this.activity = activity;
        this.get_tag_search = get_tag_search;
    }

    public recycler_Adpter_Quotes(ArrayList<The_Quotes> arrayList, Context context , Activity activity) {
        this.the_quotesArrayList = arrayList;
        this.context = context;
        this.activity = activity;
        Log.d("recycler_rev_quo", "recycler_Adpter_REVIEWS2: " + arrayList.size());
    }

    @NonNull
    @Override
    public recycler_Adpter_Quotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quotes_item,parent,false);
        return new recycler_Adpter_Quotes.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull recycler_Adpter_Quotes.ViewHolder holder, int position) {

        // set logo to images
        //holder.imageView.setImageResource(arrayList.get(position).getImage());




        The_Quotes selected_quotes = the_quotesArrayList.get(position);

        // set text

        Log.d("rev_pos", "onBindViewHolder: " + position);


        holder.text_de_qu.setText(selected_quotes.getQuotes_text());
        holder.likes_qu.setText(selected_quotes.getLikes());
        holder.username_qu.setText(selected_quotes.getUser());

        Single_one single_one = Single_one.getInstance();

        if (!get_tag_search.equals("")){
//            textView.setText("Hello, I am Awesome, Most Awesome"); // set text first
            setHighLightedText(holder.text_de_qu, single_one.getQuote_search_now()); // highlight all `a` in TextView
        }


    }
    /**
     * use this method to highlight a text in TextView
     * @param tv              TextView or Edittext or Button (or derived from TextView)
     * @param textToHighlight Text to highlight
     */
    public void setHighLightedText(TextView tv, String textToHighlight) {
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToHighlight, 0);
        Spannable wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToHighlight, ofs);
            if (ofe == -1)
                break;
            else {
                // set color here
                wordToSpan.setSpan(new BackgroundColorSpan(Color.parseColor("#FF9800")), ofe, ofe + textToHighlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return the_quotesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // here values
        TextView text_de_qu, username_qu , likes_qu ;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // find by id ; here
            text_de_qu = itemView.findViewById(R.id.quote_text_item);
            username_qu = itemView.findViewById(R.id.username_item);
            likes_qu = itemView.findViewById(R.id.likes_item);

        }
    }

    private void msg (String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG)
                .show();
    }
}


