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

public class RegisterActivity extends AppCompatActivity  {

    private TextInputLayout inputEmail,inputPassword,inputConfirmPassword;
    Button btnRegister;
    TextView alreadyHaveAccount;
    FirebaseAuth mAuth;
    ProgressDialog mloadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConfirmPassword=findViewById(R.id.inputConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister);
        alreadyHaveAccount=findViewById(R.id.alreadyHaveanAccount);
        mAuth= FirebaseAuth.getInstance();
        mloadingBar=new ProgressDialog(this);
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AttemptRegistration();
            }
        });
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void AttemptRegistration() {
        String email=inputEmail.getEditText().getText().toString();
        String password=inputPassword.getEditText().getText().toString();
        String confirmPassword=inputConfirmPassword.getEditText().getText().toString();

        if (email.isEmpty() || !email.contains("@gmail"))
        {
            showError(inputEmail,"Email is not Valid");
        }
        else if(password.isEmpty() || password.length()<5)
        {
            showError(inputPassword,"Password must be greated than 5 latter");
        }
        else if(!confirmPassword.equals(password))
        {
            showError(inputConfirmPassword,"Password did not Match!");
        }
        else
        {
            mloadingBar.setTitle("Registration");
            mloadingBar.setMessage("Please Wait,While your Credentials");
            mloadingBar.setCanceledOnTouchOutside(false);
            mloadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        mloadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration  is Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,SetupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        mloadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration is Failed", Toast.LENGTH_SHORT).show();
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