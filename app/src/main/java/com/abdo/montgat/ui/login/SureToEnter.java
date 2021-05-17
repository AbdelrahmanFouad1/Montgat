package com.abdo.montgat.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.ContainerActivity;
import com.abdo.montgat.ui.login.forget.ForgetPassActivity1;
import com.abdo.montgat.utlis.Constant;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class SureToEnter extends AppCompatActivity {

    private Button go;
    private ImageView close;
    private CountryCodePicker codePicker;
    private TextInputLayout surePhone;
    private String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_to_enter);
        initView();
    }

    private void initView() {
        go = findViewById(R.id.go_btn_sure);
        close = findViewById(R.id.back_image_sure);
        surePhone = findViewById(R.id.phone_TextInputLayout_sure);
        codePicker = (CountryCodePicker) findViewById(R.id.codeCountryPiker_sure);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhoneNo()) {
                    return;
                }

                String getUserEnterPhone = surePhone.getEditText().getText().toString().trim();

                if (getUserEnterPhone.charAt(0) == '0') {
                    getUserEnterPhone = getUserEnterPhone.substring(1);
                }

                phoneNo = codePicker.getSelectedCountryCodeWithPlus() + getUserEnterPhone;
                CheckPasswordFromFirebase();
                Constant.setDefaults("phone", phoneNo, getBaseContext());


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SureToEnter.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void CheckPasswordFromFirebase() {
        Query checkPassword = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phone").equalTo(phoneNo);
        checkPassword.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    surePhone.setError(null);
                    surePhone.setErrorEnabled(false);

                    Intent intent = new Intent(SureToEnter.this, ContainerActivity.class);
                    intent.putExtra("FromReservation", "2");
                    startActivity(intent);

                } else {
                    surePhone.setError("Phone doesn't not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SureToEnter.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validatePhoneNo() {
        String val = surePhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            surePhone.setError("Field cannot be empty");
            return false;
        } else {
            surePhone.setError(null);
            surePhone.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        return;

    }

}