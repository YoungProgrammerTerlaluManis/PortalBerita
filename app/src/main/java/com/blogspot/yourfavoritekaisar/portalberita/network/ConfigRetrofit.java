package com.blogspot.yourfavoritekaisar.portalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    public static final String API_URL = "http://192.168.56.1/portal_berita/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   public  ApiService service = retrofit.create(ApiService.class);
}
