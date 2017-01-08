package com.teamninjas.prototype_aicet4.Network.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShivamArora on 06-01-2017.
 */

public class Response_Sinup {

    @SerializedName("username")
    String userName_sinup ;
    @SerializedName("email")
    String email_sinup ;
    @SerializedName("is_teacher")
    boolean is_teacher ;
    @SerializedName("is_female")
    boolean is_female ;

    public String getUserName_sinup() {
        return userName_sinup;
    }

    public void setUserName_sinup(String userName_sinup) {
        this.userName_sinup = userName_sinup;
    }

    public String getEmail_sinup() {
        return email_sinup;
    }

    public void setEmail_sinup(String email_sinup) {
        this.email_sinup = email_sinup;
    }

    public boolean is_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(boolean is_teacher) {
        this.is_teacher = is_teacher;
    }

    public boolean is_female() {
        return is_female;
    }

    public void setIs_female(boolean is_female) {
        this.is_female = is_female;
    }
}
