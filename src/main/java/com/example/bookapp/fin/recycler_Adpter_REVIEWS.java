package com.example.bookapp.fin;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.example.bookapp.books_class.The_REVIEWS;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class recycler_Adpter_REVIEWS extends RecyclerView.Adapter<recycler_Adpter_REVIEWS.ViewHolder> {

    ArrayList<The_REVIEWS> arrayList_rev;
    Context context;
    Activity activity;


    public recycler_Adpter_REVIEWS(ArrayList<The_REVIEWS> arrayList, Context context , Activity activity) {
        this.arrayList_rev = arrayList;
        this.context = context;
        this.activity = activity;
        Log.d("recycler_rev", "recycler_Adpter_REVIEWS2: " + arrayList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_rev,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        // set logo to images
        //holder.imageView.setImageResource(arrayList.get(position).getImage());




        The_REVIEWS  selected_rev = arrayList_rev.get(position);


        // set text

        Log.d("rev_pos", "onBindViewHolder: " + position);

        String des = selected_rev.getDescription();

        if (des.length() >= 180){
            des =  des.substring(0, 180);

        }
        Log.d("des_size", "onBindViewHolder: " + des.length());



        holder.text_de.setText(Html.fromHtml(des+ "... " + "<font color='yellow'> <u>(View More)</u></font>"));

        holder.likes.setText(selected_rev.getLikes());
        holder.username.setText(selected_rev.getName());
        holder.stars_avg.setText(" Final Review : "+selected_rev.getStars());

       // holder.viewmore.setText(Html.fromHtml(text+"<font color='red'> <u>View More</u></font>"));



        holder.text_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                Fragment_REVIEWS myFragment = new Fragment_REVIEWS(selected_rev);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.mail_countener1, myFragment).addToBackStack(null).commit();


                Log.d("adpterwork", "onClick: yes  on rev");

            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList_rev.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // here values
        TextView text_de, stars_avg , username , likes ;
        CardView cardView ;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // find by id ; here
            stars_avg = itemView.findViewById(R.id.rev_stars);
            text_de = itemView.findViewById(R.id.rev_de);
            username = itemView.findViewById(R.id.rev_name);
            likes = itemView.findViewById(R.id.rev_likes);
            cardView = itemView.findViewById(R.id.cardview_rev);

        }
    }

    private void msg (String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG)
                .show();
    }
}

