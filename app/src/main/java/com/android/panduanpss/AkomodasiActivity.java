package com.android.panduanpss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AkomodasiActivity extends Fragment {

    ListView listView;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_akomodasi, container, false);

        listView = view.findViewById(R.id.listView);
        dataAkomodasi();
        
        return view;
    }

    private void dataAkomodasi() {
        String urlAddress = getString(R.string.urlAddress);
        final String kecakAddress = urlAddress + "select_akomodasi.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, kecakAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String info = jsonObject.getString("status");

                            JSONArray infoAkomodasi = jsonObject.getJSONArray("akomodasi");
                            Peziarah peziarah;

                            if (info.equals("true")){
                                for (int i=0; i<infoAkomodasi.length(); i++) {
                                    JSONObject dataAkomodasi = infoAkomodasi.getJSONObject(i);

                                    int akomodasi_id = dataAkomodasi.getInt("id");
                                    String nama_penginapan = dataAkomodasi.getString("nama_penginapan");
                                    String alamat = dataAkomodasi.getString("alamat");
                                    String harga = dataAkomodasi.getString("harga");
                                    String deskripsi = dataAkomodasi.getString("keterangan");
                                    String jarak = dataAkomodasi.getString("jarak");
                                    String foto_akomodasi = dataAkomodasi.getString("foto");

                                    String urlAddress = getString(R.string.urlAddress);
                                    foto_akomodasi = urlAddress+"image/"+foto_akomodasi;

                                    peziarah = new Peziarah();
                                    peziarah.setAkomodasi_id(akomodasi_id);
                                    peziarah.setNama_penginapan(nama_penginapan);
                                    peziarah.setAlamat(alamat);
                                    peziarah.setHarga(harga);
                                    peziarah.setDeskripsi(deskripsi);
                                    peziarah.setJarak(jarak);
                                    peziarah.setFoto_akomodasi(foto_akomodasi);

                                    peziarahArrayList.add(peziarah);
                                }
                            }

                            AkomodasiAdapter adapter = new AkomodasiAdapter(getContext(), peziarahArrayList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()).getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
