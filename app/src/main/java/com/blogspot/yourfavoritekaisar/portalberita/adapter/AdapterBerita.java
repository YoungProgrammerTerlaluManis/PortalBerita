package com.blogspot.yourfavoritekaisar.portalberita.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.portalberita.R;
import com.blogspot.yourfavoritekaisar.portalberita.activities.DetailActivity;
import com.blogspot.yourfavoritekaisar.portalberita.model.BeritaModel;
import com.blogspot.yourfavoritekaisar.portalberita.network.ConfigRetrofit;
import com.blogspot.yourfavoritekaisar.portalberita.responseapi.BeritaItem;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {
    Context context;
    List<BeritaItem> BeritaAja;


    public AdapterBerita(Context contextl, List<BeritaItem> beritaAja) {
        this.context = contextl;
        BeritaAja = beritaAja;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, viewGroup, false);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.ivJudulBerita.setText(BeritaAja.get(i).getJudulBerita());
        viewHolder.tvPenulis.setText(BeritaAja.get(i).getPenulis());
        viewHolder.tvTglTerbit.setText(BeritaAja.get(i).getTanggalPosting());

        final String urlGambarBerita = ConfigRetrofit.API_URL + "images/" + BeritaAja.get(i).getFoto();

        Glide.with(context).load(urlGambarBerita).into(viewHolder.ivGambarBerita);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context, DetailActivity.class);

                BeritaModel beritaModel = new BeritaModel();

                beritaModel.setJudul(BeritaAja.get(i).getJudulBerita());
                beritaModel.setFotoBerita(urlGambarBerita);
                beritaModel.setIsiBerita(BeritaAja.get(i).getIsiBerita());
                beritaModel.setPenulis(BeritaAja.get(i).getPenulis());
                beritaModel.setTanggalPosting(BeritaAja.get(i).getTanggalPosting());

                pindah.putExtra(DetailActivity.EXTRA_OBJ, beritaModel);

                context.startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {return BeritaAja.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivGambarBerita)
        ImageView ivGambarBerita;
        @BindView(R.id.ivJudulBerita)
        TextView ivJudulBerita;
        @BindView(R.id.tvTglTerbit)
        TextView tvTglTerbit;
        @BindView(R.id.tvPenulis)
        TextView tvPenulis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
