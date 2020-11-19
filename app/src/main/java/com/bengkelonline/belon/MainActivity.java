package com.bengkelonline.belon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View motor_layout = findViewById(R.id.motor_layout);
        View mobil_layout = findViewById(R.id.mobil_layout);
        motor_layout.setOnClickListener(this::onClick);
        mobil_layout.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.motor_layout:
                Intent motorIntent = new Intent(MainActivity.this, ListMotor.class);
                startActivity(motorIntent);
                break;
            case R.id.mobil_layout:
                Intent mobilIntent = new Intent(MainActivity.this, ListMobil.class);
                startActivity(mobilIntent);
                break;
        }
    }
}