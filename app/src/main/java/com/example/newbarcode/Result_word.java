package com.example.newbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.squareup.picasso.Picasso;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result_word extends AppCompatActivity {
    TextView title;
    TextView ingredient;
    TextView certificate;
//    Button back;
    //    ImageView img;
    ImageView pict;
    ImageView home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_word);

        ImageView home_button = findViewById(R.id.home_button3);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Result_word.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        title = (TextView) findViewById(R.id.Title);
        ingredient = (TextView) findViewById(R.id.Ingredient);
        certificate = (TextView) findViewById(R.id.Certificate);
//        img = (ImageView)findViewById(R.id.Images);
        pict=(ImageView)findViewById(R.id.Images);
//        back = (Button) findViewById(R.id.Back);

        String titleStr = "", ingredientStr = "", certificateStr = "",pictStr="";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                titleStr = extras.getString("title");
                ingredientStr = extras.getString("ingredient");
                certificateStr = extras.getString("certificate");
                pictStr = extras.getString("pict");
            }
        }else {
            titleStr = (String) savedInstanceState.getSerializable("title");
            ingredientStr = (String) savedInstanceState.getSerializable("ingredient");
            certificateStr = (String) savedInstanceState.getSerializable("certificate");
            pictStr = (String)savedInstanceState.getSerializable("pict");

        }
        Picasso.get().load(pictStr).into(pict);
        title.setText(titleStr);
        ingredient.setText(ingredientStr);
        certificate.setText(certificateStr);

    }


}