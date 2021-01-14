package com.bengkelonline.belon;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListMobil extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private BengkelAdapter adapter;
    private ArrayList<Bengkel> bengkels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mobil);

        adapter = new BengkelAdapter(this);

        ListView listView = findViewById(R.id.tv_list_mobil);
        listView.setAdapter(adapter);

        prepare();
        addItem();
        listView.setOnItemClickListener(this::onClick);
    }

    private void onClick(AdapterView<?> adapterView, View view, int i, long l) {
        view.setOnClickListener(this::onClick);
        view.performClick();
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name_mobil);
        dataDescription = getResources().getStringArray(R.array.data_description_mobil);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo_mobil);
    }

    private void addItem() {
        bengkels = new ArrayList<>();

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getMobilList();
        do {
            Bengkel bengkel = new Bengkel();

            try {
                InputStream inputStream = getAssets().open("belon_gambar/"+cursor.getString(cursor.getColumnIndexOrThrow("id")) + ".jpg");
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                bengkel.setPhoto(drawable);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            bengkel.setName(cursor.getString(cursor.getColumnIndexOrThrow("nama")));
            bengkel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("jenis")));
            bengkels.add(bengkel);
        }while (cursor.moveToNext());
        mDbHelper.close();

//        for (int i = 0; i < dataName.length; i++) {
//            Bengkel bengkel = new Bengkel();
//            bengkel.setPhoto(dataPhoto.getResourceId(i, -1));
//            bengkel.setName(dataName[i]);
//            bengkel.setDescription(dataDescription[i]);
//            bengkels.add(bengkel);
//        }

        adapter.setBengkels(bengkels);
        for (int i = 0; i < dataName.length; i++){
            adapter.getView(1, null, null).setOnClickListener(this::onClick);
        }
    }

    private void onClick(View view) {
        if (view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.item_bengkel,
                    null, false);
        }
        TextView txtname = view.findViewById(R.id.txt_name);
        Intent detailIntent = new Intent(ListMobil.this, DetailBengkel.class).
                putExtra("data_nama_bengkel", txtname.getText());
        startActivity(detailIntent);
    }
}