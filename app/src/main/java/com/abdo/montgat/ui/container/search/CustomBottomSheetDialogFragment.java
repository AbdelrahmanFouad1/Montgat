package com.abdo.montgat.ui.container.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abdo.montgat.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private View view;
    private RelativeLayout raRelativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.fragment_dialog_custom, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        initView();
    }

    private void initView() {
        raRelativeLayout = view.findViewById(R.id.close_RL);
        getChildFragmentManager().beginTransaction().add(R.id.frmContent, new BottomSheetFragment()).commit();


    }

}
