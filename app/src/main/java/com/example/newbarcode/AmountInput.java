package com.example.newbarcode;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newbarcode.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AmountInput extends AppCompatActivity {

    TextView amountSet;
    Cache cacheObj;
    Button amountButton;
    LinearLayout list_linear;
    EditText codeText;
    String date;
    ImageButton addButton;
    ImageView home_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_input2);

        ImageView home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(AmountInput.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        ImageView addButton =findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBudget();
            }
        });
        TextView date_value = findViewById(R.id.date_value);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                date= extras.getString("date");
            }
        } else {
            date= (String) savedInstanceState.getSerializable("date");
        }

        date_value.setText(date);

        amountSet = findViewById(R.id.amountSet);
        amountSet.setText(Double.toString(cacheObj.budget));

        amountButton = findViewById(R.id.amountButton);
        list_linear = findViewById(R.id.list_linear);
        codeText = findViewById(R.id.codeText);


        amountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stringArrayList.add(codeText.getText().toString());
                //stringArrayAdapter.notifyDataSetChanged();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ingredient");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(codeText.getText().toString()).exists()){

                            String name = dataSnapshot.child(codeText.getText().toString()).child("name").getValue().toString();
                            String price = dataSnapshot.child(codeText.getText().toString()).child("price").getValue().toString();
                            String newprice = price.replace("RM","");
                            final double[] priceDouble = new double[1];
                            priceDouble[0] = Double.parseDouble(newprice);

                            //if budget set amount is > product price in database
                            if(cacheObj.budget >= priceDouble[0]) {

                                cacheObj.budget = cacheObj.budget - priceDouble[0];
                                amountSet.setText(Double.toString(cacheObj.budget));

                                final LinearLayout a = new LinearLayout(AmountInput.this);
                                a.setOrientation(LinearLayout.HORIZONTAL);

                                TextView tvname = new TextView(AmountInput.this);
                                tvname.setText(name);

                                TextView tvprice = new TextView(AmountInput.this);
                                tvprice.setText(price);

                                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        1.0f
                                );
                                tvname.setLayoutParams(param);
                                tvprice.setLayoutParams(param);

                                tvname.setGravity(Gravity.CENTER|Gravity.BOTTOM);
                                tvprice.setGravity(Gravity.CENTER|Gravity.BOTTOM);


//                                Button buttonView = new Butto2

                                ImageButton buttonView = new ImageButton(AmountInput.this);
                                buttonView.setImageResource(R.drawable.replace);


                                a.addView(tvname);
                                a.addView(tvprice);
                                a.addView(buttonView);

                                //if price less than budget set
                                buttonView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        a.setVisibility(View.GONE);
                                        cacheObj.budget = cacheObj.budget + priceDouble[0];
                                        amountSet.setText(Double.toString(cacheObj.budget));
                                    }
                                });
                                list_linear.addView(a);

                            }else{
                                Toast.makeText(AmountInput.this, "Not enough monies :')", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else{
                            Toast.makeText(AmountInput.this, "This product does not exist", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });
    }

    public void addBudget(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Budget");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                cacheObj.budget = cacheObj.budget + Double.parseDouble(m_Text);
                amountSet.setText(Double.toString(cacheObj.budget));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

//    public void onAddField(String li, String pr) {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View rowView = inflater.inflate(R.layout.listview_row, null);
//
//        TextView list_ingredient = rowView.findViewById(R.id.list_ingredient);
//        TextView price = rowView.findViewById(R.id.price);
//        ImageButton deleteBtn = rowView.findViewById(R.id.deleteBtn);
//
//        list_ingredient.setText(li);
//        price.setText(pr);
//
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onDelete(rowView);
//
//            }
//        });
//
//        // Add the new row before the add field button.
//        //Problem here
//        listView.addView(rowView, listView.getChildCount() - 1);
//    }

//    public void onDelete(View v) {
//        listView.removeView((View) v.getParent());
//    }

}
