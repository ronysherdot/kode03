package com.android.panduanpss;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

public class SejarahAdapter extends BaseAdapter {

    Context context;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    public SejarahAdapter(Context context, ArrayList<Peziarah> peziarahArrayList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.model_sejarah, viewGroup, false);
        }

        ImageView imageSejarah = view.findViewById(R.id.imageSejarah);
        JustifiedTextView txtSejarah = view.findViewById(R.id.txtSejarah);
        TextView txtJudulSejarah = view.findViewById(R.id.txtJudulSejarah);

        final Peziarah peziarah = (Peziarah) this.getItem(position);
        txtJudulSejarah.setText(peziarah.getJudul_sejarah());
        txtSejarah.setText(peziarah.getInformasi());
        PicassoClient.downloadImage(context, peziarah.getFoto_sejarah(), imageSejarah);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtSejarah.setText(Html.fromHtml(peziarah.getInformasi(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtSejarah.setText(Html.fromHtml(peziarah.getInformasi()));
        }

        return view;
    }
}
