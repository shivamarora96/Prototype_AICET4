package com.teamninjas.prototype_aicet4;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    Toolbar mToolbar ;
    Button mRegister_button , mLogin_Button ;
    AlertDialog loginDialogue ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.Main_toolbar) ;
        mToolbar.setTitle(Constants.ACTIONBAR_TITLE);
        setSupportActionBar(mToolbar);

        mRegister_button = (Button)findViewById(R.id.Main_RegisterButton) ;
        mLogin_Button = (Button)findViewById(R.id.Main_Sinin_button) ;

        mRegister_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSinup  =  new Intent(Main.this , SinupActivity.class);
                startActivity(toSinup);
            }
        });

        mLogin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_Login_Dialogue();

            }
        });


    }

    private void set_Login_Dialogue() {
        loginDialogue = new AlertDialog.Builder(Main.this)
                                   .setIcon(R.drawable.ic_person)
                                   .setTitle("LOGIN : ")
                                   .create() ;

        View v = LayoutInflater.from(Main.this).inflate(R.layout.custom_login_view , null) ;
        loginDialogue.setView(v);
        loginDialogue.setButton(AlertDialog.BUTTON_POSITIVE, " LOGIN ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        loginDialogue.setButton(AlertDialog.BUTTON_NEGATIVE, " CANCEL ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        loginDialogue.show();
    }

}