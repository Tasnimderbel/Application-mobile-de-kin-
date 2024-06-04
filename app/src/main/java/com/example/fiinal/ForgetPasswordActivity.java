package com.example.fiinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button goToSignInActivity,btnReset;
    private EditText email;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        goToSignInActivity = findViewById(R.id.goToSignIn);
        btnReset = findViewById(R.id.btnResetPass);
        email = findViewById(R.id.emailForgetPass);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        goToSignInActivity.setOnClickListener(v -> {
            startActivity(new Intent(ForgetPasswordActivity.this, SignInActivity.class));

        });
        btnReset.setOnClickListener(view -> {
            progressDialog.setMessage("S'il vous plaît,attendez...!");
            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgetPasswordActivity.this, "Email de réinitialisation du mot de passe envoyé", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgetPasswordActivity.this,SignInActivity.class));

                    }
                    else{
                        Toast.makeText(ForgetPasswordActivity.this, "Erreur!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            });
        });
    }
}
