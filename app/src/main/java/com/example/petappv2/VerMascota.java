package com.example.petappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.petappv2.Adapters.ListaMascotasAdapter;
import com.example.petappv2.DB.DBMascotas;
import com.example.petappv2.DB.Mascotas;

import java.util.ArrayList;

public class VerMascota extends AppCompatActivity {
    RecyclerView recyclerViewCategotyList, listaMascotas;
    ListaMascotasAdapter adapter;
    ArrayList<Mascotas> listaArrayMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mascota);

        recyclerVistaMascotas();
    }

    private void recyclerVistaMascotas() {
        listaMascotas = findViewById(R.id.view2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        listaMascotas.setLayoutManager(linearLayoutManager);

        DBMascotas dbMascotas = new DBMascotas(VerMascota.this);

        listaArrayMascotas= new ArrayList<>();
        adapter= new ListaMascotasAdapter(dbMascotas.mostrarMascotas());
        listaMascotas.setAdapter(adapter);
    }
}