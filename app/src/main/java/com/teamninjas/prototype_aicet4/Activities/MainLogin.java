package com.teamninjas.prototype_aicet4.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.teamninjas.prototype_aicet4.R;

import org.json.JSONObject;

public class MainLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    EditText eemail,epassword;
    String semail,spassword;
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
        spassword = epassword.getText().toString();
        Toast.makeText(getApplicationContext(),"check "+semail+spassword,Toast.LENGTH_LONG).show();
        Intent i =new Intent(MainLogin.this,MasterAcitivty.class);
        startActivity(i);


        //send data to backend to catch the response-if ok then intent otherwise toast of wrong password.
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
