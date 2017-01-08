package com.teamninjas.prototype_aicet4.Others.UserObject;

/**
 * Created by ShivamArora on 08-01-2017.
 */

public class Notification_Object {

    String mTitle ;
    String description ;

    public Notification_Object(String mTitle, String description) {
        this.mTitle = mTitle;
        this.description = description;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
