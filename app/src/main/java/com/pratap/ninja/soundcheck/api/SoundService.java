package com.pratap.ninja.soundcheck.api;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by darsh on 6/12/17.
 */

public class SoundService {
    private SoundService() {}

    private static API api = null;

    public static API getSoundApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.soundcloud.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(API.class);
        }
        Log.d("ERR", "getSoundApi: llllllllllllllllllllllllllllllllllllll" + api);
        return api;
    }
}
