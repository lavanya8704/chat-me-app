package com.example.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private TextInputLayout inputEmail,inputPassword;
    Button btnLogin;
    TextView forgotPassword,CreateNewAccount;
    ProgressDialog mloadingBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnLogin);
        forgotPassword=findViewById(R.id.forgotPassword);
        CreateNewAccount=findViewById(R.id.CreateNewAccount);
        mloadingBar=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        CreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AtamptLogin();

            }



        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,ForgotPasswordActivity.class));

            }
        });


    }

    private void AtamptLogin() {
        String email=inputEmail.getEditText().getText().toString();
        String password=inputPassword.getEditText().getText().toString();

        if (email.isEmpty() || !email.contains("@gmail"))
        {
            showError(inputEmail,"Email is not Valid");
        }else if(password.isEmpty() || password.length()<5)
        {
            showError(inputPassword,"Password must be greated than 5 latter");
        }
        else
        {
            mloadingBar.setTitle("Login");
            mloadingBar.setMessage("Please Wait,While your Credentials");
            mloadingBar.setCanceledOnTouchOutside(false);
            mloadingBar.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mloadingBar.dismiss();
                        Toast.makeText(loginActivity.this, "login is Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(loginActivity.this,SetupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        mloadingBar.dismiss();
                        Toast.makeText(loginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void showError(TextInputLayout field, String text) {
        field.setError(text);
        field.requestFocus();

    }
}