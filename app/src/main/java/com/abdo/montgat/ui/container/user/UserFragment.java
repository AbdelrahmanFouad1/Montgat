package com.abdo.montgat.ui.container.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.abdo.montgat.R;
import com.abdo.montgat.helper.FavoriteDB;
import com.abdo.montgat.model.UserModel;
import com.abdo.montgat.ui.login.LoginActivity;
import com.abdo.montgat.utlis.Constant;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment {
    private View view;
    private MaterialRippleLayout logInCV, call, darkTheme, notification, wishlist, share, aboutUs, rating, updateProfile, orderHistory, logout;
    private SwitchCompat switchDark;
    private TextView account, userName, email, counterTV;
    private CardView profile, logoutCV;
    private CircleImageView circleImageViewProfile;
    private String phoneNumber;
    private UserFragment userFragment;
    private FavoriteDB favoriteDB;

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {

        logInCV = view.findViewById(R.id.logIn_mrl_user);
        call = view.findViewById(R.id.call_mrl_user);
        darkTheme = view.findViewById(R.id.darkTheme_mrl_user);
        switchDark = view.findViewById(R.id.darkTheme_switch_user);
        notification = view.findViewById(R.id.notification_mrl_user);
        wishlist = view.findViewById(R.id.wishlist_mrl_user);
        share = view.findViewById(R.id.share_mrl_user);
        aboutUs = view.findViewById(R.id.aboutUs_mrl_user);
        rating = view.findViewById(R.id.rating_mrl_user);
        updateProfile = view.findViewById(R.id.updateProfile_mrl_user);
        orderHistory = view.findViewById(R.id.orderHistory_mrl_user);
        account = view.findViewById(R.id.account_tv_user);
        logout = view.findViewById(R.id.logout_mrl_user);
        profile = view.findViewById(R.id.profile_cv_user);
        logoutCV = view.findViewById(R.id.logout_cv_user);
        userName = view.findViewById(R.id.userName_tv_user);
        email = view.findViewById(R.id.email_tv_user);
        counterTV = view.findViewById(R.id.counterItem_tv_user);
        circleImageViewProfile = view.findViewById(R.id.profile_civ_user);
        phoneNumber = Constant.getDefaults("phone", requireActivity());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        auth = FirebaseAuth.getInstance();

        favoriteDB = new FavoriteDB(requireActivity());

        int profile_counts = favoriteDB.getProfilesCount();
        Log.d("counter2", String.valueOf(profile_counts));
        counterTV.setText(profile_counts+"");
        favoriteDB.close();

        if (profile_counts == 0){
            counterTV.setVisibility(View.GONE);
        }

        if (auth.getCurrentUser() != null) {
            account.setVisibility(View.VISIBLE);
            profile.setVisibility(View.VISIBLE);
            logoutCV.setVisibility(View.VISIBLE);

            logInCV.setVisibility(View.GONE);

            if (auth.getCurrentUser() != null) {

                databaseReference.child("Users").child(phoneNumber).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);


                        if (auth.getCurrentUser() != null) {
                            if (userModel.getImage() != null || userModel.getUsername() != null || userModel.getEmail() != null) {

                                userName.setText(userModel.getUsername());
                                email.setText(userModel.getEmail());

                                Picasso.get().load(userModel.getImage()).placeholder(R.drawable.ic_account2).into(circleImageViewProfile);
                            } else {
                                Toast.makeText(requireActivity(), "Error loading image", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            userName.setText("");
                            email.setText("");
                            circleImageViewProfile.setImageDrawable(requireActivity().getResources().getDrawable(R.drawable.ic_account2));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        }


        logInCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireActivity(), LoginActivity.class));


            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(UserFragment.this, new UpdateProfileFragment());

            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constant.replaceFragment(UserFragment.this, new OrderHistoryFragment());

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(UserFragment.this, new NotificationFragment());
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(UserFragment.this, new WishlistFragment());
            }
        });

        darkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switchDark.setChecked(!switchDark.isChecked());

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+201555156821"));
                startActivity(intent);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(requireActivity(), "enable to open "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("Text/Plain");
                String text = " https://github.com/AbdelrahmanFouad1";
                String subject = "This is Montgat Application";

                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);

                startActivity(Intent.createChooser(intent, "Share App"));
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(UserFragment.this, new AboutUsFragment());
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account.setVisibility(View.GONE);
                profile.setVisibility(View.GONE);
                logoutCV.setVisibility(View.GONE);

                logInCV.setVisibility(View.VISIBLE);

                userName.setText("");
                email.setText("");
                circleImageViewProfile.setImageDrawable(requireActivity().getResources().getDrawable(R.drawable.ic_account2));

                auth.signOut();
            }
        });

        checkNightModeActivated();

        switchDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Constant.saveNightModeState(requireActivity(),true);
//                    requireActivity().recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Constant.saveNightModeState(requireActivity(),false);
//                    requireActivity().recreate();
                }

            }
        });

    }

    public void checkNightModeActivated(){
        if (Constant.getNightModeState(requireActivity())){
            switchDark.setChecked(true);
        }else {
            switchDark.setChecked(false);
        }
    }

}
