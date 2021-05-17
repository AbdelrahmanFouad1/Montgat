package com.abdo.montgat.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.ContainerActivity;
import com.abdo.montgat.ui.login.Register.SignUpActivity1;
import com.abdo.montgat.ui.login.forget.ForgetPassActivity1;
import com.abdo.montgat.utlis.Constant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout loginEmail, loginPassword;
    private Button forgetPassword, go, newUser_signUP;
    private ProgressBar progressBar;
    private String getEmail, getPassword;
    private ImageView back;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        loginEmail = findViewById(R.id.email_TextInputLayout_login);
        loginPassword = findViewById(R.id.password_TextInputLayout_login);
        forgetPassword = findViewById(R.id.forgetPassword_btn_login);
        go = findViewById(R.id.go_btn_login);
        newUser_signUP = findViewById(R.id.sinUp_btn_login);
        progressBar = findViewById(R.id.progress_bar_login);
        back = findViewById(R.id.back_iv_main);

        auth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Constant.setDefaults("login", "back", getBaseContext());
                Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
                intent.putExtra("FromReservation", "1");
                startActivity(intent);



            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateEmail()) {
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                getEmail = loginEmail.getEditText().getText().toString();
                getPassword = loginPassword.getEditText().getText().toString();

                CheckDataFromFirebase();

            }
        });

        newUser_signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity1.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity1.class));
            }
        });


    }

    private void CheckDataFromFirebase() {
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(getEmail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEmail.setError(null);
                    loginEmail.setErrorEnabled(false);

                    CheckPasswordFromFirebase();

                } else {
                    progressBar.setVisibility(View.GONE);
                    loginEmail.setError("Email does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CheckPasswordFromFirebase() {
        Query checkPassword = FirebaseDatabase.getInstance().getReference("Users").orderByChild("password").equalTo(getPassword);
        checkPassword.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginPassword.setError(null);
                    loginPassword.setErrorEnabled(false);

                    auth.signInWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                Intent intent = new Intent(LoginActivity.this, SureToEnter.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("login", "error: "+task.getException().getMessage());
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });


                } else {
                    progressBar.setVisibility(View.GONE);
                    loginPassword.setError("Password does not match");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateEmail() {
        String val = loginEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            loginEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            loginEmail.setError("Invalid email address");
            return false;
        } else {
            loginEmail.setError(null);
            loginEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = loginPassword.getEditText().getText().toString();


        if (val.isEmpty()) {
            loginPassword.setError("Field cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            loginPassword.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
        intent.putExtra("FromReservation", "3");
        startActivity(intent);

    }

}