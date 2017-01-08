package com.teamninjas.prototype_aicet4.Network.Network_Utilities;

import com.teamninjas.prototype_aicet4.Network.Request.Login_Request;
import com.teamninjas.prototype_aicet4.Network.Request.Sinup_Request;
import com.teamninjas.prototype_aicet4.Network.Response.Response_Login;
import com.teamninjas.prototype_aicet4.Network.Response.Response_Sinup;
import com.teamninjas.prototype_aicet4.Others.Constants;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ShivamArora on 06-01-2017.
 */

public interface Retrofit_Interface {

    //Calling Login Response Call .......................................

    @POST(Constants.RETROFIT_PARTIAL_LOGIN_URL)
    Call<Response_Login> call_LoginPost_with_body(@Body Login_Request login_request) ;

    @POST(Constants.RETROFIT_PARTIAL_SINUP_URL)
    Call<Response_Sinup> call_sinup_body(@Body Sinup_Request sinup_request) ;


}
