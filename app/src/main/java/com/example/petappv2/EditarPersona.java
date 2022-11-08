package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petappv2.DB.DBPersonas;
import com.example.petappv2.DB.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarPersona extends AppCompatActivity {
    Button btnGuardar, btnEditar;
    EditText txtNombre, txtCorreo,txtCelular,txtClave, txtCiudad;
    FloatingActionButton fabEliminar;
    int id = 0;
    boolean correcto=false;

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
        btnEditar = findViewById(R.id.btnEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBPersonas dbPersonas= new DBPersonas(EditarPersona.this);
        Personas persona;
        persona = dbPersonas.verPersona(id);

        if(persona != null){
            txtNombre.setText(persona.getNombres());
            txtCorreo.setText(persona.getCorreo());
            txtCelular.setText(persona.getTelefono());
            txtClave.setText(persona.getClave());
            txtCiudad.setText(persona.getCiudad());
            btnEditar.setVisibility(View.INVISIBLE);
            fabEliminar.setVisibility(View.INVISIBLE);

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().isEmpty() || !txtCorreo.getText().toString().isEmpty() || !txtCelular.getText().toString().isEmpty() || !txtCiudad.getText().toString().isEmpty() || !txtClave.getText().toString().isEmpty()) {
                    correcto = dbPersonas.editPersona(id, txtNombre.getText().toString(),txtCorreo.getText().toString(),txtCelular.getText().toString(),txtClave.getText().toString(), txtCiudad.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarPersona.this, "USUARIO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarPersona.this, "ERROR AL MODIFICAR EL USUARIO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarPersona.this, "DEBE LLENAR TODOS LOS DATOS SOLICITADOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, verPersonaDetalle.class);
        startActivity(intent);
    }
    }