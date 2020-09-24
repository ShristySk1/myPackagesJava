package com.ayata.mypackagesjava.DialogActivity.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ayata.mypackagesjava.DialogActivity.DialogActivity;
import com.ayata.mypackagesjava.R;

import androidx.appcompat.app.AlertDialog;

public class Utils {
    public static void createAlertDialog(Context context,int layout,dialogbtnListener listener){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(context).inflate(layout, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        //get views for dialog
        Button ok = view.findViewById(R.id.btn_ok);
        Button cancel = view.findViewById(R.id.btn_cancel);

        //create dialog
        AlertDialog dialog = builder.create();
        dialog.show();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        //click listener on dialog buttons
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.okclick();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancelClick();
            }
        });
    }
    public interface dialogbtnListener{
        void okclick();
        void cancelClick();
    }
}
