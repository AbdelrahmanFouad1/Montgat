package com.abdo.montgat.ui.container.user;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.utlis.Constant;

public class NotificationFragment extends Fragment implements IOnBackPressed {
    private View view;
    private ImageView back;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);

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

        back = view.findViewById(R.id.back_iv_notification);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(NotificationFragment.this, new UserFragment());
            }
        });

    }


    @Override
    public boolean onBackPressed() {
        Constant.replaceFragment(NotificationFragment.this, new UserFragment());
        return true;
    }
}
