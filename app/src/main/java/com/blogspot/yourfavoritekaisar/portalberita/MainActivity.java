package com.blogspot.yourfavoritekaisar.portalberita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.portalberita.adapter.AdapterBerita;
import com.blogspot.yourfavoritekaisar.portalberita.network.ApiService;
import com.blogspot.yourfavoritekaisar.portalberita.network.ConfigRetrofit;
import com.blogspot.yourfavoritekaisar.portalberita.responseapi.BeritaItem;
import com.blogspot.yourfavoritekaisar.portalberita.responseapi.ResponseBerita;

import java.util.List;
import java.util.concurrent.locks.Lock;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvListBerita)
    RecyclerView rvListBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tampilBerita();
    }

    private void tampilBerita() {
     // TODO Membuat object configretrofit dan ApiService untuk dapat merequest ke API
        ConfigRetrofit config = new ConfigRetrofit();
        ApiService api = config.service;

        api.getAllBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                Log.i("Debug",response.message());
                // Cek Response Sukses
                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    // Apabila Response Status True
                    if (status) {
                        // Tampung data body ke dalam variable data semua
                        ResponseBerita dataSemua = response.body();

                        // Tampung data berita dalam variable data berita
                        List<BeritaItem> dataBerita = dataSemua.getBerita();

                        // Buat object adapter
                        AdapterBerita adapterBerita = new AdapterBerita(MainActivity.this, dataBerita);

                        // Buat Object recycleview
                        rvListBerita.setHasFixedSize(true);
                        // setting style layout recycleview menjadi linear
                        rvListBerita.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        // Masukkan adapter recycleview
                        rvListBerita.setAdapter(adapterBerita);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("debug", t.getMessage());

            }
        });

    }

}



