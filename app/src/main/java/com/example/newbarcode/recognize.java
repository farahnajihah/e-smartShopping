package com.example.newbarcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class recognize extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    EditText resulttxt;
    ImageView previewimg;
    Button home_button9;


    private boolean connected;
    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=400;
    private static final int IMAGE_PICK_GALLERY_CODE =1000;
    private static final int IMAGE_PICK_CAMERA_CODE=1001;

    String cameraPermission[];
    String storagePermission[];

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recognize_layout);

        ImageView home_button9 = findViewById(R.id.home_button9);
        home_button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(recognize.this, homepage.class);
                startActivity(homeIntent);
            }
        });
        ActionBar actionBar = getSupportActionBar();


        Button clear =findViewById(R.id.clear);
        Button search =findViewById(R.id.search);
        //Button button2 = findViewById(R.id.button2);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        resulttxt = findViewById(R.id.ResultEt);
        //previewimg = findViewById(R.id.imageIv);
//

        //camera permission
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pickCamera();
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String status = resulttxt.getText().toString();
                if (status.isEmpty()){
                    {
                        Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
                    }
                }else{


                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Word");
//                String Value = resulttxt.getText().toString();

                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(status).exists()){
                                Toast.makeText(recognize.this,"Item found", Toast.LENGTH_SHORT).show();

                                String title = dataSnapshot.child(status).child("title").getValue().toString();
                                String ingredient = dataSnapshot.child(status).child("ingredient").getValue().toString();
                                String certificate = dataSnapshot.child(status).child("certificate").getValue().toString();
                                String pict = dataSnapshot.child(status).child("pict").getValue().toString();

                                Intent intent = new Intent(recognize.this,Result_word.class);
                                intent.putExtra("title",title);
                                intent.putExtra("ingredient",ingredient);
                                intent.putExtra("certificate",certificate );
                                intent.putExtra("pict",pict);
                                startActivity(intent);

                            }else{
                                Toast.makeText(recognize.this,"Item not found", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                        }
                    });
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text = resulttxt.getText().toString();
                if(Text.isEmpty()){
                    Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
                }
                else{
                    resulttxt.setText("");
                }
            }
        });

    }

    //menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //handle
  //  @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        if(id == R.id.button2){
//            showImageImportDialog();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void showImageImportDialog() {
//
//        String[] items = {"Camera","Gallery"};
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("Select Image");
//        dialog.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if( i == 0){
//                    //Camera optionn
//                    if (!checkCameraPermission()){
//                        requestCameraPermission();
//                    }
//                    else{
//                        pickCamera();
//                    }
//                }
//                if ( i == 1){
//                    //gallery option
//                    if (!checkStoragePermission()){
//                        requestStoragePermission();
//                    }
//                    else{
//                        pickGallery();
//                    }
//                }
//
//            }
//        });
//        dialog.create().show();
//    }

    private void pickGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }

    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image To Text");
        image_uri =  getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        pickCamera();
                    }
                    else{
                        Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if(grantResults.length>0){
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(writeStorageAccepted){
                        pickGallery();
                    }
                    else{
                        Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(image_uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                previewimg.setImageURI(resultUri);

                BitmapDrawable bitmapDrawable = (BitmapDrawable)previewimg.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if(!recognizer.isOperational()){
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
                }
                else{
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    for(int i = 0 ; i<items.size();i++){
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                        sb.append("\n");
                    }

                    resulttxt.setText(sb.toString());

                }
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
                Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
