package com.jaysontamayo.drawer.data;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class APIService {

    private final String BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/";

    public APIService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public interface MyApiEndpointInterface {

        @GET("photos")
        void getPhotos(Callback<MarsPhotoModel> cb);

    }
}
