//package com.example.bookapp.fin;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.bookapp.R;
//import com.example.bookapp.books_class.The_Book;
//import com.google.android.material.snackbar.Snackbar;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//
//public class recycler_Adpter_HORIZONTAL extends RecyclerView.Adapter<recycler_Adpter_HORIZONTAL.ViewHolder> {
//    ArrayList<The_Book> arrayList;
//    Context context;
//
//
//    // on ckick
//
//    // firebase
//
//
//    Single_one single_one = Single_one.getInstance();
//
//
//    public recycler_Adpter_HORIZONTAL(ArrayList<The_movies> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//        Log.d("recycler", "recycler_Adpter_HORIZONTAL: " + arrayList.size());
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // create view
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
//
//        // set logo to images
//        //holder.imageView.setImageResource(arrayList.get(position).getImage());
//
//        The_movies selectedMovie = arrayList.get(position);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                msg(holder.textView.getText().toString());
//                for (int i = 0; i < arrayList.size(); i++) {
//                    if(arrayList.get(i).getTitle().equals(holder.textView.getText().toString())){
//
//                        The_movies sected_movie_acount = arrayList.get(i);
//                        // find the value we type on in recycler view
//                        Log.d("6find", "onClick: " + sected_movie_acount.getTitle() );
//
//                        Single_one single_one = Single_one.getInstance();
//                        single_one.setValue_movie(sected_movie_acount);
//
//
//                        break;
//
//                    }
//
//                }
//            }
//        });
//
//        String image ="https://image.tmdb.org/t/p/w500" + selectedMovie.getImage() ;
//        Log.d("images1234", "getView: " + image);
//
//
//        if (image.equals("https://image.tmdb.org/t/p/w500null") || selectedMovie.getImage() == null || selectedMovie.getImage() == "" || selectedMovie.getImage() == "null"){
//            image = "null";
//
//            Log.d("image123", "getView: " + image);
//            if (image.equals("null") || image.equals("https://image.tmdb.org/t/p/w500null")) {
//                Log.d("opop", "onBindViewHolder: " + image);
//
//                if (selectedMovie.getImage_sec() != null) {
//                    image = "https://image.tmdb.org/t/p/w500" + selectedMovie.getImage_sec();
//                    Glide.with(context)
//                            .load(image)
//                            .centerCrop()
//                            .into(holder.imageView);
//                    holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//
//                    Log.d("opop", "onBindViewHolder: " + image);
//
//                }
//                if (image.equals("https://image.tmdb.org/t/p/w500null")) {
//                    // no images
//                    Glide.with(context)
//                            .load(single_one.getNo_imgae_abl())
//                            .centerCrop()
//                            .into(holder.imageView);
//                    holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    //Glide.with(getView(position,convertView,parent)).load(getImage("pic")).into(imageView_poster_movie);
//
//                }
//            }
//        }else {
//
//            Glide.with(context)
//                    .load(image)
//                    .fitCenter()
//                    .into(holder.imageView);
//
//            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        }
//
//
//
//        // set text
//
//        holder.textView.setText(arrayList.get(position).getTitle());
//        holder.stars_avg.setText(arrayList.get(position).getVote_average());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // here values
//        ImageView imageView;
//        TextView textView, stars_avg;
//
//        public ViewHolder(@NonNull @NotNull View itemView) {
//            super(itemView);
//            // find by id ; here
//            imageView = itemView.findViewById(R.id.image_rec);
//            textView = itemView.findViewById(R.id.text_rec);
//            add = itemView.findViewById(R.id.button_add_love_rec);
//            stars_avg = itemView.findViewById(R.id.stars_rec);
//
//
//
//        }
//
//
//
//
//    }
//
//    private void msg (String text){
//        Toast.makeText(context,text,Toast.LENGTH_LONG)
//                .show();
//    }
//}
