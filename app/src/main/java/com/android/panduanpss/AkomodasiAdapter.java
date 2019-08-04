package com.android.panduanpss;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

public class AkomodasiAdapter extends BaseAdapter {

    Context context;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    public AkomodasiAdapter(Context context, ArrayList<Peziarah> peziarahArrayList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.model_akomodasi, viewGroup, false);
        }

        ImageView imageAkomodasi = view.findViewById(R.id.imageAkomodasi);
        JustifiedTextView txtNamaAkomodasi = view.findViewById(R.id.txtNamaAkomodasi);
        JustifiedTextView txtAlamatAkomodasi = view.findViewById(R.id.txtAlamatAkomodasi);

        final Peziarah peziarah = (Peziarah) this.getItem(position);
        txtNamaAkomodasi.setText(peziarah.getNama_penginapan());
        txtAlamatAkomodasi.setText(peziarah.getAlamat());
        PicassoClient.downloadImage(context, peziarah.getFoto_akomodasi(), imageAkomodasi);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAkomodasi(
                        peziarah.getAkomodasi_id(), peziarah.getNama_penginapan(),
                        peziarah.getAlamat(), peziarah.getHarga(), peziarah.getDeskripsi(),
                        peziarah.getJarak(), peziarah.getFoto_akomodasi()
                );
            }
        });

        return view;
    }

    private void openAkomodasi(
            int akomodasi_id,
            String nama_penginapan, String alamat, String harga,
            String deskripsi, String jarak, String foto_akomodasi
    ) {
        Intent detailAkomodasi = new Intent(context, AkomodasiDetailActivity.class);
        detailAkomodasi.putExtra("akomodasi_id", akomodasi_id);
        detailAkomodasi.putExtra("nama_penginapan", nama_penginapan);
        detailAkomodasi.putExtra("alamat", alamat);
        detailAkomodasi.putExtra("harga", harga);
        detailAkomodasi.putExtra("deskripsi", deskripsi);
        detailAkomodasi.putExtra("jarak", jarak);
        detailAkomodasi.putExtra("foto_akomodasi", foto_akomodasi);
        detailAkomodasi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(detailAkomodasi);
    }
}
