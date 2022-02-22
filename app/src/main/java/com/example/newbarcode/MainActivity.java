package com.example.newbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




//        Button scanButton = findViewById(R.id.scanButton);
//        scanButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                scanow();
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if (result != null){
//            if (result.getContents() == null){
//                Toast.makeText(this,"Result Not Found", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
//                alertdialogbuilder.setMessage(result.getContents()+"\n\nShow the product details?");
//                alertdialogbuilder.setTitle("Result Scanned");
//                alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        final String barcodeNum = result.getContents();
//
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ingredient");
//
//
//                        ref.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                if (dataSnapshot.child(barcodeNum).exists()) {
//                                    Toast.makeText(MainActivity.this, "Item wujud", Toast.LENGTH_SHORT).show();
//
//                                    String factory = dataSnapshot.child(barcodeNum).child("factory").getValue().toString();
//                                    String ingr = dataSnapshot.child(barcodeNum).child("ingr").getValue().toString();
//                                    String legal = dataSnapshot.child(barcodeNum).child("legal").getValue().toString();
//                                    String name = dataSnapshot.child(barcodeNum).child("name").getValue().toString();
//                                    String price = dataSnapshot.child(barcodeNum).child("price").getValue().toString();
//                                    String sirim = dataSnapshot.child(barcodeNum).child("sirim").getValue().toString();
//                                    String picPro = dataSnapshot.child(barcodeNum).child("picPro").getValue().toString();
//
//                                    Intent intent = new Intent(MainActivity.this, retrieve.class);
//                                    intent.putExtra("factory", factory);
//                                    intent.putExtra("ingr", ingr);
//                                    intent.putExtra("legal", legal);
//                                    intent.putExtra("name", name);
//                                    intent.putExtra("price", price);
//                                    intent.putExtra("sirim", sirim);
//                                    intent.putExtra("picPro", picPro);
//                                    startActivity(intent);
//
//                                } else {
//
//                                    Toast.makeText(MainActivity.this, "Item tak wujud", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                System.out.println("The read failed: " + databaseError.getCode());
//                            }
//                        });
//
//
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                AlertDialog alertDialog = alertdialogbuilder.create();
//                alertDialog.show();
//            }
//        }
//        else{
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//    public void scanow(){
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setCaptureActivity(Portrait.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Scan Your Barcode");
//        integrator.initiateScan();
//    }


}
