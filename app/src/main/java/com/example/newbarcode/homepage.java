package com.example.newbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class homepage extends AppCompatActivity {

    private ImageView btnIngredient;
    private ImageView btnWord;
    private ImageView btnBudget;
    private ImageView btnProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnBudget = (ImageView) findViewById(R.id.btnBudget);
        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent budgetIntent = new Intent(homepage.this, MainActivityBudget.class);
                startActivity(budgetIntent);
            }
        });

        btnWord = (ImageView) findViewById(R.id.btnWord);
        btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wordIntent = new Intent(homepage.this, recognize.class);
                startActivity(wordIntent);
            }
        });

        btnIngredient = (ImageView) findViewById(R.id.btnIngredient);
        btnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(homepage.this, Barcodescan.class);
                startActivity(nextIntent);
            }
        });

        btnProduct = (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(homepage.this, Main5Activity.class);
                startActivity(nextIntent);
            }
        });
    }
}
