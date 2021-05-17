package com.abdo.montgat.ui.container.categories;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.model.CategoryModel;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.utlis.Constant;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements Callback, IOnBackPressed {
    private View view;

    private RecyclerView sideMenuRV;
    //    private List<CategoryModel> categoryModelList;
    private List<CategoryModel> sideMenu;
    private int selectedMenuPos = 0;
    private SideMenuAdapter sideMenuAdapter;
    private String fromHomeTrending;


    public static final int T_SHIRT_FRAGMENT_CODE = 0;
    public static final int SHIRT_FRAGMENT_CODE = 1;
    public static final int SUNGLASSES_FRAGMENT_CODE = 2;
    public static final int COAT_FRAGMENT_CODE = 3;
    public static final int SHOES_FRAGMENT_CODE = 4;
    public static final int TROUSERS_FRAGMENT_CODE = 5;
    public static final int CAP_FRAGMENT_CODE = 6;
    public static final int TRENDING_FRAGMENT_CODE = 7;
    public static final int NEW_ARRIVAL_FRAGMENT_CODE = 8;


    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentcategory, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        sideMenuRV = view.findViewById(R.id.sideList_rv_category);
//        fromHomeTrending = Constant.getDefaults("trending_home", requireActivity());
//        if (fromHomeTrending.equals("fromTrendingHome")) {
//
//        }

        Bundle bundle = this.getArguments();


        sideMenu = new ArrayList<>();


        if (bundle != null) {
            fromHomeTrending = bundle.getString("trending_home");

            if (fromHomeTrending.equals("fromShirtCategory")) {
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new ShirtFragment());
            }
            else if (fromHomeTrending.equals("fromSunglassesCategory")) {
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new SunglassesFragment());
            }
            else if (fromHomeTrending.equals("fromCoatCategory")) {
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new CoatFragment());
            }
            else if (fromHomeTrending.equals("fromShoesCategory")) {
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new ShoesFragment());
            }
            else if (fromHomeTrending.equals("fromTrousersCategory")) {
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new TrousersFragment());
            }
            else if (fromHomeTrending.equals("fromCapCategory")) {
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new CapFragment());
            }
            else if (fromHomeTrending.equals("fromTrendingHome")) {
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new TrendingFragment());
            }
            else if (fromHomeTrending.equals("fromArrivalHome")) {

                sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, true));
                sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
                sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));

                Constant.replaceSideMenu(CategoryFragment.this, new NewArrivalFragment());
            }
        } else {
            sideMenu.add(new CategoryModel("T-Shirt", T_SHIRT_FRAGMENT_CODE, true));
            sideMenu.add(new CategoryModel("Shirt", SHIRT_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Sunglass", SUNGLASSES_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Coat", COAT_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Shoes", SHOES_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Trouser", TROUSERS_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Cap", CAP_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("Trending", TRENDING_FRAGMENT_CODE, false));
            sideMenu.add(new CategoryModel("New Arrival", NEW_ARRIVAL_FRAGMENT_CODE, false));

            Constant.replaceSideMenu(CategoryFragment.this, new T_ShirtFragment());
        }

//        categoryModelList = new FakeData().getDataFromCategoryModel();
        sideMenuAdapter = new SideMenuAdapter(sideMenu, this);
        sideMenuRV.setAdapter(sideMenuAdapter);

        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);


    }

    @Override
    public void onSideMenuItemClick(int selected) {


        switch (sideMenu.get(selected).getCode()) {

            case T_SHIRT_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new T_ShirtFragment());
                break;

            case SHIRT_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new ShirtFragment());
                break;

            case SUNGLASSES_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new SunglassesFragment());
                break;

            case COAT_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new CoatFragment());
                break;

            case SHOES_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new ShoesFragment());
                break;

            case TROUSERS_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new TrousersFragment());
                break;

            case CAP_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new CapFragment());
                break;

            case NEW_ARRIVAL_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new NewArrivalFragment());
                break;

            case TRENDING_FRAGMENT_CODE:
                Constant.replaceSideMenu(CategoryFragment.this, new TrendingFragment());
                break;

        }

        // highlight the selected menu item
        sideMenu.get(selectedMenuPos).setSelected(false);
        sideMenu.get(selected).setSelected(true);
        selectedMenuPos = selected;
        sideMenuAdapter.notifyDataSetChanged();

    }


    private class SideMenuAdapter extends RecyclerView.Adapter<SideMenuAdapter.SideMenuVH> {

        List<CategoryModel> mData;
        Callback listener;
        int row_index = -1 ;

        public SideMenuAdapter(List<CategoryModel> mData, CategoryFragment listener) {
            this.mData = mData;
            this.listener = listener;
        }

        @NonNull
        @Override
        public SideMenuVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
            return new SideMenuVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SideMenuVH holder, int position) {

            CategoryModel item = mData.get(position);

            String name = item.getName();
            boolean select = item.isSelected();

            holder.name.setText(name);

            holder.isSelected.setVisibility(View.GONE);


            if (select) {
                // Light Mode
                holder.isSelected.setVisibility(View.VISIBLE);
//                row_index = position;

                holder.relativeLayout.setBackground(requireActivity().getResources().getDrawable(R.drawable.category_list_selected));
                holder.name.setTextColor(requireActivity().getResources().getColor(R.color.white));

                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Mode
                    holder.relativeLayout.setBackground(requireActivity().getResources().getDrawable(R.drawable.category_list_selected));
                    holder.name.setTextColor(requireActivity().getResources().getColor(R.color.backgroundDark));
                }

            } else {
                // Light Mode

                holder.isSelected.setVisibility(View.GONE);

                holder.relativeLayout.setBackground(requireActivity().getResources().getDrawable(R.drawable.category_list));
                holder.name.setTextColor(requireActivity().getResources().getColor(R.color.dark_gray));

                if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                    //Dark Mode
                    holder.relativeLayout.setBackground(requireActivity().getResources().getDrawable(R.drawable.category_list_dark));
                    holder.name.setTextColor(requireActivity().getResources().getColor(R.color.white_gray));
                }

            }

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class SideMenuVH extends RecyclerView.ViewHolder {

            private TextView name;
            private ImageView isSelected;
            private RelativeLayout relativeLayout;

            public SideMenuVH(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name_tv_categoryItem);
                isSelected = itemView.findViewById(R.id.rectangle_iv_categoryItem);
                relativeLayout = itemView.findViewById(R.id.category_rl_categoryItem);

                // menu item click listener
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSideMenuItemClick(getAdapterPosition());
                    }
                });


            }
        }

    }


    @Override
    public boolean onBackPressed() {
        if (CategoryFragment.this != null) {

            Constant.replaceFragment(CategoryFragment.this, new HomeFragment());
            return true;

        } else {

            return false;
        }
    }
}

