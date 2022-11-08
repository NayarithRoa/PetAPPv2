package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.petappv2.DB.DBPersonas;
import com.example.petappv2.DB.Personas;
import com.example.petappv2.DatosUsuario.Globales;

public class verPersonaDetalle extends AppCompatActivity {
    Button btnGuardar;
    EditText txtNombre, txtCorreo,txtCelular,txtClave, txtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona_detalle);

        //Relación de objetos con Layout
        txtNombre=findViewById(R.id.txtNombre);
        txtCorreo=findViewById(R.id.txtCorreo);
        txtCelular=findViewById(R.id.txtCelular);
        txtClave=findViewById(R.id.txtClave);
        txtCiudad=findViewById(R.id.txtCiudad);
        btnGuardar = findViewById(R.id.btnGuardar);


        final DBPersonas dbPersonas= new DBPersonas(this);
        Personas persona = dbPersonas.verPersona(Globales.getId_usuario());

        if(persona != null){
            txtNombre.setText(persona.getNombres());
            txtCorreo.setText(persona.getCorreo());
            txtCelular.setText(persona.getTelefono());
            txtClave.setText(persona.getClave());
            txtCiudad.setText(persona.getCiudad());

            txtNombre.setInputType(InputType.TYPE_NULL); //No permita que se habilite el teclado para escribir
            txtCorreo.setInputType(InputType.TYPE_NULL);
            txtCelular.setInputType(InputType.TYPE_NULL);
            txtClave.setInputType(InputType.TYPE_NULL);
            txtCiudad.setInputType(InputType.TYPE_NULL);
        }
    }
}