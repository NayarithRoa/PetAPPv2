package com.example.petappv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petappv2.DB.DBPersonas;
import com.example.petappv2.DB.Personas;
import com.example.petappv2.DatosUsuario.Globales;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class verPersonaDetalle extends AppCompatActivity {
    Button btnGuardar, btnEditar;
    EditText txtNombre, txtCorreo,txtCelular,txtClave, txtCiudad;
    FloatingActionButton fabEditar, fabEliminar;

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
        btnEditar = findViewById(R.id.btnEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
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

            btnGuardar.setVisibility(View.INVISIBLE);
        }

        btnEditar.setOnClickListener(v->  {

                Intent intent = new Intent(verPersonaDetalle.this, EditarPersona.class);
                intent.putExtra("ID", Globales.getId_usuario());
                startActivity(intent);
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(verPersonaDetalle.this);
                builder.setMessage("¿Desea eliminar esta cuenta?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbPersonas.eliminarUsuario(Globales.getId_usuario())){
                                    Toast.makeText(verPersonaDetalle.this, "CUENTA ELIMINADA", Toast.LENGTH_SHORT).show();
                                    CerrarSesion();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void CerrarSesion(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}