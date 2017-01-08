package com.teamninjas.prototype_aicet4.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teamninjas.prototype_aicet4.Adapters.Notification_Adapter;
import com.teamninjas.prototype_aicet4.Others.UserObject.Notification_Object;
import com.teamninjas.prototype_aicet4.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ShivamArora on 05-01-2017.
 */

public class Notification_Fragment extends Fragment {

    ListView listView ;
    Notification_Adapter adapter;
    ArrayList<Notification_Object> data;
    public Notification_Fragment() {
        super();
    }
    String titleList[] = {"Examination Centre ( May-June 2015 )","RESULT IS OUT!","Football Trials","CSI, MAIT chapter interviews 2015","Aayaam auditions forms 2015","Phrenics : auditions!!","Information Security workshop MAIT-CSI","E-gnite, The Annual Entrepreneurship Event At CVS"};

    String descList[] = {"Centre of Maharaja Agrasen Institute of Technology for the upcoming End Term Exams 2015 is HMR.",
            "BTech Semester 2,4,6 and 8 result for GGSIPU is declared. Please check IPU official website.",
            "welcome freshers and fellow MAITIANS. Get a chance to be a part of college's football team. Be there to give your best to the football trials Q. Where are the trials?Ans . sharp on 10 AUG 2015 i.e Monday Q. Where? A. Football ground, right infront of 9th block. Q. Outfit? A. Shorts and tees, dont come in jeans. Also, not allowed to give trials in sleepers, please wear proper shoes, studs Q. Whom to contact? A. Just call on 9818716750. Spread the word to MAITIANS who are not informed.",
            "It is to inform you that the Computer Society of India(CSI), MAIT chapter is going to conduct interviews for the post of junior coordinators from 2nd year. The details are as follows :  Date : 21st Aug 2015. Time and Place : Gather between 1st and 3rd block at 11:00 AM . Interested students can mail their Name, Phone, Roll No. and Branch at maitcomputersociety@gmail.com.",
            "AAYAAM, The Dramatics society of MAIT auditions forms are availabe at photocopy shop near back canteen. Kindly fill and submit the form there itself by 14th Aug 2015, Friday.No applicant will be entertained after that. For further queries contact : President - G. Abhishek 9015663144 PR Head - Shubham Sharma 9911373898",
            "Phrenics, the film making society of MATES is conductiong auditions. date : 19.08.2015, Wednesday Venue : Place to be disclosed.Contact : lovit sawhney : 9958674459 ishaan bhasin : 9953720891",
            "Computer society of India (MAIT chapter)along with appin technologies is conducting a workshop on Information Security and Ethical Hacking. The workshop will be a one day event and the students will be taught how to prevent themselves from external hacks, how to make your accounts much secure and much more. Certification of workshop by appin technologies will be given to everyone.Date : October 3,2015 , Time:9:30 am - 5:00 pm.Workshop fees : Rs 250/-",
            "Hello Everyone!The Entrepreneurial Board of College Of Vocational Studies brings to you E-gnite- The annual Entrepreneurship Summit of the college. It is a two day event with interactive sessions and workshops by eminent speakers on  1. Idea Generation  2. Funding Your StartUp.  3.Finance 4. Marketing  5. Pitching your B-Plan A pitching competition and a bunch of fun events too shall be organised. Dates - 13th and 14th October. So register here and grab the opportunity .tinyurl.com/e-gnite7"};


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("abcde" , "Noti TAB") ;

        View v = inflater.inflate(R.layout.fragment_notifications , container ,false) ;

        data = new ArrayList<>(8);

      for(int i = 0 ; i < 8 ; i ++){

          Notification_Object notification_object = new Notification_Object(titleList[i] , descList[i].substring(0, 80) + " ..... ");
          data.add(i , notification_object);
      }

        listView = (ListView)v.findViewById(R.id.Noti_lv);
        adapter = new Notification_Adapter(getActivity() , data);
        listView.setAdapter(adapter);


        return v ;
        }



}
