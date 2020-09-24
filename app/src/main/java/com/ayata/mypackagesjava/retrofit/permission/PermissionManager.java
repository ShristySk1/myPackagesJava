package com.ayata.mypackagesjava.retrofit.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PermissionManager {

    private Context context;


    public PermissionManager(Context context){
        this.context = context;

    }

    public  boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    private  boolean shouldAskPermission(Context context, String permission){
        if (shouldAskPermission()) {
            int permissionResult = ActivityCompat.checkSelfPermission(context, permission);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }


    public void checkPermission(Context context, String permission, PermissionAskListener listener){

        if (shouldAskPermission(context, permission)){
            if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity)context,permission)) {
                listener.onPermissionPreviouslyDenied();
                Toast.makeText(context, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();

            } else {
                    listener.onNeedPermission();
            }
        } else {
            listener.onPermissionGranted();
        }
    }


    public interface PermissionAskListener {

        void onNeedPermission();

        void onPermissionPreviouslyDenied();

        void onPermissionGranted();
    }

}
