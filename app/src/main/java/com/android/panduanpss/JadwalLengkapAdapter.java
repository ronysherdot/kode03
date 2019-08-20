package com.android.panduanpss;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

public class JadwalLengkapAdapter extends BaseAdapter {

    Context context;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    public JadwalLengkapAdapter(Context context, ArrayList<Peziarah> peziarahArrayList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.model_detail, viewGroup, false);
        }

        ImageView imageUpacara = view.findViewById(R.id.imageUpacara);
        JustifiedTextView txtNamaUpacara = view.findViewById(R.id.txtNamaUpacara);
        JustifiedTextView txtWaktuUpacara = view.findViewById(R.id.txtWaktuUpacara);
        JustifiedTextView txtKeteranganUpacara = view.findViewById(R.id.txtKeteranganUpacara);
        JustifiedTextView txtLokasiUpacara = view.findViewById(R.id.txtLokasiUpacara);

        final Peziarah peziarah = (Peziarah) this.getItem(position);
        PicassoClient.downloadImage(context, peziarah.getFoto_jadwal(), imageUpacara);
        txtNamaUpacara.setText(peziarah.getNama_upacara());
        txtWaktuUpacara.setText(peziarah.getWaktu());
        txtKeteranganUpacara.setText(peziarah.getKeterangan());
        txtLokasiUpacara.setText(peziarah.getLokasi());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtKeteranganUpacara.setText(Html.fromHtml(peziarah.getKeterangan(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtKeteranganUpacara.setText(Html.fromHtml(peziarah.getKeterangan()));
        }


        return view;
    }
}
