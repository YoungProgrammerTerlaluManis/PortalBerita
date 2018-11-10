package com.blogspot.yourfavoritekaisar.portalberita.network;

import com.blogspot.yourfavoritekaisar.portalberita.responseapi.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //TODO Menrequest data dan mengambil data berita
    @GET("tampil_berita.php")
    Call<ResponseBerita> getAllBerita();
}
