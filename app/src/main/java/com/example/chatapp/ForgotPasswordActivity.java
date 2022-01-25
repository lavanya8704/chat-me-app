package com.example.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText inputEmail;
    Button btnSend;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmail=findViewById(R.id.inputPasswordReset);
        btnSend=findViewById(R.id.btnReset);
        mAuth=FirebaseAuth.getInstance();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter Your Email ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPasswordActivity.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ForgotPasswordActivity.this, "Email Not Sent", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });
    }
}