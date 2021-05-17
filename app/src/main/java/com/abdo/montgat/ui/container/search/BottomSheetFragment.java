package com.abdo.montgat.ui.container.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.data.Data;
import com.abdo.montgat.utlis.Constant;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

public class BottomSheetFragment extends Fragment {

    private View view;
    private RecyclerView categoryRV, sizeRV, genderRV;
    private MaterialRippleLayout deselectedCategory, deselectedSize, deselectedGender;
    private final String TAG = "bottom_sheet";

    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        categoryRV = view.findViewById(R.id.category_rv_bottomSheet);
        sizeRV = view.findViewById(R.id.size_rv_bottomSheet);
        genderRV = view.findViewById(R.id.gender_rv_bottomSheet);
        deselectedCategory = view.findViewById(R.id.deselected1_MRL_bottomSheet);
        deselectedSize = view.findViewById(R.id.deselected2_MRL_bottomSheet);
        deselectedGender = view.findViewById(R.id.deselected3_MRL_bottomSheet);

        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        List<String> list1 = new Data().getDataFromCategorySheet();
        categoryRV.setAdapter(new CategorySheetAdapter(list1));

        List<String> list2 = new Data().getDataFromSizeSheet();
        sizeRV.setAdapter(new SizeSheetAdapter(list2));

        List<String> list3 = new Data().getDataFromGenderSheet();
        genderRV.setAdapter(new GenderSheetAdapter(list3));



    }

    private class CategorySheetAdapter extends RecyclerView.Adapter<CategorySheetAdapter.CategorySheetVH>{

        List<String> itemList;
        int row_index = -1 ;

        public CategorySheetAdapter(List<String> itemList) {
            this.itemList = itemList;
        }


        @NonNull
        @Override
        public CategorySheetVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.category_sheet_item, parent, false);
            return new CategorySheetVH(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull CategorySheetVH holder, int position) {
            String item = itemList.get(position);


            holder.textView.setText(item);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    String value1 = (String) ((TextView)v).getText();

                  String value1 =   holder.textView.getText().toString();

                    SearchFragment fragment = new SearchFragment ();
                    Bundle args = new Bundle();
                    args.putString("CategorySheetItem", value1);
                    fragment.setArguments(args);
                    Constant.replaceFragment(BottomSheetFragment.this, fragment);

                    Log.d(TAG, "onClick: "+value1);

                    row_index = position;

                    notifyDataSetChanged();
                }
            });

            if(row_index == position ) {

                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                } else {
                    //Light Mode
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                }

                deselectedCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                            //Dark Theme
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                        } else {
                            //Light Mode
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));;
                        }

                    }
                });

            } else if (row_index != position ){
                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                } else {
                    //Light Mode
                    //default
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
                }


            }

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        private class CategorySheetVH extends RecyclerView.ViewHolder{

            LinearLayout row_linearlayout;
            TextView textView;
            public CategorySheetVH(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_tv_categorySheetItem);
                row_linearlayout=(LinearLayout)itemView.findViewById(R.id.item_LL_categorySheetItem);
            }
        }
    }

    private class SizeSheetAdapter extends RecyclerView.Adapter<SizeSheetAdapter.SizeSheetVH>{

        List<String> items;
        int row_index = -1 ;

        public SizeSheetAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public SizeSheetVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.size_sheet_item, parent, false);
            return new SizeSheetVH(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull SizeSheetVH holder, int position) {
            String item = items.get(position);
            holder.textView.setText(item);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = (String) ((TextView)v).getText();

                    SearchFragment fragment = new SearchFragment ();
                    Bundle args = new Bundle();
                    args.putString("SizeSheetItem", value);
                    fragment.setArguments(args);
                    Constant.replaceFragment(BottomSheetFragment.this, fragment);

                    row_index = position;

                    notifyDataSetChanged();
                }
            });

            if(row_index == position ) {

                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                } else {
                    //Light Mode
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                }

                deselectedSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                            //Dark Theme
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                        } else {
                            //Light Mode
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));;
                        }

                    }
                });

            } else if (row_index != position ){
                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                } else {
                    //Light Mode
                    //default
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
                }


            }

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private class SizeSheetVH extends RecyclerView.ViewHolder{

            LinearLayout row_linearlayout;
            TextView textView;

            public SizeSheetVH(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_tv_sizeSheetItem);
                row_linearlayout=(LinearLayout)itemView.findViewById(R.id.item_LL_sizeSheetItem);
            }
        }
    }

    private class GenderSheetAdapter extends RecyclerView.Adapter<GenderSheetAdapter.GenderSheetVH>{

        List<String> items;
        int row_index = -1 ;

        public GenderSheetAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public GenderSheetVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.gender_sheet_item, parent, false);
            return new GenderSheetVH(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull GenderSheetVH holder, int position) {
            String item = items.get(position);
            holder.textView.setText(item);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = (String) ((TextView)v).getText();

                    SearchFragment fragment = new SearchFragment ();
                    Bundle args = new Bundle();
                    args.putString("GenderSheetItem", value);
                    fragment.setArguments(args);
                    Constant.replaceFragment(BottomSheetFragment.this, fragment);

                    row_index = position;

                    notifyDataSetChanged();
                }
            });

            if(row_index == position ) {

                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                } else {
                    //Light Mode
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.yellow));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white));
                }

                deselectedGender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                            //Dark Theme
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                        } else {
                            //Light Mode
                            holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                            holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));;
                        }

                    }
                });

            } else if (row_index != position ){
                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Theme
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.backgroundItemDark));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                } else {
                    //Light Mode
                    //default
                    holder.row_linearlayout.setBackgroundColor(requireActivity().getResources().getColor(R.color.white_gray));
                    holder.textView.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));
                }


            }

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private class GenderSheetVH extends RecyclerView.ViewHolder{

            LinearLayout row_linearlayout;
            TextView textView;

            public GenderSheetVH(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_tv_genderSheetItem);
                row_linearlayout=(LinearLayout)itemView.findViewById(R.id.item_LL_genderSheetItem);
            }
        }
    }


}
