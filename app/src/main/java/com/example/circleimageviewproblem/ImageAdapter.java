package com.example.circleimageviewproblem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    Context context;
    ArrayList<ImageData> imageData;


    public ImageAdapter(Context context, ArrayList<ImageData> imageData) {
        this.context = context;
        this.imageData = imageData;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_recycler_view,null,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.textView.setText(imageData.get(position).getTitle());
        //holder.circleImageView.setImageResource(imageData.get(position).getImage());
        //Glide.with(context).load(imageData.get(position).getImageUrl()).into(holder.circleImageView);

        Picasso.get().load(imageData.get(position).getImageUrl()).into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CircleImageView circleImageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.imageTitle);
            circleImageView=itemView.findViewById(R.id.holderImageView);
        }
    }
}
