package com.example.bookapp.fin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapp.MainActivity;
import com.example.bookapp.R;
import com.example.bookapp.books_class.The_Book;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class recycler_Adpter_HORIZONTAL extends RecyclerView.Adapter<recycler_Adpter_HORIZONTAL.ViewHolder> {

    ArrayList<The_Book> arrayList;
    Context context;



    public recycler_Adpter_HORIZONTAL(ArrayList<The_Book> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        Log.d("recycler", "recycler_Adpter_HORIZONTAL: " + arrayList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        // set logo to images
        //holder.imageView.setImageResource(arrayList.get(position).getImage());

        The_Book selectedMovie = arrayList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg(holder.textView.getText().toString());
                for (int i = 0; i < arrayList.size(); i++) {
                    if(arrayList.get(i).getTitle().equals(holder.textView.getText().toString())){

                        The_Book sected_movie_acount = arrayList.get(i);
                        // find the value we type on in recycler view
                        Log.d("6find", "onClick: " + sected_movie_acount.getTitle() );

                        break;

                    }

                }
            }
        });
        String image = selectedMovie.getImageback();
        Log.d("images1234", "getView: " + image);

        // todo make the image work !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Glide.with(context).load(image).placeholder(R.drawable.ic_baseline_book_24).dontAnimate().into(holder.imageView);


//        https://www.eduprizeschools.net/wp-content/uploads/2016/06/No_Image_Available.jpg
//        Glide.with(context)
//                .load(image)
//                .centerCrop()
//                .into(holder.imageView);
        //holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);


                    Log.d("opop", "onBindViewHolder: " + image);

        // set text

        holder.textView.setText(arrayList.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // here values
        ImageView imageView;
        TextView textView, stars_avg;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // find by id ; here
            imageView = itemView.findViewById(R.id.image_rec);
            textView = itemView.findViewById(R.id.text_rec);

        }
    }

    private void msg (String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG)
                .show();
    }
}
