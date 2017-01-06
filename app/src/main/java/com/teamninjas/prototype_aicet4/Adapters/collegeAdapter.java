package com.teamninjas.prototype_aicet4.Adapters;

/**
 * Created by Apekshit on 04-01-2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamninjas.prototype_aicet4.R;
import com.teamninjas.prototype_aicet4.Others.UserObject.college;
import com.teamninjas.prototype_aicet4.Others.UserObject.collegeitem;

import java.util.List;


public class collegeAdapter extends RecyclerView.Adapter<collegeAdapter.MyViewHolder>  {

        private List<college> collegeList;
        Context cont;
        public int checked=0;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor ;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView name;
            public ImageView image;
            collegeitem ci;

            @Override
            public void onClick(View v) {
               // Toast.makeText(cont,"clicked at pos"+getPosition(),Toast.LENGTH_SHORT).show();
               // Log.d("ABC","clicked at pos"+getPosition()+getAdapterPosition());
                checked=sharedpreferences.getInt("pos"+getPosition(),-1);
               if(checked==0) {
                   v.setBackgroundColor(Color.LTGRAY);
                   image.setImageResource(R.drawable.check);
                   editor.putInt("pos"+getPosition(),1);
               }else if(checked==1){
                   v.setBackgroundColor(Color.WHITE);
                   image.setImageResource(0);

                   editor.putInt("pos"+getPosition(),0);


               }
                else if(checked==-1){
                   Toast.makeText(cont,"-1",Toast.LENGTH_SHORT).show();
               }
                editor.commit();
            }
            public MyViewHolder(View view) {
                super(view);
               sharedpreferences = cont.getSharedPreferences("ABC", Context.MODE_PRIVATE);
               editor = sharedpreferences.edit();
                for(int y=0;y<collegeList.size();y++){
                    editor.putInt("pos"+y,0);

                }
                editor.commit();
                name = (TextView) view.findViewById(R.id.name);
                image=(ImageView)view.findViewById(R.id.tickimage) ;
                itemView.setOnClickListener(this);
                }
        }



        public collegeAdapter(List<college> collegeList, Context c) {
            this.collegeList = collegeList;
            cont=c;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.college_row, parent, false);

            return new MyViewHolder(itemView);
        }
        

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            college c = collegeList.get(position);
            holder.name.setText(c.getName());
           }

        @Override
        public int getItemCount() {
            return collegeList.size();
        }
    }

