package com.android.panduanpss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class JadwalDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jadwal Kegiatan");

        Intent peziarah = getIntent();
        final int hari_raya_id = peziarah.getIntExtra("hari_raya_id", 0);

        listView = findViewById(R.id.listView);
        dataJadwal();
    }

    private void dataJadwal() {
        Intent peziarah = getIntent();
        final int hari_raya_id = peziarah.getIntExtra("hari_raya_id", 0);

        String urlAddress = getString(R.string.urlAddress);
        final String kecakAddress = urlAddress + "select_jadwal.php?hari_raya_id=" + hari_raya_id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, kecakAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String info = jsonObject.getString("status");

                            JSONArray infoJadwal = jsonObject.getJSONArray("jadwal");
                            Peziarah peziarah;

                            if (info.equals("true")){
                                for (int i=0; i<infoJadwal.length(); i++) {
                                    JSONObject dataJadwal = infoJadwal.getJSONObject(i);

                                    int jadwal_id = dataJadwal.getInt("id");
                                    int hari_raya_id = dataJadwal.getInt("hari_raya_id");
                                    String nama_upacara = dataJadwal.getString("nama_upacara");
                                    String waktu = dataJadwal.getString("waktu");
                                    String keterangan = dataJadwal.getString("keterangan");
                                    String lokasi = dataJadwal.getString("lokasi");
                                    String foto_jadwal = dataJadwal.getString("foto");

                                    String urlAddress = getString(R.string.urlAddress);
                                    foto_jadwal = urlAddress+"image/"+foto_jadwal;

                                    peziarah = new Peziarah();
                                    peziarah.setJadwal_id(jadwal_id);
                                    peziarah.setHari_raya_id(hari_raya_id);
                                    peziarah.setNama_upacara(nama_upacara);
                                    peziarah.setWaktu(waktu);
                                    peziarah.setKeterangan(keterangan);
                                    peziarah.setLokasi(lokasi);
                                    peziarah.setFoto_jadwal(foto_jadwal);

                                    peziarahArrayList.add(peziarah);
                                }
                            }

                            JadwalLengkapAdapter adapter = new JadwalLengkapAdapter(getApplicationContext(), peziarahArrayList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
