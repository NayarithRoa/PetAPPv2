package com.example.petappv2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petappv2.DB.Mascotas;
import com.example.petappv2.R;
import com.example.petappv2.VerMascota;

import java.util.ArrayList;

public class ListaMascotasAdapter extends RecyclerView.Adapter<ListaMascotasAdapter.ViewHolder>{
    private ArrayList<Mascotas> listaMascotas;

    public ListaMascotasAdapter(ArrayList<Mascotas> listaMascotas) {
        this.listaMascotas = listaMascotas;
        //this.layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ListaMascotasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mascota, null, false);
        return new ListaMascotasAdapter.ViewHolder(view);
    }

    @Override
    //Asignar los elementos a cada una de las opciones
    public void onBindViewHolder(@NonNull ListaMascotasAdapter.ViewHolder viewHolder, int position) {
        viewHolder.nombre.setText(listaMascotas.get(position).getNombre());
        viewHolder.ubicacion.setText(listaMascotas.get(position).getUbicacion());
        Glide.with(viewHolder.itemView.getContext()).load(listaMascotas.get(position).getImagen()).into(viewHolder.foto);

        /*int drawableReourceId=viewHolder.itemView.getContext().getResources().getIdentifier(listaMascotas.get(position).getImagen(),"drawable",viewHolder.itemView.getContext().getPackageName());
        Glide.with(viewHolder.itemView.getContext()).load("https://loremflickr.com/320/240").into(viewHolder.foto);*/
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,ubicacion;
        ImageView foto;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.titulo);
            foto=itemView.findViewById(R.id.foto);
            ubicacion=itemView.findViewById(R.id.ubicacion);
            addBtn=itemView.findViewById(R.id.addBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerMascota.class);
                    intent.putExtra("ID", listaMascotas.get(getAdapterPosition()).getId_Mascota());
                    context.startActivity(intent);
                }
            });
        }

    }
}
