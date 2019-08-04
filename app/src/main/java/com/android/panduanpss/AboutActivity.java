package com.android.panduanpss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        Intent back = new Intent(AboutActivity.this, MenuActivity.class);
        startActivity(back);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tentang Aplikasi");
        toolbar.setNavigationIcon(R.drawable.ic_person_outline_24dp);
    }
}
