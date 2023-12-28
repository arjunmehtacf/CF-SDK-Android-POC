package com.example.cf_sdk.logic.changebanksdk.idscan;

import android.content.Intent;

/**
 *
 * Idscan abstraction.
 */

public interface Idscan {
    Intent getFrontIdCameraIntent();
    Intent getBackIdCameraIntent();
    Intent getSelfieCameraIntent();
    String getFrontImage();
    String getBackImage();
    String getSelfieImage();
    void deleteImages();
}
