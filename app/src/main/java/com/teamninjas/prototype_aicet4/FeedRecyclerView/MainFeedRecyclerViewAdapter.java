package com.teamninjas.prototype_aicet4.FeedRecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamninjas.prototype_aicet4.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Anshulgarg on 06-01-2017.
 */
public class MainFeedRecyclerViewAdapter extends RecyclerView.Adapter<MainFeedRecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflator;
    List<MainFeedObject> data = Collections.emptyList();
    Context c;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;
    static Boolean check_like = true ,check_dislike = false;

    public MainFeedRecyclerViewAdapter(Context context, List<MainFeedObject> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        c = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.main_feed_recyclerview_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        final MainFeedObject current = data.get(position);

            holder.tv_title.setText(current.getTitle());

        holder.tv_desc.setText(current.getDesc());


            Picasso.with(c)
                    .load(current.getImageUrl())
                    .into(holder.im_imageUrl);


        holder.b_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_like){
                    holder.b_likes.setColorFilter(c.getResources().getColor(R.color.Blue));
                    //new Send_Like().execute();
                    //hit json to decrease like and change check_like
                  //  notifyDataSetChanged();
                }else{
                    //holder.b_likes.setBackgroundColor(Color.parseColor("#FF9900"));
                    holder.b_likes.setColorFilter(c.getResources().getColor(R.color.Blue));
                    //hit json to increase like and change check_like
                   // new Send_Like().execute();
                    //notifyDataSetChanged();
                }



            }
        });


        holder.b_dislikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_dislike){
                    holder.b_dislikes.setBackgroundColor(Color.parseColor("#000000"));
                    //hit json to decrease dislike and change check_dislike
                   // new Send_Dislike().execute();
                    //notifyDataSetChanged();
                }else{
                   // holder.b_dislikes.setBackgroundColor(Color.parseColor("#FF9900"));
                    holder.b_dislikes.setColorFilter(c.getResources().getColor(R.color.Blue));
                    //hit json to increase dislike and change check_dislike
                   // new Send_Dislike().execute();

                   // notifyDataSetChanged();
                }



            }
        });


    }

    public List<MainFeedObject> getList() {

        return data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView im_imageUrl;
        TextView tv_title, tv_desc;
        TextView tv_numberLikes,tv_numberDislikes;
        ImageView b_likes,b_dislikes;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_numberLikes = (TextView) itemView.findViewById(R.id.feed_no_likes);
            tv_numberDislikes = (TextView) itemView.findViewById(R.id.feed_no_dislikes);
            tv_title = (TextView) itemView.findViewById(R.id.feed_title);
            tv_desc = (TextView) itemView.findViewById(R.id.feed_desc);
            im_imageUrl = (ImageView) itemView.findViewById(R.id.feed_image);
            b_likes = (ImageView) itemView.findViewById(R.id.feed_like);
            b_dislikes = (ImageView) itemView.findViewById(R.id.feed_dislike);





        }

    }

    public void put(){
        SharedPreferences pref = c.getSharedPreferences("check",Context.MODE_PRIVATE);
        check_like = pref.getBoolean("check_like", false);
        check_dislike = pref.getBoolean("check_dislike",false);
    }

    //for likes-json
    public class Send_Like extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("anyurl");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("check_like", check_like);
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            notifyDataSetChanged();
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Log.d("string", "" + key);
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }

    //for dislike-json

    public class Send_Dislike extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("anyurl");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("check_dislike", check_dislike);
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            notifyDataSetChanged();
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Log.d("string", "" + key);
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}
