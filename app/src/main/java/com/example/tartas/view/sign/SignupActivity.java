package com.example.tartas.view.sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tartas.R;
import com.example.tartas.view.MainActivity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText edit_text_emailaddress;
    EditText edit_text_username;
    EditText edit_text_password;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        mAuth = FirebaseAuth.getInstance();
        edit_text_emailaddress = findViewById(R.id.edit_text_emailAddressSignUp);
        edit_text_username = findViewById(R.id.edit_text_usernameSignUp);
        edit_text_password = findViewById(R.id.edit_text_passwordSignUp);

        Button buttonsignup = findViewById(R.id.buttonsignup);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidAll()) {

                } else {
                    dialog.show();
                    createUser();
                }

            }
        });
    }

    public void SignUP(View view) {
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
        finish();
    }

    private void createUser() {
        mAuth.createUserWithEmailAndPassword(edit_text_emailaddress.getText().toString().trim(), edit_text_password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uId = task.getResult().getUser().getUid();
                    addUser(edit_text_username.getText().toString().trim(), edit_text_emailaddress.getText().toString().trim(), edit_text_password.getText().toString().trim(), uId);
                } else {
                    dialog.dismiss();
                    Toast.makeText(SignupActivity.this, "Field", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void addUser(String fullName, String email, String passWord, String uId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        Map<String, Object> data = new HashMap<>();
        data.put("fullName", fullName);
        data.put("email", email);
        data.put("passWord", passWord);
        data.put("uId", uId);
        myRef.setValue(data);
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
        finish();

    }

    public boolean ValidAll() {
        return IsEmpty(edit_text_emailaddress, "Required")
                & IsEmpty(edit_text_password, "Required")
                & IsEmpty(edit_text_username, "Required");
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
