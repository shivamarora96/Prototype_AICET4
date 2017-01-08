package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.teamninjas.prototype_aicet4.Adapters.Master_Adapter;
import com.teamninjas.prototype_aicet4.R;

public class MasterAcitivty extends AppCompatActivity {

    private TabLayout mTablayout ;
    private ViewPager mViewPager ;
    private ImageView mSearch ;
    private Master_Adapter mAdapter ;
    private DrawerLayout mdrawerLayout ;
    private NavigationView mNavigationView ;
    private ActionBarDrawerToggle mActionBarDrawerToggle ;
    private AutoCompleteTextView actv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_acitivty);


        mSearch=(ImageView)findViewById(R.id.search_button);

        //setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.Master_toolbar);
        toolbar.setTitle("SCRAP BOOK");
        setSupportActionBar(toolbar);

        actv=(AutoCompleteTextView)findViewById(R.id.search_bar);
        String[] colleges = {"Maharaja Agrasen Institute of Technology","Bharti Vidyapeeth College of Engineering","Maharaja Surajmal Institute of Technology","Northern India Engineering College"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,colleges);
        actv.setAdapter(adapter);


        mNavigationView = (NavigationView)findViewById(R.id.Master_navigaitonVIew);
        mdrawerLayout = (DrawerLayout)findViewById(R.id.Master_drawerLayout);
        mTablayout = (TabLayout)findViewById(R.id.Master_tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.Master_viewpager);
        mAdapter =  new Master_Adapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(0);
        setupDrawerLayout();

        //Set Viewpager  and Adapter to Tablayout
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        mTablayout.getTabAt(0).setIcon(R.drawable.ic_not);
        mTablayout.getTabAt(1).setIcon(R.drawable.ic_notifications_none_black_24dp);

        //Set Drawer Toggler
        mActionBarDrawerToggle = new ActionBarDrawerToggle(MasterAcitivty.this  , mdrawerLayout , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close ) ;
        mdrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MasterAcitivty.this,CollegeInfo.class);
                startActivity(i);
            }
        });

    }

    private void setupDrawerLayout() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.DrawerMenu_profile:
                        Intent i = new Intent(getApplicationContext(), ProfileSystem.class);
                        startActivity(i);
                        finish();
                        break;
                    /*case R.id.DrawerMenu_aboutUs:
                        Intent j = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(j);
                        finish();
                        break;*/

                }


                return true;
            }
        });
    }

}
