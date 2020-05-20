package com.example.parcial2.IO.Track;

import com.example.parcial2.MODEL.Consulta.TracksList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TracksApiService {

    @GET("?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<TracksList> getTracks();

}
