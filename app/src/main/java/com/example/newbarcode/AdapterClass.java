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
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<String> nameList;
    ArrayList<String> locationList;
    ArrayList<String> priceList;
    ArrayList<String> pictureList;
    ArrayList<String> groupList;
    Context gContext;

    public AdapterClass(Context context, ArrayList<String> nameList,
                        ArrayList<String> locationList, ArrayList<String> priceList,
                        ArrayList<String> pictureList, ArrayList<String> groupList){

        this.nameList = nameList;
        this.locationList = locationList;
        this.priceList = priceList;
        this.pictureList = pictureList;
        this.groupList = groupList;
        this.gContext = context;
    }

    public AdapterClass(ArrayList<String> nameList, ArrayList<String> locationList, ArrayList<String> priceList) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.name.setText(nameList.get(position));
        myViewHolder.location.setText(locationList.get(position));
        myViewHolder.price.setText(priceList.get(position));
        String pictureURL = pictureList.get(position);
        Picasso.get().load(pictureURL).into(myViewHolder.picture);

    }

    @Override
    public int getItemCount() {
        return nameList.size();


    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,price;
        ImageView picture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            price = itemView.findViewById(R.id.price2);
            picture = itemView.findViewById(R.id.picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gContext, Main4Activity.class);
                    intent.putExtra("stringName",nameList.get(getAdapterPosition()));
                    intent.putExtra("stringLocation",locationList.get(getAdapterPosition()));
                    intent.putExtra("stringPrice",priceList.get(getAdapterPosition()));
                    intent.putExtra("stringPicture",pictureList.get(getAdapterPosition()));
                    intent.putExtra("stringGroup",groupList.get(getAdapterPosition()));
                    gContext.startActivity(intent);
                }
            });
        }
    }

    public void updateList(List<String> newList1, List<String> newList2, List<String> newList3, List<String> newList4)
    {
        nameList = new ArrayList<>();
        locationList = new ArrayList<>();
        priceList = new ArrayList<>();
        pictureList = new ArrayList<>();

        nameList.addAll(newList1);
        nameList.addAll(newList2);
        nameList.addAll(newList3);
        nameList.addAll(newList4);



        notifyDataSetChanged();
    }



}
