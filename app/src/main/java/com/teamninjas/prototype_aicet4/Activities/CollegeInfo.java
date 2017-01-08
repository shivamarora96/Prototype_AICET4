package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.teamninjas.prototype_aicet4.Adapters.Page_Adapter;
import com.teamninjas.prototype_aicet4.R;

public class CollegeInfo extends AppCompatActivity {

private FloatingActionButton mfab1,mfab2;

    private ViewPager mViewPager ;
    private Page_Adapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_info);

        mViewPager = (ViewPager)findViewById(R.id.College_viewpager);
        mAdapter =  new Page_Adapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mAdapter);




        mfab1 = (FloatingActionButton) findViewById(R.id.fab);
        mfab2 = (FloatingActionButton) findViewById(R.id.fab2);
        mfab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollegeInfo.this, NewPost.class);
               startActivity(intent);
            }
        });
        mfab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mfab2.setImageResource(R.drawable.liked);
                Toast.makeText(CollegeInfo.this,"Page liked",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
