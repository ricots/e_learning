package com.androidcodefinder.dashboarddesign;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidcodefinder.dashboarddesign.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    LinearLayout mat,akun,keluar,soal_materi;
    SharedPrefManager sharedPrefManager;
    String user_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);
        user_login = sharedPrefManager.getSP_nis();
        Toast.makeText(MainActivity.this, sharedPrefManager.getSP_nis(), Toast.LENGTH_LONG).show();

        mat = (LinearLayout) findViewById(R.id.mat);
        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),materi_siswa.class);
                startActivity(in);
            }
        });

        keluar = (LinearLayout) findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("apakah anda yakin ingin keluar ?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                startActivity(new Intent(MainActivity.this, login_siswa.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
