package com.example.tartas.view.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tartas.R;
import com.example.tartas.view.sign.SigninActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_menu;
    FirebaseAuth mAuth;
    TextView toolbarTitle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_search:
//                        Toast.makeText(MainActivity.this, "Search Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return false;
//            }
//        });

        FrameLayout frameLayout = findViewById(R.id.container);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                int slide = (int) (drawerView.getWidth() * slideOffset);
                frameLayout.setTranslationX(slide);
            }
        };
        actionBarDrawerToggle.syncState();
        nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navListeners(item);
                return false;
            }
        });
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        nav_menu.getMenu().getItem(0).setChecked(true);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.main_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.home).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setText(R.string.cakes).setIcon(R.drawable.ic_baseline_cake_24);
        tabLayout.getTabAt(2).setText(R.string.cart).setIcon(R.drawable.ic_baseline_add_shopping_cart_24);
        tabLayout.getTabAt(3).setText(R.string.account).setIcon(R.drawable.ic_baseline_account_24);

    }


    static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new CakesFragment();
                case 2:
                    return new CartFragment();
                case 3:
                    return new AccountFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    private void navListeners(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myhome:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                break;
            case R.id.myOrders:
                Toast.makeText(MainActivity.this, "My Orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myAddress:
                Toast.makeText(MainActivity.this, "My Address", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contactus:
                Toast.makeText(MainActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, SigninActivity.class));
                break;
        }
    }
}



