package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.teamninjas.prototype_aicet4.R;

public class NewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
    }
    public void submit(View view){
        Intent i=new Intent(this,CollegeInfo.class);
        startActivity(i);

    }
}
