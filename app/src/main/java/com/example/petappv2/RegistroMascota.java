package com.example.petappv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petappv2.DB.DBMascotas;
import com.example.petappv2.DB.Mascotas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class RegistroMascota extends AppCompatActivity {
    Button btnGuardar;
    EditText txtNombre, txtTipo,txtSexo,txtTamanio, txtRaza, txtEdad,txtUbicacion,txtImagen,txtEstado,txtDescripcion;
    ImageView imageview;
    FloatingActionButton btnselectImage;
    DateFormat df= new SimpleDateFormat("dd/MM/yy");
    Date fecha=new Date();
    Long resultado;
    Uri selectImageUri;

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
        imageview=findViewById(R.id.sr_image);
        txtEstado=findViewById(R.id.txtEstado);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar1);
        btnselectImage = findViewById(R.id.selectImage);

        btnGuardar.setOnClickListener(v-> {
            if (txtNombre.getText().toString().isEmpty() || txtTipo.getText().toString().isEmpty()
                    || txtSexo.getText().toString().isEmpty() || txtTamanio.getText().toString().isEmpty()
                    || txtRaza.getText().toString().isEmpty() || txtEdad.getText().toString().isEmpty()
                    || txtUbicacion.getText().toString().isEmpty()
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
        btnselectImage.setOnClickListener(v-> {
            if(hasStoragePermission(RegistroMascota.this)){
                openImageChooser();
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
            }
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selecciona imagen"),100);
    }

    private boolean hasStoragePermission(Context context) {
        int read= ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        return read == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==100){
                selectImageUri=data.getData();
                if(selectImageUri != null){
                        imageview.setImageURI(selectImageUri);

                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
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
        mascotas.setEstado(txtEstado.getText().toString());
        mascotas.setDescripcion(txtDescripcion.getText().toString());
        mascotas.setId_Persona(1); //CAMBIAR
        mascotas.setFecha(df.format(fecha));
        //Imagen
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectImageUri);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , byteArray);
            byte[] img = byteArray.toByteArray();
            mascotas.setImagen(img);
        } catch (IOException e) {
            e.printStackTrace();
        }


        DBMascotas dbMascotas = new DBMascotas(this);
        resultado = dbMascotas.insertarMascotas(mascotas);
    }
}