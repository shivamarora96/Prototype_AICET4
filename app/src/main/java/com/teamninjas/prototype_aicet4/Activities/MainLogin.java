package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.teamninjas.prototype_aicet4.Network.Network_Utilities.Retrofit_Client;
import com.teamninjas.prototype_aicet4.Network.Network_Utilities.Retrofit_Interface;
import com.teamninjas.prototype_aicet4.Network.Request.Login_Request;
import com.teamninjas.prototype_aicet4.Network.Response.Response_Login;
import com.teamninjas.prototype_aicet4.R;

import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    EditText eemail,epassword;
    String semail,spassword , susername ;
    TextView tforgot,tnotuser;


    //fbsignin
    CallbackManager callbackManager;

    LoginButton login;

    //for google signin

    private SignInButton signInButton;
    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //sadfsdf constant to check the activity result
    private int RC_SIGN_IN = 100;

    //TextViews

    private NetworkImageView profilePhoto;

    //Image Loader
    private ImageLoader imageLoader;

    //TODO CONNECT IT WITH SERVER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main_login);
        eemail = (EditText) findViewById(R.id.Login_email);
        epassword = (EditText) findViewById(R.id.Login_password);
        tforgot = (TextView) findViewById(R.id.forgot);
        tnotuser = (TextView) findViewById(R.id.Login_notuser);

        //to underline text
        tforgot.setPaintFlags(tforgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tnotuser.setPaintFlags(tnotuser.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //setListner To tnotuser
        tnotuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSinup = new Intent(MainLogin.this , SinupActivity.class);
                startActivity(toSinup);



            }
        });

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);

        //fbtasks
        callbackManager = CallbackManager.Factory.create();
        login = (LoginButton)findViewById(R.id.login_button);
        login.setReadPermissions("public_profile email");

        if(AccessToken.getCurrentAccessToken() != null){
            RequestData();

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccessToken.getCurrentAccessToken() != null) {

                }
            }
        });

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if(AccessToken.getCurrentAccessToken() != null){
                    RequestData();

                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }

    public void login(View view){
        semail = eemail.getText().toString();

        check_if_userName_OR_email();

        spassword = epassword.getText().toString();
        Toast.makeText(getApplicationContext(),"check "+semail+spassword,Toast.LENGTH_LONG).show();

        Request_server() ;



        //send data to backend to catch the response-if ok then intent otherwise toast of wrong password.
    }

    private void check_if_userName_OR_email() {
        //Function Put value in semail oe susername according to entered value

        int j = -1;
        int k = - 1;

        for(int i = 0 ; i <semail.length() ; i++){
            Log.i("zaq" , "\n > " + semail.charAt(i));
            if( semail.charAt(i) == '@') {
                j = i;
            }
            if(semail.charAt(i) == '.'){
                k = i ;
            }
        }

        if( j == -1  && k ==-1 ){
            susername = semail;
            semail = null;
        }
        else {
            susername = null;
        }
    }

    private void Request_server() {

        final SweetAlertDialog loading = new SweetAlertDialog(MainLogin.this , SweetAlertDialog.PROGRESS_TYPE);
        loading.setTitleText("Loading");
        loading.setCancelable(false);
        loading.show();

        Retrofit_Interface interfLogin = Retrofit_Client.getClient().create(Retrofit_Interface.class);
        Call<Response_Login> login_call = null ;

        Log.i("zaq" , "userName > " + susername + " email > " + semail);

        if(susername == null && semail!=null) {
           login_call =  interfLogin.call_LoginPost_with_body(new Login_Request(null, semail, spassword));
        }
        else if(semail == null && susername!=null){
            login_call = interfLogin.call_LoginPost_with_body(new Login_Request(susername , null , spassword));
        }

        login_call.enqueue(new Callback<Response_Login>() {
            @Override
            public void onResponse(Call<Response_Login> call, Response<Response_Login> response) {

                if(loading.isShowing()){
                    loading.dismiss();
                }

                if(response.isSuccessful()){
                    final SweetAlertDialog success = new SweetAlertDialog(MainLogin.this  , SweetAlertDialog.SUCCESS_TYPE);
                    success.setTitleText("LOGIN SUCCESSFULL");
                    success.setContentText("Welcome to Scrap Book");
                    success.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            Intent i =new Intent(MainLogin.this,MasterAcitivty.class);
                            startActivity(i);
                            success.dismiss();
                            finish();
                        }
                    });
                    success.show();


                }
                else if(!response.isSuccessful()){
                    Toast.makeText(MainLogin.this , "Kindly Check Your Details  " , Toast.LENGTH_LONG).show();
                    final SweetAlertDialog failed = new SweetAlertDialog(MainLogin.this , SweetAlertDialog.ERROR_TYPE);
                    failed.setTitleText("FAILED !! ");
                    failed.setContentText("Kindly Check Your Details !!");
                    failed.setCancelable(false);
                    failed.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            failed.dismiss();
                        }
                    });
                    failed.show();
                }

            }

            @Override
            public void onFailure(Call<Response_Login> call, Throwable t) {

                final SweetAlertDialog error_dialogue = new SweetAlertDialog(MainLogin.this , SweetAlertDialog.ERROR_TYPE);
                error_dialogue.setTitleText("FAILED !! ");
                error_dialogue.setContentText("Kindly Check Your Details !!");
                error_dialogue.setCancelable(false);
                error_dialogue.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        error_dialogue.dismiss();
                    }
                });
                error_dialogue.show();
            }
        });


    }

    public void forget(View v){
       //to be implemented using backend
    }

    public void signed(View view){
        Intent i =new Intent(MainLogin.this,SinupActivity.class);
        startActivity(i);
    }

    //fb and g+ sign in functions
    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();

            //Displaying name and email-save name and email

          /*  name = acct.getDisplayName();
            email = acct.getEmail();
            userid = acct.getId().toString();
            //asynk class
            //new profile_asynk().execute();
            new ProfilePic();

            shared();*/

            //to the Home activity
           // Intent i=new Intent(MainLogin.this, ProfilePic.class);
           // startActivity(i);
        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();

        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                /*try {
                    if(json != null){
                        fname = json.getString("name");
                        femail = json.getString("email");
                        plink = json.getString("link");
                        id = json.getString("id");
                        share();

                        Intent i=new Intent(FbLogin.this, Prefer.class);
                        startActivity(i);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //iffbsignin
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //If signing+
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }
}
