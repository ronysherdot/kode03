package com.android.panduanpss;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class JadwalAdapter extends BaseAdapter {

    Context context;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    public JadwalAdapter(Context context, ArrayList<Peziarah> peziarahArrayList) {
        this.context = context;
        this.peziarahArrayList = peziarahArrayList;
    }

    @Override
    public int getCount() {
        return peziarahArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return peziarahArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.model_jadwal, viewGroup, false);
        }

        ImageView imageHari = view.findViewById(R.id.imageHari);
        TextView txtHari = view.findViewById(R.id.txtHari);

        final Peziarah peziarah = (Peziarah) this.getItem(position);
        txtHari.setText(peziarah.getNama_jadwal());
        PicassoClient.downloadImage(context, peziarah.getFoto_hari(), imageHari);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJadwal(peziarah.getId());
            }
        });

        return view;
    }

    private void openJadwal(int hari_raya_id) {
        Intent detailJadwal = new Intent(context, JadwalDetailActivity.class);
        detailJadwal.putExtra("hari_raya_id", hari_raya_id);
        detailJadwal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(detailJadwal);
    }
}
