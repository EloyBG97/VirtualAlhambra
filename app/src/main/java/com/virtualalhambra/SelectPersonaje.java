package com.virtualalhambra;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SelectPersonaje extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_personaje);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
    }

}
