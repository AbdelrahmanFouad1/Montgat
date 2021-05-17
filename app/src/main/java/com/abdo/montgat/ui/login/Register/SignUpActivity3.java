package com.abdo.montgat.ui.login.Register;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.login.Verification.VerificationCodeActivity;
import com.abdo.montgat.utlis.Constant;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SignUpActivity3 extends AppCompatActivity {

    private Button next;
    private ImageView back, regUserImage;
    private CountryCodePicker countryCodePicker;
    private TextInputLayout regPhone;

    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private Uri selectImage;
    private String selectedImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
        initView();
    }

    private void initView() {
        next = findViewById(R.id.next_btn_signUp3);
        back = findViewById(R.id.back_image_signUp3);
        regUserImage = findViewById(R.id.userPhoto_iv_signUp3);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.codeCountryPiker_signUp3);
        regPhone = findViewById(R.id.phone_TextInputLayout_signUp33);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePhoneNo()) {
                    return;
                }

                String getUserEnterPhone = regPhone.getEditText().getText().toString().trim();

                if (getUserEnterPhone.charAt(0) == '0') {
                    getUserEnterPhone = getUserEnterPhone.substring(1);
                }

                final String phoneNo = countryCodePicker.getSelectedCountryCodeWithPlus() + getUserEnterPhone;

                Intent intent = new Intent(SignUpActivity3.this, VerificationCodeActivity.class);

                startActivity(intent);

                Constant.setDefaults("phone", phoneNo, getBaseContext());
//                 This is to identify that which action should OTP perform after verification.
                Constant.setDefaults("whatToDO", "createNewUser", getBaseContext());
                Constant.setDefaults("image", selectedImageUrl, getBaseContext());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity3.this, SignUpActivity2.class));
            }
        });
/*image cropped
        regUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(SignUpActivity3.this);


            }
        });

 */

        regUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 505);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 505 && resultCode == getParent().RESULT_OK && data != null) {
            selectImage = data.getData();
            Picasso.get().load(selectImage).into(regUserImage);
            uploadImage(selectImage);
        }

/*image cropped
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                selectImage = result.getUri();
                Picasso.get().load(selectImage).into(regUserImage);
                uploadImage(selectImage);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
 */
    }

    private void uploadImage(Uri selectImage) {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + selectImage.getLastPathSegment());
        uploadTask = ref.putFile(selectImage);

        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri downloadUri = task.getResult();

                selectedImageUrl = downloadUri.toString();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getBaseContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validatePhoneNo() {
        String val = regPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhone.setError("Field cannot be empty");
            return false;
        } else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignUpActivity3.this, SignUpActivity2.class);
        startActivity(intent);
        finish();
    }


}