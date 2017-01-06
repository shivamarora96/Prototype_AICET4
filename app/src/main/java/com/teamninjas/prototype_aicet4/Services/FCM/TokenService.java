package com.teamninjas.prototype_aicet4.Services.FCM;

import android.app.Service;
import android.provider.Settings.Secure;
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
        String device_id = get_device_id();
        Log.i(TAG , "Token  : " + currentToken + "\n" + " ID : " + FirebaseInstanceId.getInstance().getId() + "\n device_id = " + device_id) ;

        send_Token_To_Server(currentToken) ;

    }

    private String get_device_id(){
      return  Secure.getString(getContentResolver() , Secure.ANDROID_ID) ;
    }

    private void send_Token_To_Server(String currentToken) {
        //TODO send Token TO Server ...
    }


}
