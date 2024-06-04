package com.example.fiinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



     private ImageView expertImage, aproposImage, serviceImage, rdvImage,patientImage;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        aproposImage = findViewById(R.id.aproposImage);

        expertImage = findViewById(R.id.expertImage);

        serviceImage = findViewById(R.id.serviceImage);

        rdvImage = findViewById(R.id.rdvImage);

        patientImage = findViewById(R.id.patientImage);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);



        aproposImage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        });


        expertImage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, KinesActivity.class));
        });

        serviceImage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ServicesActivity.class));
        });


        rdvImage.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://calendly.com/contactc415/30min"));
            startActivity(intent);
        });


        patientImage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfilActivity.class));
        });





    }
}