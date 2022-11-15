package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class apiWhatsapp extends AppCompatActivity {
    String messagestr, phonestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_whatsapp);

        if (isWhatappInstalled()){
            messagestr ="Hola, estoy interesado en la adopción de la mascota";
            phonestr = "573227108910";
            if (!messagestr.isEmpty() && !phonestr.isEmpty()) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phonestr+
                        "&text="+messagestr));
                startActivity(i);
            }

        }else {
            Toast.makeText(apiWhatsapp.this,"Whatsapp is not installed",Toast.LENGTH_SHORT).show();
        }
    }


    //Create method appInstalledOrNot
    private boolean isWhatappInstalled(){
        PackageManager packageManager =getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }
}
