package com.ort.automovilismo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Comida;
import com.ort.automovilismo.modelo.Piloto;

import java.util.List;


public class AdaptadorPilotos
        extends RecyclerView.Adapter<AdaptadorPilotos.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_pilotos);
            imagen = (ImageView) v.findViewById(R.id.miniatura_pilotos);
        }
    }

    private List<Piloto>listadoPiloto;
    public AdaptadorPilotos(List<Piloto>lista) {
        listadoPiloto=lista;
    }

    @Override
    public int getItemCount() {
        return Piloto.PILOTOS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pilotos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Piloto item = listadoPiloto.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
       // viewHolder.precio.setText("$" + item.getPrecio());

    }


}