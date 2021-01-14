package com.bengkelonline.belon;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DetailBengkel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bengkel);

        String data_nama = getIntent().getStringExtra("data_nama_bengkel");

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getBengkelDetail(data_nama);

        TextView nama_bengkel = findViewById(R.id.nama_bengkel);
        TextView jenis_bengkel = findViewById(R.id.jenis_bengkel);
        TextView waktu_operasi = findViewById(R.id.waktu_operasi);
        TextView no_tlp = findViewById(R.id.no_tlp);
        TextView lokasi = findViewById(R.id.lokasi);

        nama_bengkel.setText(nullcheck(cursor.getString(cursor.getColumnIndexOrThrow("nama")), "nama"));
        jenis_bengkel.setText(nullcheck(cursor.getString(cursor.getColumnIndexOrThrow("jenis")), "jenis"));
        waktu_operasi.setText(nullcheck(cursor.getString(cursor.getColumnIndexOrThrow("jamoperasional")), "jam operasional"));
        no_tlp.setText(nullcheck(cursor.getString(cursor.getColumnIndexOrThrow("nohp")), "nomor telepon"));
        lokasi.setText(nullcheck(cursor.getString(cursor.getColumnIndexOrThrow("alamat")), "alamat"));

        try {
            ImageView foto_bengkel = findViewById(R.id.detail_gambar_bengkel);

            InputStream inputStream = getAssets().open("belon_gambar/"+cursor.getString(cursor.getColumnIndexOrThrow("id")) + ".jpg");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            foto_bengkel.setImageDrawable(drawable);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mDbHelper.close();

    }

    String nullcheck(String hasil_query, String kolom){
        if(hasil_query.length() < 2){
            return kolom + " tidak tersedia";
        } else
            return hasil_query;
    }
}