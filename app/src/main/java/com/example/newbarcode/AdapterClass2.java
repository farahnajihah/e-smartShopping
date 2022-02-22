package com.example.newbarcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClass2 extends RecyclerView.Adapter<AdapterClass2.MyViewHolder> {

    ArrayList<String> nameList1;
    ArrayList<String> locationList1;
    ArrayList<String> priceList1;
    ArrayList<String> pictureList1;
    ArrayList<String> groupList;
    Context gContext;

    public AdapterClass2(Context context, ArrayList<String> nameList,
                        ArrayList<String> locationList, ArrayList<String> priceList,
                        ArrayList<String> pictureList, ArrayList<String> groupList){

        this.nameList1 = nameList;
        this.locationList1 = locationList;
        this.priceList1 = priceList;
        this.pictureList1 = pictureList;
        this.groupList = groupList;
        this.gContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder2,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.name2.setText(nameList1.get(position));
        myViewHolder.location2.setText(locationList1.get(position));
        myViewHolder.price2.setText(priceList1.get(position));
        String pictureURL = pictureList1.get(position);
        Picasso.get().load(pictureURL).into(myViewHolder.image2);

    }

    @Override
    public int getItemCount() {
        return nameList1.size();


    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image2;
        TextView name2,location2,price2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name2 = itemView.findViewById(R.id.name2);
            location2 = itemView.findViewById(R.id.location2);
            price2 = itemView.findViewById(R.id.price2);
            image2 = itemView.findViewById(R.id.image2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gContext, Main4Activity.class);
                    intent.putExtra("stringName",nameList1.get(getAdapterPosition()));
                    intent.putExtra("stringLocation",locationList1.get(getAdapterPosition()));
                    intent.putExtra("stringPrice",priceList1.get(getAdapterPosition()));
                    intent.putExtra("stringPicture",pictureList1.get(getAdapterPosition()));
                    intent.putExtra("stringGroup",groupList.get(getAdapterPosition()));
                    gContext.startActivity(intent);
                }
            });
        }
    }

//    public void updateList(List<String> newList1,List<String> newList2,List<String> newList3,List<String> newList4)
//    {
//        nameList = new ArrayList<>();
//        locationList = new ArrayList<>();
//        priceList = new ArrayList<>();
//        pictureList = new ArrayList<>();
//
//        nameList.addAll(newList1);
//        nameList.addAll(newList2);
//        nameList.addAll(newList3);
//        nameList.addAll(newList4);
//
//
//
//        notifyDataSetChanged();
//    }



}
