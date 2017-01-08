package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.teamninjas.prototype_aicet4.Network.Request.Sinup_Request;
import com.teamninjas.prototype_aicet4.Network.Response.Response_Sinup;
import com.teamninjas.prototype_aicet4.Network.Network_Utilities.Retrofit_Client;
import com.teamninjas.prototype_aicet4.Network.Network_Utilities.Retrofit_Interface;
import com.teamninjas.prototype_aicet4.Others.Constants;
import com.teamninjas.prototype_aicet4.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinupActivity extends AppCompatActivity {

    EditText mEditText_name , mEditText_password , mEditText_confirmPass , mEditText_emailid , mUsername_EditText ;
    String mName , mPass, mConfirmPass , mEmailId  , mSex , mUserName ;
    String mCheck_faculity = null  ;
    RadioGroup mradioGroup_sex  , mradioGroup_faculity;
    RadioButton mradioButton_male  ,  mradioButton_female ,  mradioButton_others , mradioButton_faculity_yes , mradioButton_faculity_no ;
    Button mButton_Register ;
    final String TAG ="abcde" ;

    String college[] = {"MAIT", "NIEC", "MSIT", "NSIT"};
    int colll = 0 ;
    String colldata = null;
    AutoCompleteTextView mCollege_AutoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup);

        mButton_Register = (Button)findViewById(R.id.Sinup_RegisterButton);
        mEditText_name = (EditText)findViewById(R.id.Sinup_Name);
        mEditText_emailid =(EditText)findViewById(R.id.Sinup_email);
        mradioButton_faculity_no = (RadioButton) findViewById(R.id.Sinup_radiobutton_Faculity_no) ;
        mradioButton_faculity_yes = (RadioButton)findViewById(R.id.Sinup_radiobutton_Faculity_yes);
        mEditText_password = (EditText)findViewById(R.id.Sinup_password);
        mEditText_confirmPass = (EditText)findViewById(R.id.Sinup_confirm_password);
        mradioGroup_sex = (RadioGroup)findViewById(R.id.Sinup_sex_radio_group);
        mradioButton_female = (RadioButton)findViewById(R.id.Sinup_radiobutton_female);
        mradioButton_male = (RadioButton)findViewById(R.id.Sinup_radiobutton_male);
        mUsername_EditText = (EditText)findViewById(R.id.Sinup_User_Name);
        mradioGroup_faculity = (RadioGroup)findViewById(R.id.Sinup_Is_Faculity_radioGRoup);

//        mradioButton_others = (RadioButton)findViewById(R.id.Sinup_radiobutton_other) ;

    }


    @Override
    protected void onStart() {
        super.onStart();

//TODO REMOVE IT  LATER ..............
        mButton_Register.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(SinupActivity.this , AdditionalInfo.class));
                return  true ;
            }
        });

        if( getSharedPreferences("data" ,MODE_PRIVATE)!=null)
        Log.i("abcde" , getSharedPreferences("data" ,MODE_PRIVATE).getString(Constants.SHARED_PREF_COLLEGE_NAME_KEY , "no"));

        mCollege_AutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.Sinup_college);
        ArrayAdapter<String> ar_obj2 = new ArrayAdapter<String>(SinupActivity.this, android.R.layout.select_dialog_item, college);
        mCollege_AutoCompleteTextView.setAdapter(ar_obj2);
        mCollege_AutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colldata = mCollege_AutoCompleteTextView.getText().toString();
                Log.d("collegedata", "" + colldata);
                colll = colldata.length();

                SharedPreferences sharedPreferences = getSharedPreferences("data" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.SHARED_PREF_COLLEGE_NAME_KEY , colldata );
                editor.apply();

            }
        });

        mButton_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_Values_To_Paramters_And_Post_Over_Server(view);
                Intent i =new Intent(SinupActivity.this,CollgeStudent.class);
