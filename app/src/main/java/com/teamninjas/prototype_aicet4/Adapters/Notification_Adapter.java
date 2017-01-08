package com.teamninjas.prototype_aicet4.Adapters;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.teamninjas.prototype_aicet4.Others.UserObject.Notification_Object;
import com.teamninjas.prototype_aicet4.R;

import java.util.ArrayList;

/**
 * Created by ShivamArora on 08-01-2017.
 */

public class Notification_Adapter extends ArrayAdapter<Notification_Object> {

    Context content ;
    ArrayList<Notification_Object> data ;

    public Notification_Adapter(Context context, ArrayList<Notification_Object> data) {
        super(context, 0);
        this.content = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(content).inflate(R.layout.custom_notification_view , parent , false);
        TextView title = (TextView)v.findViewById(R.id.Notification_Title);
        TextView description = (TextView)v.findViewById(R.id.Notification_message);
        title.setText(data.get(position).getmTitle());
        description.setText(data.get(position).getDescription());
        return v;
    }
}


