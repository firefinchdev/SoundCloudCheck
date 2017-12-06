package com.pratap.ninja.soundcheck.api;


import com.pratap.ninja.soundcheck.models.Sound;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by darsh on 6/12/17.
 */

public interface API {

    @GET("tracks?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J")
    Call<ArrayList<Sound>> getTracks();

    @GET("tracks/{trackid}?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J")
    Call<ArrayList<Sound>> getTracksFromId(@Path("trackid") String trackId);

    @GET("tracks?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J&q={searchstr}")
    Call<ArrayList<Sound>> getSearchResults(@Path("searchstr") String searchString);

    @GET("tracks?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J")
    Call<ArrayList<Sound>> getSearchResults2(@Query("q") String searchString);

    /*@GET("{latitude},{longitude}?units=si")
    Call<WeatherPOJO> getCityWeather(@Path("latitude") String latitude,@Path("longitude") String longitude);*/
}
