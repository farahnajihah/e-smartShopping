package com.example.newbarcode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Barcodescan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Barcodescan.this, homepage.class);
                startActivity(homeIntent);
            }
        });

//        Name = (TextView)findViewById(R.id.pro_name);
//        Price = (TextView)findViewById(R.id.pro_price);
//        Legal = (TextView) findViewById(R.id.pro_legal);
//        Factory = (TextView) findViewById(R.id.pro_factory);
//        Ingr = (TextView)findViewById(R.id.pro_ingr);
//        Sirim = (TextView)findViewById(R.id.pro_sirim);


        Button scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanow();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this,"Result Not Found", Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
                alertdialogbuilder.setMessage(result.getContents()+"\n\nShow the product details?");
                alertdialogbuilder.setTitle("Result Scanned");
                alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String barcodeNum = result.getContents();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ingredient");


                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.child(barcodeNum).exists()) {
                                    Toast.makeText(Barcodescan.this, "Item wujud", Toast.LENGTH_SHORT).show();

                                    String factory = dataSnapshot.child(barcodeNum).child("factory").getValue().toString();
                                    String ingr = dataSnapshot.child(barcodeNum).child("ingr").getValue().toString();
                                    String legal = dataSnapshot.child(barcodeNum).child("legal").getValue().toString();
                                    String name = dataSnapshot.child(barcodeNum).child("name").getValue().toString();
                                    String price = dataSnapshot.child(barcodeNum).child("price").getValue().toString();
                                    String sirim = dataSnapshot.child(barcodeNum).child("sirim").getValue().toString();
                                    String picPro = dataSnapshot.child(barcodeNum).child("picPro").getValue().toString();

                                    Intent intent = new Intent(Barcodescan.this, retrieve.class);
                                    intent.putExtra("factory", factory);
                                    intent.putExtra("ingr", ingr);
                                    intent.putExtra("legal", legal);
                                    intent.putExtra("name", name);
                                    intent.putExtra("price", price);
                                    intent.putExtra("sirim", sirim);
                                    intent.putExtra("picPro", picPro);
                                    startActivity(intent);

                                } else {
//                                    AlertDialog.Builder alertdialogbuildererror = new AlertDialog.Builder(MainActivity.this);
//                                    alertdialogbuildererror.setMessage("Item tak wujud");
//                                    alertdialogbuildererror.setTitle("Failed");
                                    Toast.makeText(Barcodescan.this, "Item tak wujud", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertdialogbuilder.create();
                alertDialog.show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void scanow(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Your Barcode");
        integrator.initiateScan();
    }

//    // Read from the database
//    myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//            String value = dataSnapshot.getValue(String.class);
//            Log.d("Value is:" + value);
//            //Log.d("Value is: " + value);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError error) {
//            // Failed to read value
//            Log.w(TAG, "Failed to read value.", error.toException());
//        }
//    });
}
