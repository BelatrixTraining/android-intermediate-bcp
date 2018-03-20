package com.belatrix.kotlintemplate;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.belatrix.kotlintemplate.camera.CameraPreview;

public class CameraPermissionActivity extends AppCompatActivity {

    private static final String TAG = "CameraPermissionA";

    private static final int CAMERA_ID = 0;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_permission);


        // Open an instance of the first camera and retrieve its info.
        mCamera = getCameraInstance(CAMERA_ID);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, cameraInfo);

        if (mCamera == null) {
            // Camera is not available, display error message
            setContentView(R.layout.activity_camera_unavailable);
        } else {

            setContentView(R.layout.activity_camera);

            // Get the rotation of the screen to adjust the preview image accordingly.
            int displayRotation = getWindowManager().getDefaultDisplay().getRotation();

            // Create the Preview view and set it as the content of this Activity.
            CameraPreview cameraPreview = new CameraPreview(this, null,
                    0, mCamera, cameraInfo, displayRotation);
            FrameLayout preview = findViewById(R.id.camera_preview);
            preview.addView(cameraPreview);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera access
        releaseCamera();
    }

    private Camera getCameraInstance(int cameraId) {
        Camera c = null;
        try {
            c = Camera.open(cameraId); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
            Log.e(TAG, "Camera " + cameraId + " is not available: " + e.getMessage());
        }
        return c; // returns null if camera is unavailable
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
}
