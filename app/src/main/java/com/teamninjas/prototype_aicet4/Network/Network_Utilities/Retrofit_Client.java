package com.teamninjas.prototype_aicet4.Network.Network_Utilities;

import com.teamninjas.prototype_aicet4.Others.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ShivamArora on 06-01-2017.
 */

public class Retrofit_Client {

    private static Retrofit retrofit  = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            String BASE_URL = Constants.RETROFIT_BASE_URL;
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
