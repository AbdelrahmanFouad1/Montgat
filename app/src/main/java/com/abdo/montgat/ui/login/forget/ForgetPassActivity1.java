package com.abdo.montgat.ui.login.forget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.ContainerActivity;
import com.abdo.montgat.ui.login.Verification.VerificationCodeActivity;
import com.abdo.montgat.ui.login.LoginActivity;
import com.abdo.montgat.utlis.Constant;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgetPassActivity1 extends AppCompatActivity {

    private Button next;
    private ImageView back;
    private CountryCodePicker codePicker;
    private TextInputLayout forgetPhone;
    private String phoneNo, fromUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass1);
        initView();
    }

    private void initView() {
        next = findViewById(R.id.next_btn_forget1);
        back = findViewById(R.id.back_image_forget1);
        forgetPhone = findViewById(R.id.phone_TextInputLayout_forget1);
        codePicker = (CountryCodePicker) findViewById(R.id.codeCountryPiker_forget1);
        fromUpdate = Constant.getDefaults("fromUpdate", getApplicationContext());



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhoneNo()) {
                    return;
                }

                String getUserEnterPhone = forgetPhone.getEditText().getText().toString().trim();

                if (getUserEnterPhone.charAt(0) == '0') {
                    getUserEnterPhone = getUserEnterPhone.substring(1);
                }

                 phoneNo = codePicker.getSelectedCountryCodeWithPlus() + getUserEnterPhone;

                CheckPasswordFromFirebase();

                Constant.setDefaults("phoneNO", phoneNo, getBaseContext());
//                 This is to identify that which action should OTP perform after verification.
                Constant.setDefaults("whatToDO", "updateData", getBaseContext());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fromUpdate.equals("backToUpdate")) {

                    Intent intent = new Intent(ForgetPassActivity1.this, ContainerActivity.class);
                    intent.putExtra("FromReservation", "4");
                    startActivity(intent);

                }else {
                    startActivity(new Intent(ForgetPassActivity1.this, LoginActivity.class));
                }
            }
        });

    }

    private Boolean validatePhoneNo() {
        String val = forgetPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            forgetPhone.setError("Field cannot be empty");
            return false;
        } else {
            forgetPhone.setError(null);
            forgetPhone.setErrorEnabled(false);
            return true;
        }
    }

    private void CheckPasswordFromFirebase() {
        Query checkPassword = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phone").equalTo(phoneNo);
        checkPassword.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    forgetPhone.setError(null);
                    forgetPhone.setErrorEnabled(false);

                    startActivity(new Intent(ForgetPassActivity1.this, VerificationCodeActivity.class));

                } else {
                    forgetPhone.setError("Phone doesn't not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForgetPassActivity1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ForgetPassActivity1.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

}