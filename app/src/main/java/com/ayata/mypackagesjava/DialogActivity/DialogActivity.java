package com.ayata.mypackagesjava.DialogActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ayata.mypackagesjava.DialogActivity.Utils.Utils;
import com.ayata.mypackagesjava.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener, Utils.dialogbtnListener {
    private Button opendialog, openCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //init views
        opendialog = findViewById(R.id.btn_open_dialog);
        openCustomDialog = findViewById(R.id.btn_open_custom_dialog);

        //set listener
        opendialog.setOnClickListener(this);
        openCustomDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_dialog:
                opendialog();
                break;
            case R.id.btn_open_custom_dialog:
                openCustomDialog();
                break;

        }
    }

    void opendialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are your sure?");
        builder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(DialogActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void openCustomDialog() {
        Utils.createAlertDialog(this, R.layout.layout_dialog, this);

//        final Dialog dialog = new Dialog(DialogActivity.this);
//        // hide to default title for Dialog
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        // inflate the layout dialog_layout.xml and set it
//        // as contentView
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.layout_dialog, null, false);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setContentView(view);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        dialog.show();


    }

    @Override
    public void okclick() {
        Toast.makeText(this, "clicked ok", Toast.LENGTH_LONG).show();

    }

    @Override
    public void cancelClick() {
        Toast.makeText(this, "clicked cancel", Toast.LENGTH_LONG).show();

    }
}
