package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText em;
    private EditText pw;
    private EditText dt;
    private EditText ph;
    private Button sg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        em = (EditText) findViewById(R.id.SignInEmail);
        pw = (EditText) findViewById(R.id.SignInPassword);
        dt = (EditText) findViewById(R.id.Date);
        ph = (EditText) findViewById(R.id.Phone);
        sg = (Button) findViewById(R.id.SignInButton);

        sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = em.getText().toString();
                String password = pw.getText().toString();
                String dob = dt.getText().toString();
                String phone = ph.getText().toString();

                if(email.isEmpty())
                {
                    em.getError();
                    em.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pw.getError();
                    pw.requestFocus();
                    return;
                }
                if(dob.isEmpty())
                {
                    dt.getError();
                    dt.requestFocus();
                    return;
                }
                if(phone.isEmpty())
                {
                    ph.getError();
                    ph.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(SignUpActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        });
    }
}