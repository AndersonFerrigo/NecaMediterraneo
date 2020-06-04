package com.pt.necadomediterraneo.errorsmessages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.pt.necadomediterraneo.R;
import com.pt.necadomediterraneo.webview.NecaMediterraneoWebView;

public class NecaMediterraneoErrorConectivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    private ImageView myImageView;
    private Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neca_mediterraneo_error_conectivity);
        // In Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        constraintLayout = findViewById(R.id.coordenatorLayoutError);
        popUpConexao();
    }

    public void popUpConexao(){
        Snackbar  snackbar =
                Snackbar.make(constraintLayout, R.string.snackbar_error_description, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new View.OnClickListener() {

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View view) {

                                Snackbar snackbar1 = Snackbar.make(constraintLayout, "Testando conexão novamente", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                                isConnected(getApplicationContext());
                            }
                        });

        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void isConnected(Context context) {

        boolean isconnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.getActiveNetwork();
        }

        //Verifica internet pela WIFI
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
            isconnected = true;
        }

        //Verifica se tem internet móvel
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
            isconnected = true;

        }
        if (isconnected == true) {
            Intent intentViewMain = new Intent(NecaMediterraneoErrorConectivity.this, NecaMediterraneoWebView.class);
            startActivity(intentViewMain);
            finish();
        } else {

            popUpConexao();
        }
    }
}
