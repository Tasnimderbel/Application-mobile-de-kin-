package com.example.fiinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiinal.R;
import com.example.fiinal.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {
    private TextView goToSignIn;
    private EditText nomet,ncinet,phoneet,adresseet,emailet,passwordet,passwordConfet;
    private Button signUpBtn;
    private String nomS,ncinS,phoneS,adresseS,emailS,passwordS,passwordConfS;

    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        nomet = findViewById(R.id.Nom);
        ncinet     = findViewById(R.id.Cin);
        phoneet = findViewById(R.id.Phone);
        adresseet     = findViewById(R.id.Adresse);
        emailet    = findViewById(R.id.Email);
        passwordet = findViewById(R.id.Password);
        passwordConfet = findViewById(R.id.PasswordConf);
        goToSignIn = findViewById(R.id.goToSignIn);
        signUpBtn = findViewById(R.id.signUpBtn);

        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
        });
        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(v -> {

            if (validate()) {
                //Toast.makeText(this, "valide", Toast.LENGTH_SHORT).show();
                firebaseAuth.createUserWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendEmailVerification();
                        }else{
                            Toast.makeText(SignUpActivity.this, "échec de l'enregistrement", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });
    }

    private void sendEmailVerification() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null ){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        //send user data
                        sendUserData();
                        Toast.makeText(SignUpActivity.this, "Inscription terminée, veuillez vérifier votre email", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                        finish();
                    }else {
                        Toast.makeText(SignUpActivity.this, "échec de l'enregistrement", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference myRef = firebaseDatabase.getReference("Users");

        User user = new User(nomS, phoneS, ncinS, emailS, adresseS);

        myRef.child(""+firebaseAuth.getUid()).setValue(user);
    }


    private Boolean validate() {
        boolean result=false;
        nomS=nomet.getText().toString();
        ncinS=ncinet.getText().toString();
        phoneS=phoneet.getText().toString();
        adresseS=adresseet.getText().toString();
        emailS=emailet.getText().toString();
        passwordS=passwordet.getText().toString();
        passwordConfS=passwordConfet.getText().toString();
        if (nomS.isEmpty()||nomS.length()<7)
        {
            nomet.setError("nom not valide");
        }
        else if (ncinS.isEmpty()||ncinS.length()!=8) {
            ncinet.setError("ncin not valide");
        }else if (phoneS.isEmpty()||phoneS.length()!=8) {
            phoneet.setError("phone not valide");
        }
        else if (adresseS.isEmpty()) {
            adresseet.setError("adresse not valide");
        }else if (!isValidEmail(emailS)) {
            emailet.setError("email not valide");
        }else if (passwordS.isEmpty()||passwordS.length()<7){
            passwordet.setError("password invalide");
        } else if (!passwordS.equals(passwordConfS)||passwordConfS.isEmpty()) {
            passwordConfet.setError("password not match");
        } else {
            result=true;
        }
        return result;
    }
    public  boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}