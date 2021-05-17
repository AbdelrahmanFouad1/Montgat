package com.abdo.montgat.ui.container.search;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.utlis.Constant;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SearchFragment extends Fragment {
    private View view;
    private TextView filter, cancel, textCategory, textSize, textGender;
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout container, searchFromBNV, searchFromHome, linearCategory, linearSize, linearGender;
    private ImageView back, closeCategory, closeSize, closeGender;
    private MaterialRippleLayout searchIcon, crossIcon;
    private EditText searchField1, searchField2;
    private String clickSearch, category, size, gender;
    private Animation leftAnimation, rightAnimation, fade_in;
    private RecyclerView filterRV;
    private Button button;
    private final String TAG = "search";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        initView();


            Bundle bundle = this.getArguments();
            if (bundle != null) {

                category = bundle.getString("CategorySheetItem");
                size = bundle.getString("SizeSheetItem");
                gender = bundle.getString("GenderSheetItem");

                Log.d(TAG, "onCreateView: " + category);
                Log.d(TAG, "onCreateView: " + size);
                Log.d(TAG, "onCreateView: " + gender);

                if (category != null){
                    linearCategory.setVisibility(View.VISIBLE);
                    textCategory.setText(category);
                }


                if (size != null){
                    linearSize.setVisibility(View.VISIBLE);
                    textSize.setText(size);
                }


                if (gender != null){
                    linearGender.setVisibility(View.VISIBLE);
                    textGender.setText(gender);
                }


        }

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

    private void initView() {
        filter = view.findViewById(R.id.filter_tv_search);
        container = view.findViewById(R.id.bottom_sheet);
        searchFromBNV = view.findViewById(R.id.search_LL_search);
        searchFromHome = view.findViewById(R.id.home_LL_search);
        back = view.findViewById(R.id.back_iv_search);
        searchIcon = view.findViewById(R.id.search2_MRL_search);
        crossIcon = view.findViewById(R.id.cross2_MRL_search);
        searchField2 = view.findViewById(R.id.searchField2_et_search);
        searchField1 = view.findViewById(R.id.searchField1_et_search);
        cancel = view.findViewById(R.id.cancel_tv_search);
//        filterRV = view.findViewById(R.id.filterItem_rv_search);

        linearCategory = view.findViewById(R.id.category_LL_search);
        linearSize = view.findViewById(R.id.size_LL_search);
        linearGender = view.findViewById(R.id.gender_LL_search);
        textCategory = view.findViewById(R.id.category_TV_search);
        textSize = view.findViewById(R.id.size_TV_search);
        textGender = view.findViewById(R.id.gender_TV_search);
        closeCategory = view.findViewById(R.id.category_IV_search);
        closeSize = view.findViewById(R.id.size_IV_search);
        closeGender = view.findViewById(R.id.gender_IV_search);


        clickSearch = Constant.getDefaults("clickSearch", requireActivity());


        if (clickSearch.equals("hideSomeIcon")) {

            searchFromBNV.setVisibility(View.GONE);
            searchFromHome.setVisibility(View.VISIBLE);

        } else if (clickSearch.equals("viewSomeIcon")) {

            searchFromBNV.setVisibility(View.VISIBLE);
            searchFromHome.setVisibility(View.GONE);

        }

        bottomSheetDialog = new BottomSheetDialog(requireActivity(), R.style.BottomSheetTheme);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new CustomBottomSheetDialogFragment().showNow(getActivity().getSupportFragmentManager(), "TAG");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(SearchFragment.this, new HomeFragment());
            }
        });

        searchField2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = searchField2.getText().toString();

                if (text.isEmpty()) {
                    crossIcon.setVisibility(View.GONE);
                    searchIcon.setVisibility(View.VISIBLE);
                    fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
                    searchIcon.setAnimation(fade_in);

                } else {
                    crossIcon.setVisibility(View.VISIBLE);
                    fade_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in_animation);
                    crossIcon.setAnimation(fade_in);
                    searchIcon.setVisibility(View.GONE);
                }

                crossIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchField2.setText("");
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchField1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                cancel.setAnimation(leftAnimation);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String text = searchField1.getText().toString();

                if (text.isEmpty()) {
                    cancel.setVisibility(View.GONE);
                    rightAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.right_animation);
                    cancel.setAnimation(rightAnimation);

                }else {
                    cancel.setVisibility(View.VISIBLE);
                    leftAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.left_animation);
                    cancel.setAnimation(leftAnimation);
                }

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchField1.setText("");
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textSize.setText(size);
        textGender.setText(gender);

        closeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearCategory.setVisibility(View.GONE);
            }
        });

        closeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSize.setVisibility(View.GONE);
            }
        });

        closeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGender.setVisibility(View.GONE);
            }
        });

    }


}

//    List<FilterSearchModel> item = new ArrayList<>();
//
//        item.add(new FilterSearchModel(category, size, gender));
//
//                filterRV.setAdapter(new FilterItemAdapter(item));


//private class FilterItemAdapter extends RecyclerView.Adapter<FilterItemAdapter.FilterItemVH> {
//
//    List<String> itemList;
//
//    public FilterItemAdapter(List<String> itemList) {
//        this.itemList = itemList;
//    }
//
//    @NonNull
//    @Override
//    public FilterItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(requireActivity()).inflate(R.layout.search_filter_item, parent, false);
//        return new FilterItemVH(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FilterItemVH holder, int position) {
//        String item = itemList.get(position);
//
//
//        holder.textView.setText(item);
//
//
//        if (item != null) {
//
//            holder.imageView.setImageResource(R.drawable.ic_close);
//
//        }
//
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (v.equals(holder.imageView)) {
//                    removeAt(position);
//                }
//
//            }
//        });
//    }
//
//    public void removeAt(int position) {
//        itemList.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, itemList.size());
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemList.size();
//    }
//
//    private class FilterItemVH extends RecyclerView.ViewHolder {
//
//        private TextView textView;
//        private ImageView imageView;
//
//        public FilterItemVH(@NonNull View itemView) {
//            super(itemView);
//
//            textView = itemView.findViewById(R.id.item_tv_filterItem);
//            imageView = itemView.findViewById(R.id.cross_iv_filterItem);
//        }
//    }
//}


// if (category != null) {
//
//         Constant.setDefaults("1", category, requireActivity());
//         List<String> item = new ArrayList<>();
//        item.add(category);
//        filterRV.setAdapter(new FilterItemAdapter(item));
//
//        }else if (category == null){
//        String saveCategory = Constant.getDefaults("1", requireActivity());
//        List<String> item = new ArrayList<>();
//        item.add(saveCategory);
//        filterRV.setAdapter(new FilterItemAdapter(item));
//
//        }
//
//        if (size != null) {
//
//        Constant.setDefaults("2", size, requireActivity());
//        List<String> item2 = new ArrayList<>();
//        item2.add(size);
//        filterRV.setAdapter(new FilterItemAdapter(item2));
//
//        }else if (size == null){
//        String saveSize = Constant.getDefaults("2", requireActivity());
//        List<String> item2 = new ArrayList<>();
//        item2.add(saveSize);
//        filterRV.setAdapter(new FilterItemAdapter(item2));
//
//        }
//
//
//        Constant.setDefaults("3", gender, requireActivity());
//
//
//
//        String saveGender = Constant.getDefaults("3", requireActivity());
//
//
//
