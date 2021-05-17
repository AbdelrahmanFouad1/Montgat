package com.abdo.montgat.ui.login.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.login.LoginActivity;
import com.abdo.montgat.ui.login.Verification.VerificationCodeActivity;
import com.abdo.montgat.utlis.Constant;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity1 extends AppCompatActivity {

    private ImageView regBack;
    private TextInputLayout regFullName, regUsername, regEmail, regPassword;
    private Button regNext;
    private String fullName, username, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        initView();
    }

    private void initView() {
        regBack = findViewById(R.id.back_image_signUp1);
        regFullName = findViewById(R.id.fullName_TextInputLayout_signUp1);
        regUsername = findViewById(R.id.username_TextInputLayout_signUp1);
        regEmail = findViewById(R.id.email_TextInputLayout_signUp1);
        regPassword = findViewById(R.id.password_TextInputLayout_signUp1);
        regNext = findViewById(R.id.next_btn_signUp1);

        auth = FirebaseAuth.getInstance();

        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName() |!validatePassword() | !validateEmail() | !validateUsername())
                {
                    return;
                }

                 fullName = regFullName.getEditText().getText().toString();
                 username = regUsername.getEditText().getText().toString();
                 email = regEmail.getEditText().getText().toString();
                 password = regPassword.getEditText().getText().toString();

                CheckUsername();

                Constant.setDefaults("fullName", fullName, getBaseContext());
                Constant.setDefaults("username", username, getBaseContext());
                Constant.setDefaults("email", email, getBaseContext());
                Constant.setDefaults("password", password, getBaseContext());

            }
        });

        regBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity1.this, LoginActivity.class));
            }
        });
    }

    private Boolean validateName() {
        String val = regFullName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regFullName.setError("Field cannot be empty");
            return false;
        }
        else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void CheckUsername() {
        Query checkPassword = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(username);
        checkPassword.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    regUsername.setError("Username is already exist");


                } else {

                    signInWithEmail();

                    regUsername.setError(null);
                    regUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(SignUpActivity1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithEmail() {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(SignUpActivity1.this, SignUpActivity2.class));

                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignUpActivity1.this, "Wrong in Email register", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignUpActivity1.this, "Email is already exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignUpActivity1.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


