package com.example.parcial2.IO.AddTrack;

import com.example.parcial2.IO.Track.TracksApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTracksApiAdapter {

    private static AddTracksApiService API_SERVICE;

    public static AddTracksApiService getApiService(){

        //Interceptor

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Peticiones con interceptor

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = "http://ws.audioscrobbler.com/";

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(AddTracksApiService.class);
        }
        return API_SERVICE;
    }
}
