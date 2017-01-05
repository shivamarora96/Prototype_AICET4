package com.teamninjas.prototype_aicet4.Services.FCM;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ShivamArora on 05-01-2017.
 */
public class TokenService extends FirebaseInstanceIdService {

   public final String TAG = "fcm_abc" ;

    @Override
    public void onTokenRefresh() {

        String currentToken = FirebaseInstanceId.getInstance().getToken() ;
        Log.i(TAG , "Token  : " + currentToken + "\n" + " ID : " + FirebaseInstanceId.getInstance().getId()  ) ;

        send_Token_To_Server(currentToken) ;

    }

    private void send_Token_To_Server(String currentToken) {
        //TODO send Token TO Server ...
    }


}
