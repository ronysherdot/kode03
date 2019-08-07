package com.android.panduanpss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class SejarahActivity extends Fragment {

    ListView listView;
    ArrayList<Peziarah> peziarahArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sejarah, container, false);

        listView = view.findViewById(R.id.listView);
        dataSejarah();

        return view;
    }

    private void dataSejarah() {
        String urlAddress = getString(R.string.urlAddress);
        final String kecakAddress = urlAddress + "select.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, kecakAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String info = jsonObject.getString("status");

                            JSONArray infoSejarah = jsonObject.getJSONArray("sejarah");
                            Peziarah peziarah;

                            if (info.equals("true")){
                                for (int i=0; i<infoSejarah.length(); i++) {
                                    JSONObject dataSejarah = infoSejarah.getJSONObject(i);

                                    int sejarah_id = dataSejarah.getInt("id");
                                    String judul_sejarah = dataSejarah.getString("judul_sejarah");
                                    String informasi = dataSejarah.getString("keterangan");
                                    String foto_sejarah = dataSejarah.getString("foto");

                                    String urlAddress = getString(R.string.urlAddress);
                                    foto_sejarah = urlAddress+"image/"+foto_sejarah;

                                    peziarah = new Peziarah();
                                    peziarah.setSejarah_id(sejarah_id);
                                    peziarah.setJudul_sejarah(judul_sejarah);
                                    peziarah.setInformasi(informasi);
                                    peziarah.setFoto_sejarah(foto_sejarah);

                                    peziarahArrayList.add(peziarah);
                                }
                            }

                            SejarahAdapter adapter = new SejarahAdapter(getContext(), peziarahArrayList);
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
