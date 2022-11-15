package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petappv2.DB.DBPersonas;
import com.example.petappv2.DB.Personas;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion;
    EditText txtCorreo, txtClave;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación de objetos con Layout
        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        googleBtn = findViewById(R.id.google_btn);
        //Registro datos en bd
        datosDefault();
        //Login Google
        googleLogin();

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = gsc.getSignInIntent();
                startActivityForResult(signInIntent,1000);
            }
        });
        //Iniciar sesión
        btnIniciarSesion.setOnClickListener(v->{
            try{
                //Verificar existencia persona
                DBPersonas dbPersonas = new DBPersonas(this);
                if(dbPersonas.autenticarPersonas(txtCorreo.getText().toString(),txtClave.getText().toString())){
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, VerMascota.class);
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

    private void googleLogin() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }
    }

    private void datosDefault(){
        //Creacion de personas
        DBPersonas dbPersonas = new DBPersonas(this);
        //Verifica existencia de usuarios
        if(!dbPersonas.existePersonas()){
            //Crea usuarios por defecto
            Personas personas= new Personas();
            personas.setNombres("Nayarith Roa");
            personas.setTelefono("3023691520");
            personas.setCiudad("Tunja");
            personas.setCorreo("admin");
            personas.setClave("12345");
            dbPersonas.insertarPersonas(personas);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(MainActivity.this,VerMascota.class);
        startActivity(intent);
    }
}