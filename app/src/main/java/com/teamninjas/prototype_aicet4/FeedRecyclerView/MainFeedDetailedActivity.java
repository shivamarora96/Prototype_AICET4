package com.teamninjas.prototype_aicet4.FeedRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.teamninjas.prototype_aicet4.R;


/**
 * Created by Anshulgarg on 06-01-2017.
 */
public class MainFeedDetailedActivity extends AppCompatActivity {

    public static String house = "";
    int count = 0;
    ImageView im_d_imageUrl;
    TextView tv_d_title, tv_d_desc;
    String imageUrl,title, desc;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_feed_detailed_view);

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");

        imageUrl = getIntent().getStringExtra("imageurl");

        tv_d_title = (TextView) findViewById(R.id.d_feed_title);
        tv_d_desc = (TextView) findViewById(R.id.d_feed_desc);

        im_d_imageUrl = (ImageView) findViewById(R.id.d_feed_image);

        tv_d_title.setText(title);
        tv_d_desc.setText(desc);

        Picasso.with(this)
                .load(imageUrl)
                .into(im_d_imageUrl);

        recyclerView.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        count = 0;
    }
}
