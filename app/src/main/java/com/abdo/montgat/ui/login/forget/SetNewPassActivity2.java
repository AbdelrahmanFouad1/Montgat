package com.abdo.montgat.ui.login.forget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abdo.montgat.R;
import com.abdo.montgat.utlis.Constant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SetNewPassActivity2 extends AppCompatActivity {

    private Button update;
    private ImageView back;
    private TextInputLayout newPassword, newConfirmPassword;
    private String passwordNew, confirmPassword, phone;
//    private AuthCredential credential;
    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_pass2);
        initView();
    }

    private void initView() {
        update = findViewById(R.id.update_btn_forget3);
        back = findViewById(R.id.back_image_forget3);
        newPassword = findViewById(R.id.password_TextInputLayout_forget3);
        newConfirmPassword = findViewById(R.id.confirmPassword_TextInputLayout_forget3);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


//        user = FirebaseAuth.getInstance().getCurrentUser();
//        String email = user.getEmail();
//        String passwordOld = user.get();
//        credential = EmailAuthProvider
//                .getCredential(email, "password1234");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword() | !validateConfirmPassword()) {
                    return;
                }

                passwordNew = newPassword.getEditText().getText().toString();
                phone = Constant.getDefaults("phoneNO", getBaseContext());

                updateDataFirebase();

                startActivity(new Intent(SetNewPassActivity2.this, DoneUpdateActivity3.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetNewPassActivity2.this, ForgetPassActivity1.class));
            }
        });

    }

    private void updateDataFirebase() {


        user.updatePassword(passwordNew)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Constant.initRef().child("Users").child(phone).child("password").setValue(passwordNew);
                        Log.d("TAG", "User password updated.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SetNewPassActivity2.this, "Password not updated", Toast.LENGTH_SHORT).show();
            }
        });


//        user.reauthenticate(credential)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Constant.initRef().child("Users").child(phone).child("password").setValue(password);
////                                        Toast.makeText(SetNewPassActivity2.this, "Password updated", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(SetNewPassActivity2.this, "Error password not updated", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        } else {
//                            Toast.makeText(SetNewPassActivity2.this, "Error auth failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

    private Boolean validatePassword() {
        String val = newPassword.getEditText().getText().toString();
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
            newPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            newPassword.setError("Password is too weak");
            return false;
        } else {
            newPassword.setError(null);
            newPassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String val = newConfirmPassword.getEditText().getText().toString();
        String val2 = newPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            newConfirmPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(val2)) {
            newConfirmPassword.setError("Password doesn't not match");
            return false;
        } else {
            newConfirmPassword.setError(null);
            newConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SetNewPassActivity2.this, ForgetPassActivity1.class);
        startActivity(intent);
        finish();
    }

}