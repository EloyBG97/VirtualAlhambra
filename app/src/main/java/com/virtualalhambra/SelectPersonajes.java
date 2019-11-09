package com.virtualalhambra;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.virtualalhambra.selectpersonajes.CameraPreview;

public class SelectPersonajes extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_personajes_good);


        boolean permiso = pedirPermiso();

        // Create an instance of Camera
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            System.err.print("ERROR");
        }
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);

        if(permiso) {
            FrameLayout preview = findViewById(R.id.camera_preview);
            preview.setVisibility((int) 0);
            preview.addView(mPreview);
        }

        else {
            ImageView imageView = findViewById(R.id.imageView3);
            imageView.setVisibility((int) 0);
        }
    }

    public boolean pedirPermiso() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);



                builder.setTitle("Informacion de Permisos");
                builder.setMessage("La camara es necesaria para poder descubrir un nuevo mundo de realidad aumentada");
                builder.setCancelable(false);

                builder.setNeutralButton("Entendido", new DialogInterface.OnClickListener() {public void onClick(DialogInterface builder, int id) {

                }});

                AlertDialog dialog = builder.create();
                builder.show();


            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA},1);

            }

        }

        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }
}

