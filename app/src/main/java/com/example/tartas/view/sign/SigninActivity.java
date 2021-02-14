package com.example.tartas.view.sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tartas.R;
import com.example.tartas.view.MainActivity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    EditText edit_text_emailaddress;
    EditText edit_text_password;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        mAuth = FirebaseAuth.getInstance();
        edit_text_emailaddress = findViewById(R.id.edit_text_emailaddress);
        edit_text_password = findViewById(R.id.edit_text_password);
    }

    public void Signin(View view) {
        if (!ValidAll()){

        }else {
            dialog.show();
            signUser();

        }
    }

    private void signUser() {

        mAuth.signInWithEmailAndPassword(edit_text_emailaddress.getText().toString().trim(),
                edit_text_password.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.hide();
                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SigninActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.hide();
            }
        });
    }

    public boolean ValidAll() {
        return IsEmpty(edit_text_emailaddress, "Required")
                & IsEmpty(edit_text_password, "Required");
    }

    public boolean IsEmpty(EditText editText, String msg) {
        boolean isDone = true;
        if (editText != null) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(msg);
                isDone = false;
            }
        }
        return isDone;


    }

}