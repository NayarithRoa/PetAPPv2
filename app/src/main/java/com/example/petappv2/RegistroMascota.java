package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petappv2.DB.DBMascotas;
import com.example.petappv2.DB.Mascotas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroMascota extends AppCompatActivity {
    Button btnGuardar;
    EditText txtNombre, txtTipo,txtSexo,txtTamanio, txtRaza, txtEdad,txtUbicacion,txtImagen,txtEstado,txtDescripcion;
    DateFormat df= new SimpleDateFormat("dd/MM/yy");
    Date fecha=new Date();
    Long resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        //Relación de objetos con Layout
        txtNombre=findViewById(R.id.txtNombree);
        txtTipo=findViewById(R.id.txtTipo);
        txtSexo=findViewById(R.id.txtSexo);
        txtTamanio=findViewById(R.id.txtTamanio);
        txtRaza=findViewById(R.id.txtRaza);
        txtEdad=findViewById(R.id.txtEdad);
        txtUbicacion=findViewById(R.id.txtUbicacion);
        txtImagen=findViewById(R.id.txtImagen);
        txtEstado=findViewById(R.id.txtEstado);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar1);

        btnGuardar.setOnClickListener(v-> {
            if (txtNombre.getText().toString().isEmpty() || txtTipo.getText().toString().isEmpty()
                    || txtSexo.getText().toString().isEmpty() || txtTamanio.getText().toString().isEmpty()
                    || txtRaza.getText().toString().isEmpty() || txtEdad.getText().toString().isEmpty()
                    || txtUbicacion.getText().toString().isEmpty() || txtImagen.getText().toString().isEmpty()
                    || txtEstado.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty()
            ){
                Toast.makeText(this,"Digite todos los datos solicitados",Toast.LENGTH_LONG).show();
            }else{
                insertar();
                if(resultado>0){
                    //temporal
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }

            }

        });
    }
    private void insertar() {
        Mascotas mascotas = new Mascotas();
        mascotas.setNombre(txtNombre.getText().toString());
        mascotas.setTipo(txtTipo.getText().toString());
        mascotas.setSexo(txtSexo.getText().toString());
        mascotas.setTamanio(txtTamanio.getText().toString());
        mascotas.setRaza(txtRaza.getText().toString());
        mascotas.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        mascotas.setUbicacion(txtUbicacion.getText().toString());
        mascotas.setImagen(txtImagen.getText().toString());
        mascotas.setEstado(txtEstado.getText().toString());
        mascotas.setDescripcion(txtDescripcion.getText().toString());
        mascotas.setId_Persona(1); //CAMBIAR
        mascotas.setFecha(df.format(fecha));


        DBMascotas dbMascotas = new DBMascotas(this);
        resultado = dbMascotas.insertarMascotas(mascotas);
    }
}