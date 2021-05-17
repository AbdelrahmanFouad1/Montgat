package com.abdo.montgat.ui.container;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.container.cart.CartFragment;
import com.abdo.montgat.ui.container.categories.CategoryFragment;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.ui.container.search.SearchFragment;
import com.abdo.montgat.ui.container.user.UserFragment;
import com.abdo.montgat.utlis.Constant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContainerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        initBottomNav();
    }

    private void initBottomNav() {
        bottomNavigationView = findViewById(R.id.bottom_nav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){

                    case R.id.homeFragment:
                        fragment = new HomeFragment();
                        break;

                    case R.id.categoryFragment:
                        fragment = new CategoryFragment();
                        break;

                    case R.id.searchFragment:
                        fragment = new SearchFragment();
                        Constant.setDefaults("clickSearch", "viewSomeIcon", getBaseContext());
                        break;

                    case R.id.userFragment:

//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                            Window w = getWindow();
//                            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//                        }
                        fragment = new UserFragment();
                        break;

                        case R.id.cartFragment:
                        fragment = new CartFragment();
                        break;

                }

               getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).addToBackStack(null).commit();

        Intent i = getIntent();
        String data = i.getStringExtra("FromReservation");

        if (data != null && (data.contentEquals("1") || data.contentEquals("2") || data.contentEquals("3") || data.contentEquals("4"))) {


            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new UserFragment()).commit();

            bottomNavigationView.setSelectedItemId(R.id.userFragment);

        }

//TODO : It's work

//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
//        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
            bottomNavigationView.setSelectedItemId(R.id.homeFragment);
        }else {
            bottomNavigationView.setSelectedItemId(R.id.homeFragment);
        }
    }



//     else if (bottomNavigationView.getSelectedItemId() == R.id.categoryFragment) {
//        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
//
//    }

}