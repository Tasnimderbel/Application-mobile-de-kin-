
package com.example.fiinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private TextView forgetPass;
    private TextView goToSignUp;
    private Button signIn;
    private EditText email;
    private EditText password;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.emailSignIn);
        password = findViewById(R.id.passwordSignIn);

        forgetPass = findViewById(R.id.goToForgetPass);
        goToSignUp = findViewById(R.id.goToSignUp);
        signIn = findViewById(R.id.btnSignIn);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        goToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        });

        forgetPass.setOnClickListener(v -> {
            startActivity((new Intent(SignInActivity.this, ForgetPasswordActivity.class)));
        });

        signIn.setOnClickListener(v -> {

            progressDialog.setMessage("S'il vous plaît,attendez...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        verifyEmail();
                        Toast.makeText(SignInActivity.this, "Connecté avec sucées", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else{
                        Toast.makeText(SignInActivity.this, "Veuillez vérifier que vos données sont correctes", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        });
    }

    private void verifyEmail() {
        FirebaseUser connectedUser = firebaseAuth.getCurrentUser();
        boolean isEmailFlag = connectedUser.isEmailVerified();
        if (isEmailFlag){
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Please Verify your email's account", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
