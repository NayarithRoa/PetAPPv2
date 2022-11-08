package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petappv2.DB.DBPersonas;

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion;
    EditText txtCorreo, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación de objetos con Layout
        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        //Iniciar sesión
        btnIniciarSesion.setOnClickListener(v->{
            try{
                //Verificar existencia persona
                DBPersonas dbPersonas = new DBPersonas(this);
                if(dbPersonas.autenticarPersonas(txtCorreo.getText().toString(),txtClave.getText().toString())){
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, verPersonaDetalle.class);
                    /*intent.putExtra("documento",txtDocumento.getText());*/
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Usuario no registrado en el sistema", Toast.LENGTH_LONG).show();
                    txtCorreo.setText("");
                    txtClave.setText("");
                }
            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}