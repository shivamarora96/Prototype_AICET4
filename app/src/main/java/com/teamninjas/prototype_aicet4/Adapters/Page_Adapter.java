package com.teamninjas.prototype_aicet4.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teamninjas.prototype_aicet4.Fragments.MainFeedRecyclerView_Fragment;

/**
 * Created by apekshit on 8/1/17.
 */


    public class Page_Adapter extends FragmentPagerAdapter {




        public Page_Adapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
        return new MainFeedRecyclerView_Fragment();
        }


    @Override
    public int getCount() {
        return 1;
    }
}


