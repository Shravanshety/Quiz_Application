package com.shravanshetty.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signIn extends AppCompatActivity {
    EditText userEmail,password;
    Button signInBtn;
    TextView signUp;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userEmail=findViewById(R.id.userEmail);

        password=findViewById(R.id.password);

        signInBtn=findViewById(R.id.signIn);
        signUp=findViewById(R.id.signUp);
        progressBar=findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();



        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(signIn.this,signUp.class));
                finish();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = userEmail.getText().toString().trim();
                String mPassword = password.getText().toString();

                if(TextUtils.isEmpty(mEmail)) {
                    userEmail.setError("Please enter your Password");
                    return;
                }if(TextUtils.isEmpty(mPassword)) {
                    password.setError("Please enter your Phone Number");
                    return;
                }
                progressBar.setVisibility((View.VISIBLE));

                mAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signIn.this, "user logged in succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signIn.this,MainActivity.class));
                            finishAffinity();
                        }else{
                            Toast.makeText(signIn.this, "Please check your Email and Password or Create a New Account", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}