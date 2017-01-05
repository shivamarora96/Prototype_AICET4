package com.teamninjas.prototype_aicet4.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.teamninjas.prototype_aicet4.Adapters.Master_Adapter;
import com.teamninjas.prototype_aicet4.R;

public class MasterAcitivty extends AppCompatActivity {

    private TabLayout mTablayout ;
    private ViewPager mViewPager ;
    private Master_Adapter mAdapter ;
    private DrawerLayout mdrawerLayout ;
    private NavigationView mNavigationView ;
    private ActionBarDrawerToggle mActionBarDrawerToggle ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_acitivty);

        //setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.Master_toolbar);
        toolbar.setTitle("SCRAP BOOK");
        setSupportActionBar(toolbar);

        mNavigationView = (NavigationView)findViewById(R.id.Master_navigaitonVIew);
        mdrawerLayout = (DrawerLayout)findViewById(R.id.Master_drawerLayout);
        mTablayout = (TabLayout)findViewById(R.id.Master_tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.Master_viewpager);
        mAdapter =  new Master_Adapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(0);

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

        if(!isGooglePlayServicesAvailable(MasterAcitivty.this)){
            Toast.makeText(MasterAcitivty.this , "Please Update Your GOOGLe Play Service" , Toast.LENGTH_LONG).show();


        }

    }

//Check For GOOGLE PLAY SERVICE UPDATE .........................
    public boolean isGooglePlayServicesAvailable(final Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            Log.i("PService" , "Not Available");
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            AlertDialog playServiceDialogue = new AlertDialog.Builder(activity)
                    .setTitle("Not Compatible ")
                    .setMessage("Please Update Your Google Play Service !!")
                    .setIcon(android.R.drawable.stat_notify_error)
                    .setNeutralButton(" OK ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    })
                    .create() ;
            playServiceDialogue.show();

            return false;
        }

        return true;
    }







}
