package com.virtualalhambra.selectpersonajes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.virtualalhambra.R;

public class PasilloEtsiit extends AppCompatActivity {
    public static final String ID_IMAGEN_ASOCIADA = "pasillo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasillo_etsiit);
    }
}
