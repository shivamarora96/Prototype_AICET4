package com.teamninjas.prototype_aicet4.Network.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShivamArora on 06-01-2017.
 */

public class Response_Login {

    @SerializedName("email")
    private String email_login ;

    @SerializedName("username")
    private String username_login ;

    @SerializedName("status")
    private String status_login ;


    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public String getUsername_login() {
        return username_login;
    }

    public void setUsername_login(String username_login) {
        this.username_login = username_login;
    }

    public String getStatus_login() {
        return status_login;
    }

    public void setStatus_login(String status_login) {
        this.status_login = status_login;
    }
}
