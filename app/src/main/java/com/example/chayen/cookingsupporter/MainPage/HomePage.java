package com.example.chayen.cookingsupporter.MainPage;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.MainPage.History.HistoryListFragment;
import com.example.chayen.cookingsupporter.NavigationAndSearch.AddFoodMenu;
import com.example.chayen.cookingsupporter.NavigationAndSearch.Category.Category;
import com.example.chayen.cookingsupporter.NavigationAndSearch.Profile;
import com.example.chayen.cookingsupporter.NavigationAndSearch.Search;
import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.example.chayen.cookingsupporter.LoginRegister.mAuth;
import static com.example.chayen.cookingsupporter.LoginRegister.mGoogleApiClient;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    Toolbar mToolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    View view;
    RelativeLayout mViewInformation;
    AppCompatTextView mTextViewName, mTextViewMoney, mTitle;

    TabLayout tabLayout;

    MenuItem search_icon, add_food_icon;

    TextView user_profile_name, user_profile_email;
    ImageView user_profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initialize();
        setFirebaseUserProfile();
        setToolbar();
        setDrawer();
        setViewPager();
    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return MainHomePageFragment.newInstance();
                    case 1:
//                        Log.d("test", ""+ HomePage.transactionDetails.getCustomer());
                        return HistoryListFragment.newInstance();
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "HOME";
                    case 1:
                        return "HISTORY";
                    default:
                        return "";
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initialize() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_home_page);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTitle = (AppCompatTextView) findViewById(R.id.title);
        view = navigationView.getHeaderView(0);

        user_profile_name = (TextView)view.findViewById(R.id.user_profile_displayname);
        user_profile_email = (TextView)view.findViewById(R.id.user_profile_email);
        user_profile_image = (ImageView)view.findViewById(R.id.user_profile_image);
    }

    private void setFirebaseUserProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl= user.getPhotoUrl();

//            Log.d("user_profile", ""+ name + "\n" + email + "\n" + photoUrl);

            user_profile_name.setText(name);
            user_profile_email.setText(email);
            Picasso.with(getApplicationContext()).load(photoUrl).into(user_profile_image);
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setTitle("");
    }

    private void setDrawer() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        search_icon = menu.findItem(R.id.menu_search_icon);
        add_food_icon = menu.findItem(R.id.icon_add_food);
        menuItemOnClick();
        return true;
    }

    private void menuItemOnClick(){
        search_icon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(HomePage.this, Search.class);
                startActivity(intent);
                return false;
            }
        });

        add_food_icon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(HomePage.this, AddFoodMenu.class);
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
//        Toast.makeText(HomePage.this, "" + id + " " + R.id.source_of_fund, Toast.LENGTH_SHORT).show();

        if (id == R.id.profile) {
            Intent intent = new Intent(HomePage.this, Profile.class);
            startActivity(intent);
        }else if (id == R.id.category) {
            Intent intent = new Intent(HomePage.this, Category.class);
            startActivity(intent);
        }else if (id == R.id.logout) {
            logout();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logout(){
        signOut();
        Log.d("Check Logout", "test");
    }

    private void signOut() {
        mAuth.signOut();
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                    }
//                });
        HomePage.this.finish();
    }
}
