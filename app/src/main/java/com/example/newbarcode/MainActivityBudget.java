package com.example.newbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivityBudget extends AppCompatActivity implements View.OnClickListener {

    CalendarView calenderView;
    TextView myDate;
    EditText rmInput;
    Button setButton;
    Cache cacheObj;
    double budget;

    //to create set amount
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setButton:
                cacheObj.budget = Double.parseDouble(rmInput.getText().toString());
                Intent intent = new Intent(MainActivityBudget.this,AmountInput.class);  //untuk pergi next page
                intent.putExtra("date",myDate.getText().toString());
                startActivity(intent);  //
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_budget);
//        setContentView(R.id.);

        ImageView home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivityBudget.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        budget = cacheObj.budget;


        calenderView = findViewById(R.id.calendarView);
        rmInput = findViewById(R.id.rmInput);
        myDate = findViewById(R.id.myDate);
        setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(this);     //go to class

        //rmInput.setText(Double.toString(budget));

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                myDate.setText(date);
            }
        });
    }
}
