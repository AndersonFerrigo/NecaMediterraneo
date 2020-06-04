package com.pt.necadomediterraneo.presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.pt.necadomediterraneo.R;
import com.pt.necadomediterraneo.errorsmessages.NecaMediterraneoErrorConectivity;
import com.pt.necadomediterraneo.webview.NecaMediterraneoWebView;

public class NecaMediterraneoSplash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 1000;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neca_mediterraneo_splash);
        // In Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        view =  findViewById(R.id.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                if(isConnected(getApplicationContext())){
                    Intent intentWebView = new Intent( NecaMediterraneoSplash.this, NecaMediterraneoWebView.class);
                    startActivity(intentWebView);
                    finish();
                } else{
                    Intent intentErrorView = new Intent( NecaMediterraneoSplash.this, NecaMediterraneoErrorConectivity.class);
                    startActivity(intentErrorView);
                    finish();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
            }

        }, SPLASH_SCREEN_TIME_OUT);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.getActiveNetwork();
        }
        //Verifica internet pela WIFI
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
            return true;
        }
        //Verifica se tem internet móvel
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }

}
