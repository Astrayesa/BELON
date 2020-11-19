package com.bengkelonline.belon;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListMotor extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private BengkelAdapter adapter;
    private ArrayList<Bengkel> bengkels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_motor);

        adapter = new BengkelAdapter(this);

        ListView listView = findViewById(R.id.tv_list_motor);
        listView.setAdapter(adapter);

        prepare();
        addItem();
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name_motor);
        dataDescription = getResources().getStringArray(R.array.data_description_motor);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo_motor);
    }

    private void addItem() {
        bengkels = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Bengkel bengkel = new Bengkel();
            bengkel.setPhoto(dataPhoto.getResourceId(i, -1));
            bengkel.setName(dataName[i]);
            bengkel.setDescription(dataDescription[i]);
            bengkels.add(bengkel);
        }

        adapter.setBengkels(bengkels);
    }
}