package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petappv2.DB.DBPersonas;
import com.example.petappv2.DB.Personas;

public class RegistroPersona extends AppCompatActivity {
    Button btnGuardar;
    EditText txtNombre, txtCorreo,txtCelular,txtClave, txtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);

        //Relación de objetos con Layout
        txtNombre=findViewById(R.id.txtNombre);
        txtCorreo=findViewById(R.id.txtCorreo);
        txtCelular=findViewById(R.id.txtCelular);
        txtClave=findViewById(R.id.txtClave);
        txtCiudad=findViewById(R.id.txtCiudad);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v-> {
            if (txtNombre.getText().toString().isEmpty() || txtCorreo.getText().toString().isEmpty() || txtCelular.getText().toString().isEmpty() || txtCiudad.getText().toString().isEmpty() || txtClave.getText().toString().isEmpty()){
                Toast.makeText(this,"Digite todos los datos solicitados",Toast.LENGTH_LONG).show();
            }else{
            insertar();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private void insertar() {
        Personas personas = new Personas();
        personas.setNombres(txtNombre.getText().toString());
        personas.setTelefono(txtCelular.getText().toString());
        personas.setCorreo(txtCorreo.getText().toString());
        personas.setClave(txtClave.getText().toString());
        personas.setCiudad(txtCiudad.getText().toString());

        DBPersonas dbPersonas = new DBPersonas(this);
        Long resultado = dbPersonas.insertarPersonas(personas);
    }
}