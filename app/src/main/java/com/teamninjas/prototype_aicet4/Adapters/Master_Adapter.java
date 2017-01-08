package com.teamninjas.prototype_aicet4.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teamninjas.prototype_aicet4.Fragments.MainFeedRecyclerView_Fragment;
import com.teamninjas.prototype_aicet4.Fragments.Notification_Fragment;

/**
 * Created by ShivamArora on 05-01-2017.
 */

public class Master_Adapter extends FragmentPagerAdapter {

    private  final int tabCount = 2 ;
    private final String[] tabTitle = {"NEW FEEDS ", " NOTIFICATION "} ;



    public Master_Adapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return new MainFeedRecyclerView_Fragment();
        }

        else if(position == 1)
        return new Notification_Fragment();

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return tabTitle[position] ;
        }



}
