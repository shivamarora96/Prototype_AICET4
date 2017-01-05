package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamninjas.prototype_aicet4.R;

public class CollgeStudent extends AppCompatActivity {

    Boolean check;
    TextView tskip;
    LinearLayout ll_coll, ll_cour, ll_bran, ll_yr;
    AutoCompleteTextView coll, cour, bran, yr;


    String college[] = {"MAIT", "NIEC", "MSIT", "NSIT"};
    String course[] = {"BTECH", "BSC", "MSC", "MTECH"};
    String branch[] = {"IT", "CSE", "MAE", "ECE"};
    String year[] = {"1", "2", "3", "4"};
    String colldata = null, courdata = null, brandata = null, yrdata = null;
    int colll = 0, courl = 0, branl = 0, yrl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collge_student);
        tskip = (TextView) findViewById(R.id.skipid);
        tskip.setPaintFlags(tskip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ll_coll = (LinearLayout) findViewById(R.id.ll_college);
        ll_cour = (LinearLayout) findViewById(R.id.ll_course);
        ll_bran = (LinearLayout) findViewById(R.id.ll_branch);
        ll_yr = (LinearLayout) findViewById(R.id.ll_year);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);


        coll = (AutoCompleteTextView) findViewById(R.id.p_college);
        ArrayAdapter ar_obj2 = new ArrayAdapter<String>(CollgeStudent.this, android.R.layout.select_dialog_item, college);
        coll.setAdapter(ar_obj2);
        coll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colldata = coll.getText().toString();
                Log.d("collegedata", "" + colldata);
                colll = colldata.length();

            }
        });


        cour = (AutoCompleteTextView) findViewById(R.id.p_course);

        ArrayAdapter ar_obj3 = new ArrayAdapter<String>(CollgeStudent.this, android.R.layout.select_dialog_item, course);
        cour.setAdapter(ar_obj3);
        cour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courdata = cour.getText().toString();
                Log.d("coursedata", "" + courdata);
                courl = courdata.length();
            }
        });


        bran = (AutoCompleteTextView) findViewById(R.id.p_branch);
        ArrayAdapter ar_obj4 = new ArrayAdapter<String>(CollgeStudent.this, android.R.layout.select_dialog_item, branch);
        bran.setAdapter(ar_obj4);
        bran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                brandata = bran.getText().toString();
                Log.d("branchdata", "" + brandata);

                branl = brandata.length();
            }
        });


        yr = (AutoCompleteTextView) findViewById(R.id.p_year);
        ArrayAdapter ar_obj5 = new ArrayAdapter<String>(CollgeStudent.this, android.R.layout.select_dialog_item, year);
        yr.setAdapter(ar_obj5);
        yr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yrdata = yr.getText().toString();
                Log.d("yeardata", "" + yrdata);
                yrl = yrdata.length();
            }
        });

    }

    public void submit(View v) {

        check = feed();
        // check = true;
        if (check.equals(true)) {
            Intent i = new Intent(CollgeStudent.this, AdditionalInfo.class);
           startActivity(i);
        }else
            Toast.makeText(getApplicationContext(),"PLEASE FILL ALL DETAILS",Toast.LENGTH_LONG).show();

        //send all the data to the backend
    }


    public void skip(View view) {
        Intent i = new Intent(CollgeStudent.this,AdditionalInfo.class);
        startActivity(i);
    }

    //to check if any field is not empty
    public Boolean feed() {
        if (colll == 0) {
            coll.setError("*Required Field");
            return false;
        }

        if (courl == 0) {
            cour.setError("*Required Field");
            return false;
        }

        if (branl == 0) {
            bran.setError("*Required Field");
            return false;
        }

        if (yrl == 0) {
            yr.setError("*Required Field");
            return false;
        }

        return true;

    }
}
