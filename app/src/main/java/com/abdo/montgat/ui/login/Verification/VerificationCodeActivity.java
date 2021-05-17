package com.abdo.montgat.ui.login.Verification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.abdo.montgat.R;
import com.abdo.montgat.model.UserModel;
import com.abdo.montgat.ui.login.Register.SignUpActivity1;
import com.abdo.montgat.ui.login.Register.SignUpActivity3;
import com.abdo.montgat.ui.login.forget.ForgetPassActivity1;
import com.abdo.montgat.ui.login.forget.SetNewPassActivity2;
import com.abdo.montgat.ui.container.ContainerActivity;
import com.abdo.montgat.utlis.Constant;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.TimeUnit;

public class VerificationCodeActivity extends AppCompatActivity {

    private PinView pinView;
    private ImageView close;
    private Button verify;
    private String whatToDO, fullName, username, email, password, gender, birthDay, image, phone, updatePhone;
    private TextView phoneNumber_tv, resend;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        initView();
    }

    private void initView() {
        close = findViewById(R.id.close_image_verification);
        verify = findViewById(R.id.verification_btn_verification);
        pinView = findViewById(R.id.code_pinView_verification);
        phoneNumber_tv = findViewById(R.id.phoneNumber_tv_Verification);
        progressBar = findViewById(R.id.progress_bar);
        resend = findViewById(R.id.resend_btn_verification);

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        fullName = Constant.getDefaults("fullName", getBaseContext());
        username = Constant.getDefaults("username", getBaseContext());
        email = Constant.getDefaults("email", getBaseContext());
        password = Constant.getDefaults("password", getBaseContext());

        gender = Constant.getDefaults("gender", getBaseContext());
        birthDay = Constant.getDefaults("birthDay", getBaseContext());

        image = Constant.getDefaults("image", getBaseContext());
        phone = Constant.getDefaults("phone", getBaseContext());
        whatToDO = Constant.getDefaults("whatToDO", getBaseContext());

        updatePhone = Constant.getDefaults("phoneNO", getBaseContext());


        if (whatToDO.equals("updateData")) {
            phoneNumber_tv.setText(updatePhone);
            startPhoneNumberVerification(updatePhone);

        } else if (whatToDO.equals("createNewUser")) {
            phoneNumber_tv.setText(phone);
            startPhoneNumberVerification(phone);
        }


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = pinView.getText().toString();
                if (code.isEmpty() || code.length() < 6) {
                    pinView.setError("Wrong OTP...");
                    pinView.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                verifyPhoneNumberWithCode(code);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whatToDO.equals("updateData")) {
                    startActivity(new Intent(VerificationCodeActivity.this, ForgetPassActivity1.class));
            } else if (whatToDO.equals("createNewUser")) {
                    startActivity(new Intent(VerificationCodeActivity.this, SignUpActivity3.class));
            }

            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                            //Dark Mode
                            resend.setTextColor(getBaseContext().getResources().getColor(R.color.white_gray));
                        } else {
                            //Light Mode
                            resend.setTextColor(Color.BLACK);
                        }

                    }
                }, 6000);
                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Mode
                    resend.setTextColor(getBaseContext().getResources().getColor(R.color.backgroundItemDark));
                } else {
                    //Light Mode
                    resend.setTextColor(Color.GRAY);
                }

                if (whatToDO.equals("updateData")) {
                    progressBar.setVisibility(View.VISIBLE);
                    resendVerificationCode(updatePhone, mResendToken);

                } else if (whatToDO.equals("createNewUser")) {
                    progressBar.setVisibility(View.VISIBLE);
                    resendVerificationCode(phone, mResendToken);

                }

            }
        });


    }

    private void startPhoneNumberVerification(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        mVerificationInProgress = true;
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code = credential.getSmsCode();
            if (code != null) {
                pinView.setText(code);
                verifyPhoneNumberWithCode(code);
            }
            progressBar.setVisibility(View.VISIBLE);
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(VerificationCodeActivity.this, "Verification Failed, Please try another time !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

            progressBar.setVisibility(View.GONE);
            mVerificationId = verificationId;
            mResendToken = token;

        }
    };

    private void verifyPhoneNumberWithCode(String codeByUser) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, codeByUser);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (whatToDO.equals("updateData")) {
                                updateOldUsersData();
                            } else if (whatToDO.equals("createNewUser")) {
                            storeNewUsersData();

                            }

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerificationCodeActivity.this, "OPT is incorrect, Please try again", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(VerificationCodeActivity.this, "OPT is incorrect, Please try again", Toast.LENGTH_SHORT).show();
                            }
                            //TODO : if code enter by user is incorrect show toast here

                        }
                    }
                });
    }

    private void updateOldUsersData() {
        startActivity(new Intent(getBaseContext(), SetNewPassActivity2.class));
    }

    private void storeNewUsersData() {
        UserModel userModel = new UserModel(fullName, username, email, password, gender, birthDay, image, phone);

        Constant.initRef().child("Users").child(phone).setValue(userModel).addOnCompleteListener(task ->
        {
            Toast.makeText(getBaseContext(), "Added successfully in Firebase", Toast.LENGTH_SHORT).show();

        });

        startActivity(new Intent(getBaseContext(), ContainerActivity.class));
    }

    @Override
    public void onBackPressed() {
        return;
    }

}