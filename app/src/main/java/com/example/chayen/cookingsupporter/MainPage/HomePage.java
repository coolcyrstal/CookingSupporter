package com.example.chayen.cookingsupporter.MainPage;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initialize();
        //setToolbar();
        setDrawer();
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
    }

    private void initialize() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_home_page);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTitle = (AppCompatTextView) findViewById(R.id.title);
        view = navigationView.getHeaderView(0);
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

    private void logout(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
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

            // Handle the camera action
        }else if (id == R.id.category) {

        }else if (id == R.id.logout) {
            logout();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
