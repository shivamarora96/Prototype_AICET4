package com.teamninjas.prototype_aicet4.Others;

/**
 * Created by ShivamArora on 27-12-2016.
 */

public class Constants {

    //SINUP ACTIVITY ...........
    public static final String ACTIONBAR_TITLE = " STUDENT PORTAL " ;
    public static final String Sinup_SEX_MALE = "male";
    public static final String Sinup_SEX_FEMALE = "female";
    public static final String Sinup_SEX_OTHERS = "others";
    public static final int Sinup_MIN_PASSWORD_LENGTH = 8 ;
    public static final String SINUP_YES_FACULITY = "yes";
    public static final String SINUP_NO_FACULITY = "nope";

    //NETWORKING ...................
    public static final String RETROFIT_BASE_URL = "http://139.59.26.211/" ;
    public static final String RETROFIT_PARTIAL_LOGIN_URL = "api/user/login/" ;
    public static final String RETROFIT_PARTIAL_SINUP_URL = "api/user/register/" ;

    //SHARED PREFERENCE ..............
    public static final String SHARED_PREF_COLLEGE_NAME_KEY = "coll_name";

}


//Basics Networking ...................
/* Base-URL = http://139.59.43.9
# Get auth token
        url  : ip/api-token-auth/
        Post
        data = {
        "username" : "shubham",
        "password" : "test1234"
        }

        it will give you "token" save it for all request


        # Login

        url : ip/api/uses/login
        POst
        data = {
        "username" or "email",
        "password"
        }
        No need of token
        it will give you like
        {
        "email": "shubham.aggarwal2020@gmail.com",
        "username": "shubham",
        "status": "Login Successfully"
        }

        # Signup

        url : ip/api/user/register
        No need of token
        Post
        {
        "username" : "anss2hxdul",
        "email" : "anshul@gmail.com",
        "password" : "test1234",
        "is_teacher" : 0,
        "is_female" : 0
        }*/
