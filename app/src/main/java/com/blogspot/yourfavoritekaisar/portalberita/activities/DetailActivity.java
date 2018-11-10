package com.blogspot.yourfavoritekaisar.portalberita.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.portalberita.R;
import com.blogspot.yourfavoritekaisar.portalberita.model.BeritaModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_OBJ = "obj";
    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.tvPenulis)
    TextView tvPenulis;
    @BindView(R.id.wvContentBerita)
    TextView wvContentBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BeritaModel beritaModel = getIntent().getParcelableExtra(EXTRA_OBJ);

        String judul = beritaModel.getJudul();
        String penulis = beritaModel.getPenulis();
        String IsiBerita = beritaModel.getIsiBerita();
        String gambarBerita = beritaModel.getFotoBerita();
        String tglTerbit = beritaModel.getTanggalPosting();

        getSupportActionBar().setTitle(judul);

        tvPenulis.setText(penulis);
        tvTglTerbit.setText(tglTerbit);
        wvContentBerita.setText(IsiBerita);

        Glide.with(this).load(gambarBerita).into(ivGambarBerita);


    }
}
