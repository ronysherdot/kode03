package com.android.panduanpss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class AkomodasiDetailActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView imageAkomodasi;
    JustifiedTextView txtAlamatAkomodasi, txtHargaAkomodasi, txtJarakAkomodasi, txtDeskripsiAkomodasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akomodasi_detail);

        imageAkomodasi = findViewById(R.id.imageAkomodasi);
        txtAlamatAkomodasi = findViewById(R.id.txtAlamatAkomodasi);
        txtHargaAkomodasi = findViewById(R.id.txtHargaAkomodasi);
        txtJarakAkomodasi = findViewById(R.id.txtJarakAkomodasi);
        txtDeskripsiAkomodasi = findViewById(R.id.txtDeskripsiAkomodasi);

        Intent peziarah = getIntent();
        final int akomodasi_id = peziarah.getIntExtra("akomodasi_id", 0);
        final String nama_penginapan = peziarah.getStringExtra("nama_penginapan");
        final String alamat = peziarah.getStringExtra("alamat");
        final String harga = peziarah.getStringExtra("harga");
        final String deskripsi = peziarah.getStringExtra("deskripsi");
        final String jarak = peziarah.getStringExtra("jarak");
        final String foto_akomodasi = peziarah.getStringExtra("foto_akomodasi");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(nama_penginapan);

        txtAlamatAkomodasi.setText(alamat);
        txtHargaAkomodasi.setText(harga);
        txtJarakAkomodasi.setText(jarak);
        txtDeskripsiAkomodasi.setText(deskripsi);
        PicassoClient.downloadImage(getApplicationContext(), foto_akomodasi, imageAkomodasi);
    }
}
