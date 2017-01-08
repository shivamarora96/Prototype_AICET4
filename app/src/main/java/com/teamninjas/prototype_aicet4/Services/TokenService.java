package com.teamninjas.prototype_aicet4.Services;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ShivamArora on 08-01-2017.
 */
public class TokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
       String currentToken =  FirebaseInstanceId.getInstance().getToken() ;
        Log.i("abcde" , "Token - > " + currentToken ) ;
        send_TO_SERVER(currentToken);
    }

    private void send_TO_SERVER(String currentToken) {
        //SEND IT TO SERVER
    }
}
