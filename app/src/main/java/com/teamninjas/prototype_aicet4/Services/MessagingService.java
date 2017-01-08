package com.teamninjas.prototype_aicet4.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.teamninjas.prototype_aicet4.R;

/**
 * Created by ShivamArora on 08-01-2017.
 */
public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String notificatioin_title =  remoteMessage.getNotification().getTitle();
        String notifiaction_body= remoteMessage.getNotification().getBody().toUpperCase();

        Log.i("abcde", "MessageRecieved > " + "Titile: " + notificatioin_title + "\n" + "message > " + notifiaction_body);

    create_Notification(notificatioin_title , notifiaction_body);

    }





    void create_Notification(String Title , String Message){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this) ;
        mBuilder.setContentTitle(Title);
        mBuilder.setContentText(Message);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources() , R.drawable.ic_notifications_none_black_24dp)) ;
        mBuilder.setSmallIcon(R.drawable.ic_notifications_none_black_24dp);
        mBuilder.setTicker(Title);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mBuilder.setPriority(1);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());



    }

}
