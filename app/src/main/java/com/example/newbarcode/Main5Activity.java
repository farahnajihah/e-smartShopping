package com.example.newbarcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {



    DatabaseReference ref;
    ArrayList<String> nameList;
    ArrayList<String> locationList;
    ArrayList<String> priceList;
    ArrayList<String> pictureList;
    ArrayList<String> groupList;
    RecyclerView recyclerView;
    SearchView searchView;
    LinearLayoutManager manager;
    Button searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//        ImageView home_button = findViewById(R.id.home_button);
//        home_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent homeIntent = new Intent(Main5Activity.this, homepage.class);
//                startActivity(homeIntent);
//            }
//        });

        ref = FirebaseDatabase.getInstance().getReference("Product");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        searchbtn = findViewById(R.id.searchbtn);
        manager = new LinearLayoutManager(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchItems(searchView.getQuery().toString());
            }
        });

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
                            nameList.add(ds.child("name").getValue().toString());
                            locationList.add(ds.child("location").getValue().toString());
                            priceList.add(ds.child("price").getValue().toString());
                            pictureList.add(ds.child("picture").getValue().toString());
                            groupList.add(ds.child("group").getValue().toString());
                        }

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(manager);
                        AdapterClass adapterClass = new AdapterClass (Main5Activity.this, nameList,locationList,priceList,pictureList,groupList);

                        recyclerView.setAdapter(adapterClass);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Main5Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }



    }

    protected void onStart() {

        super.onStart();

        if(searchView!=null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
//                    search(s);
                    return true;
                }
            });
        }
    }



    public void searchItems(final String searchStr){

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
                            if(ds.child("name").getValue().toString().toLowerCase().contains(searchStr.toLowerCase()) || ds.child("location").getValue().toString().toLowerCase().contains(searchStr.toLowerCase()))
                            {
                                nameList.add(ds.child("name").getValue().toString());
                                locationList.add(ds.child("location").getValue().toString());
                                priceList.add(ds.child("price").getValue().toString());
                                pictureList.add(ds.child("picture").getValue().toString());
                                groupList.add(ds.child("group").getValue().toString());
                            }
                        }

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(manager);
                        AdapterClass adapterClass = new AdapterClass (Main5Activity.this, nameList,locationList,priceList,pictureList,groupList);

                        recyclerView.setAdapter(adapterClass);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Main5Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
