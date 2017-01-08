package com.teamninjas.prototype_aicet4.Network.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShivamArora on 07-01-2017.
 */

public class Sinup_Request {
    @SerializedName("email")
    String email ;
    @SerializedName("username")
    String username ;
    @SerializedName("password")
    String password ;
    @SerializedName("is_teacher")
    boolean is_teacher ;
    @SerializedName("is_female")
    boolean is_female ;

    public Sinup_Request(String email, String username, String password, boolean is_teacher, boolean is_female) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.is_teacher = is_teacher;
        this.is_female = is_female;
    }
}
