package com.abdo.montgat.ui.container.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.abdo.montgat.R;
import com.abdo.montgat.model.UserModel;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.ui.login.forget.ForgetPassActivity1;
import com.abdo.montgat.utlis.Constant;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfileFragment extends Fragment implements IOnBackPressed {
    private View view;
    private CircleImageView profileImage;
    private ImageView back, editImage, editInformation, doneInformation, doneImage;
    private EditText nameField, barthDayField, numberField, emailField, usernameField;
    private String phoneNumber, selectedImageUrl, _IMAGE, _NAME, _EMAIL, _USERNAME, _BIRTHDAY, _NUMBER;
    private Button changePassword;
    private Animation fade_in;
    private Uri selectImage;

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update_profile, container, false);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//            Window window = requireActivity().getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.WHITE);
//
//        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        back = view.findViewById(R.id.back_iv_profile);
        profileImage = view.findViewById(R.id.profile_civ_profile);
        editImage = view.findViewById(R.id.editImage_iv_profile);
        editInformation = view.findViewById(R.id.edit_iv_profile);
        doneInformation = view.findViewById(R.id.done_iv_profile);
        nameField = view.findViewById(R.id.name_et_profile);
        barthDayField = view.findViewById(R.id.barthDay_et_profile);
        numberField = view.findViewById(R.id.numPhone_et_profile);
        emailField = view.findViewById(R.id.email_et_profile);
        usernameField = view.findViewById(R.id.username_et_profile);
        changePassword = view.findViewById(R.id.changePass_btn_profile);
        doneImage = view.findViewById(R.id.doneImage_iv_profile);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        auth = FirebaseAuth.getInstance();

        phoneNumber = Constant.getDefaults("phone", requireActivity());

        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        disableEditText();

        databaseReference.child("Users").child(phoneNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);


                if (auth.getCurrentUser() != null) {
                    if (userModel.getImage() != null || userModel.getFullName() != null || userModel.getEmail() != null || userModel.getUsername() != null || userModel.getBirthDay() != null || userModel.getPhone() != null) {

                        _NAME = userModel.getFullName();
                        nameField.setText(_NAME);
                        _EMAIL = userModel.getEmail();
                        emailField.setText(_EMAIL);
                        _USERNAME = userModel.getUsername();
                        usernameField.setText(_USERNAME);
                        _BIRTHDAY = userModel.getBirthDay();
                        barthDayField.setText(_BIRTHDAY);
                        _NUMBER = userModel.getPhone();
                        numberField.setText(_NUMBER);

                        _IMAGE = userModel.getImage();
                        Log.d("updatepro", _IMAGE);
                        Picasso.get().load(_IMAGE).placeholder(R.drawable.account3).into(profileImage);
                    } else {
                        Toast.makeText(requireActivity(), "Error loading image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    nameField.setText("");
                    emailField.setText("");
                    usernameField.setText("");
                    barthDayField.setText("");
                    numberField.setText("");

                    profileImage.setImageDrawable(requireActivity().getResources().getDrawable(R.drawable.account3));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(UpdateProfileFragment.this, new UserFragment());
            }
        });


        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneImage.setVisibility(View.VISIBLE);
                fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
                doneImage.setAnimation(fade_in);
                editImage.setVisibility(View.GONE);

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 505);

            }
        });

        doneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doneImage.setVisibility(View.GONE);
                editImage.setVisibility(View.VISIBLE);
                fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
                editImage.setAnimation(fade_in);

                if (isImageChange()) {
                    Toast.makeText(requireActivity(), "Image has been uploaded", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(requireActivity(), "same picture! So it is not uploaded", Toast.LENGTH_LONG).show();
                }
            }
        });

        editInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditText();
            }
        });

        doneInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableEditText();
                if (isNameChange() & isUsernameChange() & isBirthDayChange()) {
                    Toast.makeText(requireActivity(), "Data has been update", Toast.LENGTH_LONG).show();
                }
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.setDefaults("fromUpdate", "backToUpdate", requireActivity());
                startActivity(new Intent(requireActivity(), ForgetPassActivity1.class));
            }
        });

    }

    private boolean isImageChange() {
        if (!_IMAGE.equals(selectedImageUrl)) {

            databaseReference.child("Users").child(phoneNumber).child("image").setValue(selectedImageUrl);

            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChange() {

        if (!_NAME.equals(nameField.getText().toString())) {

            databaseReference.child("Users").child(phoneNumber).child("fullName").setValue(nameField.getText().toString());

            return true;
        } else {
            return false;
        }
    }

    private boolean isUsernameChange() {
        if (!_USERNAME.equals(usernameField.getText().toString())) {

            databaseReference.child("Users").child(phoneNumber).child("username").setValue(usernameField.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isBirthDayChange() {
        if (!_BIRTHDAY.equals(barthDayField.getText().toString())) {

            databaseReference.child("Users").child(phoneNumber).child("birthDay").setValue(barthDayField.getText().toString());
            return true;
        } else {
            return false;
        }
    }

//    private boolean isNumberChange() {
//        if (!_NUMBER.equals(numberField.getText().toString())) {
//
//            databaseReference.child("Users").child(phoneNumber).child("phone").setValue(numberField.getText().toString());
//            return true;
//        } else {
//            return false;
//        }
//    }


    private void enableEditText() {
        doneInformation.setVisibility(View.VISIBLE);
        fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
        doneInformation.setAnimation(fade_in);
        editInformation.setVisibility(View.GONE);

        nameField.setEnabled(true);
        barthDayField.setEnabled(true);
        numberField.setEnabled(false);
        usernameField.setEnabled(true);
        emailField.setEnabled(false);

        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
            //Dark Mode
            nameField.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
            barthDayField.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
            usernameField.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
            emailField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            numberField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
        } else {
            //light mode
            nameField.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
            barthDayField.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
            usernameField.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
            emailField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            numberField.setTextColor(requireActivity().getResources().getColor(R.color.gray));

        }
    }

    private void disableEditText() {

        doneInformation.setVisibility(View.GONE);
        editInformation.setVisibility(View.VISIBLE);
        fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
        editInformation.setAnimation(fade_in);

        nameField.setEnabled(false);
        barthDayField.setEnabled(false);
        numberField.setEnabled(false);
        usernameField.setEnabled(false);
        emailField.setEnabled(false);


        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
            //Dark Mode
            nameField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            barthDayField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            usernameField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            emailField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            numberField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
        } else {
            //Light Mode
            nameField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            barthDayField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            emailField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            numberField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
            usernameField.setTextColor(requireActivity().getResources().getColor(R.color.gray));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 505 && resultCode == requireActivity().RESULT_OK && data != null) {
            selectImage = data.getData();
            Picasso.get().load(selectImage).into(profileImage);
            uploadImage(selectImage);
        }


    }

    private void uploadImage(Uri selectImage) {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("uploadImages/" + selectImage.getLastPathSegment());
        uploadTask = ref.putFile(selectImage);

        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(requireActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
                Log.d("updatepro", selectedImageUrl);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        Constant.replaceFragment(UpdateProfileFragment.this, new UserFragment());
        return true;
    }
}
