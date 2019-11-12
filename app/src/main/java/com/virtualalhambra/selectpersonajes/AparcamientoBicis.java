package com.virtualalhambra.selectpersonajes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.virtualalhambra.R;

public class AparcamientoBicis extends AppCompatActivity {
    public static final String ID_IMAGEN_ASOCIADA  = "parking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparcamiento_bici);
    }
}
