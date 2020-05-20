package com.example.parcial2.IO.AddTrack;

import com.example.parcial2.MODEL.Consulta.TracksList;
import com.example.parcial2.MODEL.ConsultaAdd.Searching;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddTracksApiService {

    @GET("2.0/")
    Call<Searching> getSearching(@Query("method") String method, @Query("track") String track, @Query("api_key") String api_key,
                                 @Query("format") String format);
}
