package com.abdo.montgat.ui.container.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.data.Data;
import com.abdo.montgat.helper.FavoriteDB;
import com.abdo.montgat.model.CategoryHomeModel;
import com.abdo.montgat.model.ProductModel;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.ui.container.cart.CartFragment;
import com.abdo.montgat.ui.container.categories.CategoryFragment;
import com.abdo.montgat.ui.container.productShow.ProductFragment;
import com.abdo.montgat.ui.container.search.SearchFragment;
import com.abdo.montgat.ui.container.user.UpdateProfileFragment;
import com.abdo.montgat.ui.container.user.UserFragment;
import com.abdo.montgat.ui.login.LoginActivity;
import com.abdo.montgat.utlis.Constant;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.io.Serializable;
import java.util.List;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, CallbackCategory, IOnBackPressed, Serializable {

    private View view;
    private SliderView sliderView;
    private RecyclerView categoryRV, trendingRV, newRV;
    private MaterialRippleLayout search, seeCategory, seeTrending, seeArrival;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Menu menu;
    private FirebaseAuth auth;
    private List<CategoryHomeModel> categoryMenu;
    private FavoriteDB favoriteDB;
    private CategoryHomeAdapter categoryHomeAdapter;
    private CategoryFragment fragment;
    private Bundle args, bundle2;
    private ProductFragment productFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();


    }

    private void initView() {
        sliderView = view.findViewById(R.id.imageSlider);
        categoryRV = view.findViewById(R.id.category_rv_home);
        trendingRV = view.findViewById(R.id.trending_rv_home);
        newRV = view.findViewById(R.id.new_rv_home);
        search = view.findViewById(R.id.search_MRL_home);
        seeCategory = view.findViewById(R.id.seeAll_category_mrl_home);
        seeTrending = view.findViewById(R.id.seeAll_trending_mrl_home);
        seeArrival = view.findViewById(R.id.seeAll_new_mrl_home);

        auth = FirebaseAuth.getInstance();

        fragment = new CategoryFragment();
        args = new Bundle();

        seeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());

            }
        });

        seeTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());

                args.putString("trending_home", "fromTrendingHome");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);

            }
        });

        seeArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());

                args.putString("trending_home", "fromArrivalHome");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);

            }
        });

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        toolbar = view.findViewById(R.id.toolbar);

        SliderAdapter sliderAdapter = new SliderAdapter(new Data().getDataFromSlider());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();


        categoryMenu = new Data().getDataFromCategory();
        categoryHomeAdapter = new CategoryHomeAdapter(categoryMenu, this);
        categoryRV.setAdapter(categoryHomeAdapter);