//                startActivity(i);
                Log.i(TAG , "Values - > "  +  mName  + " : " + mSex  + " : "+ mEmailId  + " : ->" + mCheck_faculity+ "<-" + mPass + " : " + mConfirmPass );
            }
        });

    }


    //Set Values TO Params and TODO Post College Data Over server
    private void set_Values_To_Paramters_And_Post_Over_Server(View snackbar_required_View) {

        final SweetAlertDialog loding = loading_dialogue();
        parse_ValueTO_Varaibles_from_Views();
        Log.i(TAG , "CHECK_PARAMS->" + check_params());
        if(check_params()){
            //TODO POST OVER SERVER .........
            boolean is_female = false ;
            boolean is_teacher = false;
            if(mSex.equals(Constants.Sinup_SEX_FEMALE)){
                is_female = true ;
            }
            if(mCheck_faculity.equals(Constants.SINUP_YES_FACULITY)){
                is_teacher = true;
            }

            Request_Server_And_Show_Dialogues(loding, is_female, is_teacher);
        }
        else {
            //IF DETAILS ARE INCORRECT
            if((loding != null) && loding.isShowing()) {
                loding.dismiss();
            }
            Snackbar.make(snackbar_required_View , "Kindly Check Your Details Again !! " , Snackbar.LENGTH_LONG).show();
        }
    }

    private void Request_Server_And_Show_Dialogues(final SweetAlertDialog loding, boolean is_female, boolean is_teacher) {
        Sinup_Request sinup_request = new Sinup_Request(mEmailId , mUserName , mPass ,is_teacher , is_female ) ;
        Retrofit_Interface retrofit_interface = Retrofit_Client.getClient().create(Retrofit_Interface.class) ;
        Call<Response_Sinup> response_sinupCall = retrofit_interface.call_sinup_body(sinup_request);
        response_sinupCall.enqueue(new Callback<Response_Sinup>() {
            @Override
            public void onResponse(Call<Response_Sinup> call, Response<Response_Sinup> response) {

                Log.i(TAG , "RESPONSE_>" + response.isSuccessful() ) ;
                if(response.isSuccessful()) {
                    Toast.makeText(SinupActivity.this, "CHECK YOUR MAIL BOX", Toast.LENGTH_SHORT).show();

                    if((loding != null) && loding.isShowing()){
                        loding.dismiss();
                        show_successfullDialogue();
                    }

                }
                else {
                    if((loding != null) && loding.isShowing()) {
                        loding.dismiss();
                    }
                        Toast.makeText(SinupActivity.this , "USERNAME NOT AVAILABLE" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response_Sinup> call, Throwable t) {
                Log.i(TAG , "RESPONSE_>" + "Error" ) ;
                Toast.makeText(SinupActivity.this , t.getMessage().toString()  , Toast.LENGTH_LONG).show();


                if((loding != null) && loding.isShowing()){
                    loding.dismiss();

                    show_error_dialogue();
                }

            }
        });
    }

    private void show_successfullDialogue() {
        final SweetAlertDialog success = new SweetAlertDialog(SinupActivity.this , SweetAlertDialog.SUCCESS_TYPE);
        success.setTitleText("SUCCESSFULL");
        success.setContentText("WELCOME "  + mName + "TO SCRAPBOOK");
        success.setCancelable(false);
        success.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                    success.dismiss();
                //TODO ADD INTENT TO IT............
            }
        });
        success.show();
    }

    private void parse_ValueTO_Varaibles_from_Views() {
        mName = mEditText_name.getText().toString();
        mEmailId = mEditText_emailid.getText().toString();
        mPass = mEditText_password.getText().toString();
        mConfirmPass = mEditText_confirmPass.getText().toString();
        mUserName = mUsername_EditText.getText().toString();

        int currentSexId  = mradioGroup_sex.getCheckedRadioButtonId();
        int currentFaculityId = mradioGroup_faculity.getCheckedRadioButtonId();

        if(currentSexId == mradioButton_male.getId()) {
            mSex = Constants.Sinup_SEX_MALE;
        }
    else if(currentSexId == mradioButton_female.getId())
        {
            mSex = Constants.Sinup_SEX_FEMALE;
        }
//    else if(currentSexId == mradioButton_others.getId()){
//            mSex = Constants.Sinup_SEX_OTHERS;
//        }
        if(currentFaculityId == mradioButton_faculity_yes.getId()) {
            mCheck_faculity = Constants.SINUP_YES_FACULITY;
        }
        else if(currentFaculityId == mradioButton_faculity_no.getId())
        {   mCheck_faculity = Constants.SINUP_NO_FACULITY ;
        }


    else {
            mSex = "NotFound";
        }
    }

    @NonNull
    private SweetAlertDialog loading_dialogue() {
         SweetAlertDialog loding = new SweetAlertDialog(SinupActivity.this , SweetAlertDialog.PROGRESS_TYPE) ;
        loding.setTitleText("LOADING");
        loding.setCancelable(false);
        loding.setContentText("PLEASE WAIT ");
        loding.show();
        return loding;
    }

    private void show_error_dialogue() {
        final SweetAlertDialog un_success = new SweetAlertDialog(SinupActivity.this , SweetAlertDialog.ERROR_TYPE);
        un_success.setTitleText("ERROR");
        un_success.setContentText("NETWORK ERROR!!");
        un_success.setCancelable(false);
        un_success.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                un_success.dismiss();
            }
        });
        un_success.show();
    }

    // Check Is All Values Filled //TODO ALSO CHECK FOR INSTITUTE
    private boolean check_params() {
        if(mName!=null && mEmailId!=null && mCheck_faculity !=null && mUserName!=null && mPass!=null && mConfirmPass!=null && mPass.length() >=Constants.Sinup_MIN_PASSWORD_LENGTH ){
                if(mSex.equals(Constants.Sinup_SEX_MALE) || mSex.equals(Constants.Sinup_SEX_FEMALE) || mSex.equals(Constants.Sinup_SEX_OTHERS)){

                    if( mPass.equals(mConfirmPass) )
                    return true ;

                    else {
                        Toast.makeText(SinupActivity.this , "PASSWORD DID'NT MATCHED" , Toast.LENGTH_LONG).show();
                    }
                }
        }
        return false;
    }

}
