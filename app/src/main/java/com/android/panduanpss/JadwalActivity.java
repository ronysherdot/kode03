package com.android.panduanpss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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

public class JadwalActivity extends Fragment {

    GridView gridView;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_jadwal, container, false);

        gridView = view.findViewById(R.id.gridView);
        dataJadwal();

        return view;
    }

    private void dataJadwal() {
        String urlAddress = getString(R.string.urlAddress);
        final String jadwalAddress = urlAddress + "select_hari.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, jadwalAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String info = jsonObject.getString("status");

                            JSONArray infoHari = jsonObject.getJSONArray("hari");
                            Peziarah peziarah;

                            if (info.equals("true")){
                                for (int i=0; i<infoHari.length(); i++) {
                                    JSONObject dataHari = infoHari.getJSONObject(i);

                                    int id = dataHari.getInt("id");
                                    String nama_jadwal = dataHari.getString("nama_jadwal");
                                    String foto_hari = dataHari.getString("foto");

                                    String urlAddress = getString(R.string.urlAddress);
                                    foto_hari = urlAddress+"image/"+foto_hari;

                                    peziarah = new Peziarah();
                                    peziarah.setId(id);
                                    peziarah.setNama_jadwal(nama_jadwal);
                                    peziarah.setFoto_hari(foto_hari);

                                    peziarahArrayList.add(peziarah);
                                }
                            }

                            JadwalAdapter adapter = new JadwalAdapter(getContext(), peziarahArrayList);
                            gridView.setAdapter(adapter);

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