//        Constant.replaceSideMenu(HomeFragment.this, new T_ShirtFragment());

        List<ProductModel> list2 = new Data().getDataFromTrending();
        trendingRV.setAdapter(new TrendingHomeAdapter(list2));

        List<ProductModel> list3 = new Data().getDataFromNewArrival();
        newRV.setAdapter(new NewHomeAdapter(list3));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(HomeFragment.this, new SearchFragment());
                Constant.setDefaults("clickSearch", "hideSomeIcon", requireActivity());

            }
        });


        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(requireActivity(),
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(HomeFragment.this);
        navigationView.setCheckedItem(R.id.nav_home);


        if (auth.getCurrentUser() != null) {
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_profile).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);
        }


    }


    @Override
    public void onCategoryClick(int selectedPosition) {

        switch (categoryMenu.get(selectedPosition).getCode()) {

            case Data.T_SHIRT_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                break;

            case Data.SHIRT_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromShirtCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

            case Data.SUNGLASSES_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromSunglassesCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

            case Data.COAT_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromCoatCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

            case Data.SHOES_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromShoesCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

            case Data.TROUSERS_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromTrousersCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

            case Data.CAP_FRAGMENT_HOME_CODE:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                args.putString("trending_home", "fromCapCategory");
                fragment.setArguments(args);
                Constant.replaceFragment(HomeFragment.this, fragment);
                break;

        }

        categoryHomeAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Constant.replaceFragment(HomeFragment.this, new HomeFragment());
                break;

            case R.id.nav_cart:
                Constant.replaceFragment(HomeFragment.this, new CartFragment());
                break;

            case R.id.nav_category:
                Constant.replaceFragment(HomeFragment.this, new CategoryFragment());
                break;

            case R.id.nav_setting:
                Constant.replaceFragment(HomeFragment.this, new UserFragment());
                break;

            case R.id.nav_profile:
                Constant.replaceFragment(HomeFragment.this, new UpdateProfileFragment());
                break;


            case R.id.nav_login:
                startActivity(new Intent(requireActivity(), LoginActivity.class));
                menu.findItem(R.id.nav_logout).setVisible(true);
                menu.findItem(R.id.nav_profile).setVisible(true);
                menu.findItem(R.id.nav_login).setVisible(false);
                break;

            case R.id.nav_logout:
                auth.signOut();
                menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                break;

            case R.id.nav_contact:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+201555156821"));
                startActivity(intent);
                break;

            case R.id.nav_rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(requireActivity(), "enable to open " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.nav_Share:
                Intent intent2 = new Intent(Intent.ACTION_SEND);


                intent2.setType("Text/Plain");
                String text = " https://github.com/AbdelrahmanFouad1";
                String subject = "This is Montgat Application";

                intent2.putExtra(Intent.EXTRA_TEXT, text);
                intent2.putExtra(Intent.EXTRA_SUBJECT, subject);

                startActivity(Intent.createChooser(intent2, "Share App"));
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

        List<Integer> imageList;

        public SliderAdapter(List<Integer> imageList) {
            this.imageList = imageList;
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.image_slider, parent, false);
            return new SliderAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

            viewHolder.imageView.setImageResource(imageList.get(position));

        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        private class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            private ImageView imageView;

            public SliderAdapterVH(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageViewSlider);
            }
        }
    }

    public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeAdapterVH> {

        List<CategoryHomeModel> categoryList;
        CallbackCategory listener;

        public CategoryHomeAdapter(List<CategoryHomeModel> categoryList, CallbackCategory listener) {
            this.categoryList = categoryList;
            this.listener = listener;
        }


        @NonNull
        @Override
        public CategoryHomeAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.category_home_item, parent, false);
            return new CategoryHomeAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHomeAdapterVH holder, int position) {
            CategoryHomeModel category = categoryList.get(position);

            int image = category.getImage();
            String name = category.getName();

            holder.imageView.setImageResource(image);
            holder.textView.setText(name);

        }

        @Override
        public int getItemCount() {
            return categoryList.size();
        }

        private class CategoryHomeAdapterVH extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView textView;
            private LinearLayout isSelect;

            public CategoryHomeAdapterVH(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_categoryHome_item);
                textView = itemView.findViewById(R.id.name_categoryHome_item);
                isSelect = itemView.findViewById(R.id.category_ll_item);

                // menu item click listener
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onCategoryClick(getAdapterPosition());
                    }
                });

            }
        }
    }

    private class TrendingHomeAdapter extends RecyclerView.Adapter<TrendingHomeAdapter.TrendingHomeAdapterVH> {

        List<ProductModel> trendingList;

        public TrendingHomeAdapter(List<ProductModel> trendingList) {
            this.trendingList = trendingList;
        }

        @NonNull
        @Override
        public TrendingHomeAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            favoriteDB = new FavoriteDB(requireActivity());
            //create table on first
            SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                createTableOnFirstStartTrending();
            }
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.trending_home_item, parent, false);
            return new TrendingHomeAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TrendingHomeAdapterVH holder, int position) {
            ProductModel trending = trendingList.get(position);

            readCursorDataTrending(trending, holder);

            int image = trending.getImage();
            String label = trending.getLabel();
            String price = trending.getPrice();

            holder.imageView.setImageResource(image);
            holder.label.setText(label);
            holder.price.setText(price);

            holder.trending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle2 = new Bundle();
                    productFragment = new ProductFragment();
                    bundle2.putSerializable("description", trending);
                    bundle2.putString("FROM_HOME","trending");
                    productFragment.setArguments(bundle2);
                    Constant.replaceFragment(HomeFragment.this, productFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return trendingList.size();
        }

        private class TrendingHomeAdapterVH extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView label, price;
            private LinearLayout trending;

            public TrendingHomeAdapterVH(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.image_iv_trendingHome_item);
                label = itemView.findViewById(R.id.label_tv_trendingHome_item);
                price = itemView.findViewById(R.id.price_tv_trendingHome_item);
                trending = itemView.findViewById(R.id.trending_ll_item);
            }
        }
    }


    private void createTableOnFirstStartTrending() {
        favoriteDB.insertEmpty();

        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorDataTrending(ProductModel trending, TrendingHomeAdapter.TrendingHomeAdapterVH holder) {
        Cursor cursor = favoriteDB.read_all_data(trending.getKey_id());
        SQLiteDatabase db = favoriteDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoriteDB.FAVORITE_STATUS));
                trending.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
//                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
//                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

    }


    private class NewHomeAdapter extends RecyclerView.Adapter<NewHomeAdapter.NewHomeAdapterVH> {

        List<ProductModel> newList;

        public NewHomeAdapter(List<ProductModel> newList) {
            this.newList = newList;
        }

        @NonNull
        @Override
        public NewHomeAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.new_home_item, parent, false);
            return new NewHomeAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewHomeAdapterVH holder, int position) {
            ProductModel newHome = newList.get(position);

            int image = newHome.getImage();
            String label = newHome.getLabel();
            String price = newHome.getPrice();

            holder.imageView.setImageResource(image);
            holder.label.setText(label);
            holder.price.setText(price);

            holder.arrival.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle2 = new Bundle();
                    productFragment = new ProductFragment();
                    bundle2.putSerializable("description", newHome);
                    bundle2.putString("FROM_HOME","newArrival");
                    productFragment.setArguments(bundle2);
                    Constant.replaceFragment(HomeFragment.this, productFragment);

                }
            });

        }

        @Override
        public int getItemCount() {
            return newList.size();
        }

        private class NewHomeAdapterVH extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView label, price;
            private LinearLayout arrival;

            public NewHomeAdapterVH(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.image_iv_newHome_item);
                label = itemView.findViewById(R.id.label_tv_newHome_item);
                price = itemView.findViewById(R.id.price_tv_newHome_item);
                arrival = itemView.findViewById(R.id.newArrival_ll_item);
            }
        }

    }


    private void confirmDialog() {

        final AlertDialog alert = new AlertDialog.Builder(
                new ContextThemeWrapper(requireActivity(), R.style.AppCompatAlertDialogStyle))
                .create();
        alert.setTitle("Are you sure?");
        alert.setMessage("Do you want to exit an App");
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        alert.dismiss();

                        requireActivity().finish();

                    }
                });

        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        alert.dismiss();

                    }
                });

        alert.show();
    }


    @Override
    public boolean onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;

        } else {
            confirmDialog();
            return true;
        }
    }

}


// TODO : Best animation

//        CUBEINDEPTHTRANSFORMATION
//        DEPTHTRANSFORMATION
//        VERTICALFLIPTRANSFORMATION
//        ZOOMOUTTRANSFORMATION
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);