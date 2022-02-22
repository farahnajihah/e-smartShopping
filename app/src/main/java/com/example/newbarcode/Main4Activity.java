package com.example.newbarcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Main4Activity extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<String> nameList;
    ArrayList<String> locationList;
    ArrayList<String> priceList;
    ArrayList<String> pictureList;
    ArrayList<String> groupList;
    RecyclerView rv2;
    LinearLayoutManager manager;
   ImageView pic1;
   TextView name1,location,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ImageView home_button = findViewById(R.id.home_button2);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Main4Activity.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference("Product");
        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        pic1 = (ImageView)findViewById(R.id.image1);
        name1 = (TextView)findViewById(R.id.name1);
        location = (TextView)findViewById(R.id.location);
        price = (TextView)findViewById(R.id.price2);



        String stringName = "", stringLocation = "",stringPrice = "",stringPicture = "";
        final String[] stringGroup = new String[1];
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                stringName = extras.getString("stringName");
                stringLocation = extras.getString("stringLocation");
                stringPrice = extras.getString("stringPrice");
                stringPicture = extras.getString("stringPicture");
                stringGroup[0] = extras.getString("stringGroup");
            }
        } else {
            stringName = (String) savedInstanceState.getSerializable("stringName");
            stringLocation = (String) savedInstanceState.getSerializable("stringLocation");
            stringPrice = (String) savedInstanceState.getSerializable("stringPrice");
            stringPicture = (String) savedInstanceState.getSerializable("stringPicture");
            stringGroup[0] = (String) savedInstanceState.getSerializable("stringGroup");
        }

        name1.setText(stringName);
        location.setText(stringLocation);
        price.setText(stringPrice);
        Picasso.get().load(stringPicture).into(pic1);

        rv2 = findViewById(R.id.rv2);

        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        nameList = new ArrayList<>();
                        locationList = new ArrayList<>();
                        priceList = new ArrayList<>();
                        pictureList = new ArrayList<>();
                        groupList = new ArrayList<>();

                        for ( DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            if(ds.child("group").getValue().toString().equals(stringGroup[0])) {
                                nameList.add(ds.child("name").getValue().toString());
                                locationList.add(ds.child("location").getValue().toString());
                                priceList.add(ds.child("price").getValue().toString());
                                pictureList.add(ds.child("picture").getValue().toString());
                                groupList.add(ds.child("group").getValue().toString());
                            }
                        }

                        rv2.setHasFixedSize(true);
                        rv2.setLayoutManager(manager);
                        AdapterClass2 adapterClass = new AdapterClass2 (Main4Activity.this, nameList,locationList,priceList,pictureList,groupList);

                        rv2.setAdapter(adapterClass);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Main4Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}


