package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user taps the Send button */
    public void selectPersonaje(View view) {
        Intent intent = new Intent(this, SelectPersonajes.class);
        startActivity(intent);
    }


    /** Called when the user taps the Send button */
    public void tocaCajaFuerte (View view) {
        // Do something in response to button
        Intent intent = new Intent(this, CajaFuerte.class);
        startActivity(intent);
    }
}
