package com.example.newbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

import java.util.Map;

public class retrieve extends AppCompatActivity {


    TextView Name;
    TextView Price;
    TextView Legal;
    TextView Factory;
    TextView Ingr;
    TextView Sirim;

    ImageView PicPro;
    ImageView home_button10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        ImageView home_button10 = findViewById(R.id.home_button10);
        home_button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(retrieve.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        Name = (TextView)findViewById(R.id.pro_name);
        Price = (TextView)findViewById(R.id.pro_price);
        Legal = (TextView) findViewById(R.id.pro_legal);
        Factory = (TextView) findViewById(R.id.pro_factory);
        Ingr = (TextView)findViewById(R.id.pro_ingr);
        Sirim = (TextView)findViewById(R.id.pro_sirim);
        PicPro = (ImageView) findViewById(R.id.picture);

        String nameStr = "", priceStr = "", legalStr = "", factoryStr = "", ingrStr = "", sirimStr = "", picProStr="";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                nameStr = extras.getString("name");
                priceStr = extras.getString("price");
                legalStr = extras.getString("legal");
                factoryStr = extras.getString("factory");
                ingrStr = extras.getString("ingr");
                sirimStr = extras.getString("sirim");
                picProStr = extras.getString("picPro");
            }
        } else {
            nameStr= (String) savedInstanceState.getSerializable("name");
            priceStr= (String) savedInstanceState.getSerializable("price");
            legalStr= (String) savedInstanceState.getSerializable("legal");
            factoryStr= (String) savedInstanceState.getSerializable("factory");
            ingrStr= (String) savedInstanceState.getSerializable("ingr");
            sirimStr= (String) savedInstanceState.getSerializable("sirim");
            picProStr= (String) savedInstanceState.getSerializable("picPro");
        }

        Picasso.get().load(picProStr).into(PicPro);
        Name.setText(nameStr);
        Price.setText(priceStr);
        Legal.setText(legalStr);
        Factory.setText(factoryStr);
        Ingr.setText(ingrStr);
        Sirim.setText(sirimStr);
//        int pictureURL = Gambar.getImageAlpha(gambarStr);

    }
}
