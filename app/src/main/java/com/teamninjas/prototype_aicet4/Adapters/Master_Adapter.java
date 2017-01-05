package com.teamninjas.prototype_aicet4.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.teamninjas.prototype_aicet4.FeedRecyclerView.MainFeedRecyclerView;
import com.teamninjas.prototype_aicet4.Fragments.NewsFeed;
import com.teamninjas.prototype_aicet4.Fragments.Notification;

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
            return new MainFeedRecyclerView();
        }

        else if(position == 1)
        return new Notification();

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
