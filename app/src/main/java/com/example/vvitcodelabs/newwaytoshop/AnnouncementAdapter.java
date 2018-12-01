package com.example.vvitcodelabs.newwaytoshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.Holder> {
    Context context;
    ArrayList<ItemsPojo> arrayList;

    public AnnouncementAdapter(Context context,ArrayList<ItemsPojo> arrayList) {
        this.context=context;
        this.arrayList = arrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View AnnounceView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new Holder(AnnounceView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        Picasso.with(context).load(arrayList.get(position).getImage()).error(R.mipmap.ic_launcher).into(holder.image);
        holder.title.setText(arrayList.get(position).getName());
        holder.price.setText("Price: "+String.valueOf(arrayList.get(position).getPrice()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Main2Activity.class);
                intent.putExtra("name",arrayList.get(position).getName());
                intent.putExtra("price",String.valueOf(arrayList.get(position).getPrice()));
                intent.putExtra("image",arrayList.get(position).getImage());
                intent.putExtra("desc",arrayList.get(position).getDescription());
                Log.i("hello",arrayList.get(position).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,price;
        CardView cardView;

        public Holder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.itemName);
            image= itemView.findViewById(R.id.itemimage);
            price = itemView.findViewById(R.id.itemPrice);
        }
    }
}