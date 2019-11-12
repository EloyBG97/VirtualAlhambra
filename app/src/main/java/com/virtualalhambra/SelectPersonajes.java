package com.virtualalhambra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.virtualalhambra.selectpersonajes.PasilloEtsiit;
import com.wikitude.NativeStartupConfiguration;
import com.wikitude.WikitudeSDK;
import com.wikitude.common.WikitudeError;
import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.rendering.ExternalRendering;
import com.wikitude.tracker.ImageTarget;
import com.wikitude.tracker.ImageTracker;
import com.wikitude.tracker.ImageTrackerListener;
import com.wikitude.tracker.TargetCollectionResource;

import com.virtualalhambra.selectpersonajes.AparcamientoBicis;
import com.virtualalhambra.selectpersonajes.WikitudeSDKConstants;
import com.virtualalhambra.selectpersonajes.rendering.external.CustomSurfaceView;
import com.virtualalhambra.selectpersonajes.rendering.external.Driver;
import com.virtualalhambra.selectpersonajes.rendering.external.GLRenderer;
import com.virtualalhambra.selectpersonajes.rendering.external.StrokedRectangle;

public class SelectPersonajes extends AppCompatActivity  implements ImageTrackerListener, ExternalRendering {

        private static final String TAG = "SimpleImageTracking";

        private WikitudeSDK wikitudeSDK;
        private CustomSurfaceView customSurfaceView;
        private Driver driver;
        private GLRenderer glRenderer;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            wikitudeSDK = new WikitudeSDK(this);
            NativeStartupConfiguration startupConfiguration = new NativeStartupConfiguration();
            startupConfiguration.setLicenseKey(WikitudeSDKConstants.WIKITUDE_SDK_KEY);
            startupConfiguration.setCameraPosition(CameraSettings.CameraPosition.BACK);
            startupConfiguration.setCameraResolution(CameraSettings.CameraResolution.AUTO);


            wikitudeSDK.onCreate(getApplicationContext(), this, startupConfiguration);

            final TargetCollectionResource targetCollectionResource = wikitudeSDK.getTrackerManager().createTargetCollectionResource("file:///android_asset/facultad.wtc");
            wikitudeSDK.getTrackerManager().createImageTracker(targetCollectionResource, this, null);

        }

        @Override
        protected void onResume() {
            super.onResume();
            wikitudeSDK.onResume();
            customSurfaceView.onResume();
            driver.start();
        }

        @Override
        protected void onPause() {
            super.onPause();
            customSurfaceView.onPause();
            driver.stop();
            wikitudeSDK.onPause();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            wikitudeSDK.onDestroy();
        }

        @Override
        public void onRenderExtensionCreated(final RenderExtension renderExtension) {
            glRenderer = new GLRenderer(renderExtension);
            wikitudeSDK.getCameraManager().setRenderingCorrectedFovChangedListener(glRenderer);
            customSurfaceView = new CustomSurfaceView(getApplicationContext(), glRenderer);
            driver = new Driver(customSurfaceView, 30);
            setContentView(customSurfaceView);
        }

        @Override
        public void onTargetsLoaded(ImageTracker tracker) {
            Log.v(TAG, "Image tracker loaded");
        }

        @Override
        public void onErrorLoadingTargets(ImageTracker tracker, WikitudeError error) {
            Log.v(TAG, "Unable to load image tracker. Reason: " + error.getMessage());
        }

        @Override
        public void onImageRecognized(ImageTracker tracker, final ImageTarget target) {
            Log.v(TAG, "Recognized target " + target.getName());

            StrokedRectangle strokedRectangle = new StrokedRectangle(StrokedRectangle.Type.STANDARD);
            glRenderer.setRenderablesForKey(target.getName() + target.getUniqueId(), strokedRectangle, null);

            Intent intent;
            switch (target.getName()) {
                case AparcamientoBicis.ID_IMAGEN_ASOCIADA:
                    intent = new Intent(this, AparcamientoBicis.class);
                    startActivity(intent);
                    break;

                case PasilloEtsiit.ID_IMAGEN_ASOCIADA:
                    intent = new Intent(this, PasilloEtsiit.class);
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onImageTracked(ImageTracker tracker, final ImageTarget target) {
            StrokedRectangle strokedRectangle = (StrokedRectangle) glRenderer.getRenderableForKey(target.getName() + target.getUniqueId());

            if (strokedRectangle != null) {
                strokedRectangle.viewMatrix = target.getViewMatrix();

                strokedRectangle.setXScale(target.getTargetScale().x);
                strokedRectangle.setYScale(target.getTargetScale().y);
            }
        }

        @Override
        public void onImageLost(ImageTracker tracker, final ImageTarget target) {
            Log.v(TAG, "Lost target " + target.getName());
            glRenderer.removeRenderablesForKey(target.getName() + target.getUniqueId());
        }

        @Override
        public void onExtendedTrackingQualityChanged(ImageTracker tracker, final ImageTarget target, final int oldTrackingQuality, final int newTrackingQuality) {

        }
    }
