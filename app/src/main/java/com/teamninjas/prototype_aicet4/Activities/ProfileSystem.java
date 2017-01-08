package com.teamninjas.prototype_aicet4.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.teamninjas.prototype_aicet4.R;

public class ProfileSystem extends AppCompatActivity {

    EditText ecollege,eemail,elink;
    ImageButton iedit;
    Button bsave;
    String scollege,semail,slink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_system);
        ecollege = (EditText) findViewById(R.id.institute);
        eemail = (EditText) findViewById(R.id.email);
        iedit = (ImageButton) findViewById(R.id.EditButton);
        elink = (EditText) findViewById(R.id.link);
        bsave = (Button) findViewById(R.id.SaveButton);
    }

    public void edit(View v){
        ecollege.setFocusableInTouchMode(true);
        eemail.setFocusableInTouchMode(true);
        elink.setFocusableInTouchMode(true);
       ecollege.setFocusable(true);
        eemail.setFocusable(true);
        elink.setFocusable(true);
        iedit.setVisibility(View.INVISIBLE);
        bsave.setVisibility(View.VISIBLE);
    }

    public void save(View view){
        scollege = ecollege.getText().toString();
        semail = eemail.getText().toString();
        slink = elink.getText().toString();
        Toast.makeText(getApplicationContext(),"UPDATED",Toast.LENGTH_SHORT).show();
        ecollege.setFocusable(false);
        eemail.setFocusable(false);
        elink.setFocusable(false);
        bsave.setVisibility(View.GONE);
        iedit.setVisibility(View.VISIBLE);
    }
}
