package com.teamninjas.prototype_aicet4.Network.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShivamArora on 08-01-2017.
 */

public class Login_Request {

    @SerializedName("username")
    private String username ;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public Login_Request(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

