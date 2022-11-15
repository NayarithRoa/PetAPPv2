package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.petappv2.DB.DBMascotas;
import com.example.petappv2.DB.Mascotas;

public class DetalleMascota extends AppCompatActivity {
    Button btnMensaje;
    EditText txtNombre, txtTipo,txtSexo,txtTamanio, txtRaza, txtEdad,txtUbicacion,txtDescripcion;
    ImageView imageview;
    int id = 0;
    Mascotas mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        //Relación de objetos con Layout
        txtNombre=findViewById(R.id.txtNombree);
        txtTipo=findViewById(R.id.txtTipo);
        txtSexo=findViewById(R.id.txtSexo);
        txtTamanio=findViewById(R.id.txtTamanio);
        txtRaza=findViewById(R.id.txtRaza);
        txtEdad=findViewById(R.id.txtEdad);
        txtUbicacion=findViewById(R.id.txtUbicacion);
        imageview=findViewById(R.id.sr_image);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        btnMensaje = findViewById(R.id.btnGuardar1);

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

        final DBMascotas dbMascotas= new DBMascotas(DetalleMascota.this);
        mascota = dbMascotas.verMascota(id);

        if(mascota != null){
            mascota.setId_Mascota(mascota.getId_Mascota());
            mascota.setTipo(mascota.getTipo());
            mascota.setNombre(mascota.getNombre());
            mascota.setSexo(mascota.getSexo());
            mascota.setTamanio(mascota.getTamanio());
            mascota.setRaza(mascota.getRaza());
            mascota.setEdad(mascota.getEdad());
            mascota.setUbicacion(mascota.getUbicacion());
            //No permita que se habilite el teclado para escribir
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTipo.setInputType(InputType.TYPE_NULL);
            txtSexo.setInputType(InputType.TYPE_NULL);
            txtTamanio.setInputType(InputType.TYPE_NULL);
            txtRaza.setInputType(InputType.TYPE_NULL);
            txtEdad.setInputType(InputType.TYPE_NULL);
            txtUbicacion.setInputType(InputType.TYPE_NULL);
            txtDescripcion.setInputType(InputType.TYPE_NULL);

        }
    }
}