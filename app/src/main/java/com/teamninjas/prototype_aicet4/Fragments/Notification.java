package com.teamninjas.prototype_aicet4.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamninjas.prototype_aicet4.R;

/**
 * Created by ShivamArora on 05-01-2017.
 */

public class Notification extends Fragment {

    public Notification() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("abcde" , "Noti TAB") ;
        return inflater.inflate(R.layout.fragment_notifications , container ,false) ;
            }
}
