package com.github.handioq.baseandroid.ui.base.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.handioq.baseandroid.R;

import butterknife.BindView;

public abstract class NavigationActivity extends ToolBarActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    ImageView ivProfile;
    TextView tvUserName;
    TextView tvEmail;

    @Override
    protected int getResId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void afterInject(Bundle savedInstanceState) {
        super.afterInject(savedInstanceState);

        View headerLayout = navigationView.getHeaderView(0);
        ivProfile = (ImageView) headerLayout.findViewById(R.id.profile_image);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(SettingsActivity.getInstance(NavigationActivity.this, true));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        tvUserName = (TextView) headerLayout.findViewById(R.id.username);
        tvEmail = (TextView) headerLayout.findViewById(R.id.email);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                return switchContentFragment(menuItem.getItemId());
            }
        });

        navigationView.setItemIconTintList(null);

        // Initializing ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                tvUserName.setText("Username username");
                tvEmail.setText("superemail@gmail.com");
                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_home);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    abstract protected boolean switchContentFragment(int id);

    protected void forceNavigationItemChecked(int menuItemId) {
        MenuItem item = navigationView.getMenu().findItem(menuItemId);
        if (!item.isChecked()) {
            item.setChecked(true);
        }
    }

    protected Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }
}
