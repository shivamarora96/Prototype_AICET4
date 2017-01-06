package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.teamninjas.prototype_aicet4.Others.Constants;
import com.teamninjas.prototype_aicet4.R;

public class SinupActivity extends AppCompatActivity {

    EditText mEditText_name , mEditText_password , mEditText_confirmPass , mEditText_emailid  ;
    String mName , mPass, mConfirmPass , mEmailId  , mSex;
    RadioGroup mradioGroup_sex ;
    RadioButton mradioButton_male  ,  mradioButton_female ,  mradioButton_others ;
    Button mButton_Register ;

    final String TAG ="abcde" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup);

        mButton_Register = (Button)findViewById(R.id.Sinup_RegisterButton);
        mEditText_name = (EditText)findViewById(R.id.Sinup_Name);
        mEditText_emailid =(EditText)findViewById(R.id.Sinup_email);
        mEditText_password = (EditText)findViewById(R.id.Sinup_password);
        mEditText_confirmPass = (EditText)findViewById(R.id.Sinup_confirm_password);
        mradioGroup_sex = (RadioGroup)findViewById(R.id.Sinup_sex_radio_group);
        mradioButton_female = (RadioButton)findViewById(R.id.Sinup_radiobutton_female);
        mradioButton_male = (RadioButton)findViewById(R.id.Sinup_radiobutton_male);
        mradioButton_others = (RadioButton)findViewById(R.id.Sinup_radiobutton_other) ;


    }


    @Override
    protected void onStart() {
        super.onStart();

        mButton_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_Values_To_Paramters_And_Post_Over_Server();
                Intent i =new Intent(SinupActivity.this,CollgeStudent.class);
                startActivity(i);
                Log.i(TAG , "Values - > "  +  mName  + " : " + mSex  + " : "+ mEmailId  + " : " + mPass + " : " + mConfirmPass );
            }
        });




    }

    //Set Values TO Params and TODO Post Over server
    private void set_Values_To_Paramters_And_Post_Over_Server() {
        mName = mEditText_name.getText().toString();
        mEmailId = mEditText_emailid.getText().toString();
        mPass = mEditText_password.getText().toString();
        mConfirmPass = mEditText_confirmPass.getText().toString();

        int currentSexId  = mradioGroup_sex.getCheckedRadioButtonId();

        if(currentSexId == mradioButton_male.getId()) {
            mSex = Constants.Sinup_SEX_MALE;
        }
    else if(currentSexId == mradioButton_female.getId())
        {
            mSex = Constants.Sinup_SEX_FEMALE;
        }
    else if(currentSexId == mradioButton_others.getId()){
            mSex = Constants.Sinup_SEX_OTHERS;
        }
    else {
            mSex = "NotFound";
        }

        Log.i(TAG , "CHECK_PARAMS->" + check_params());

        if(check_params()){
            //TODO POST OVER SERVER .........


        }

    }
// Check Is All Values Filled
    private boolean check_params() {
        if(mName!=null && mEmailId!=null && mPass!=null && mConfirmPass!=null && mPass.equals(mConfirmPass) && mPass.length() >=Constants.Sinup_MIN_PASSWORD_LENGTH ){
                if(mSex.equals(Constants.Sinup_SEX_MALE) || mSex.equals(Constants.Sinup_SEX_FEMALE) || mSex.equals(Constants.Sinup_SEX_OTHERS)){

                        return true ;
                }
        }

        return false;
    }


}
