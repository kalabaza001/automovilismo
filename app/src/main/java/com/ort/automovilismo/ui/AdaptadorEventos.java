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
import com.ort.automovilismo.modelo.Evento;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorEventos
        extends RecyclerView.Adapter<AdaptadorEventos.ViewHolder> {


    private final List<Evento> items;
    private int seleccion;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titulo_evento;
        public TextView fecha_evento;
        public TextView lugar_evento;

        public ViewHolder(View v) {
            super(v);
            titulo_evento = (TextView) v.findViewById(R.id.titulo_evento);
            fecha_evento = (TextView) v.findViewById(R.id.fecha_evento);
            lugar_evento = (TextView) v.findViewById(R.id.lugar_evento);
        }
    }

    public AdaptadorEventos(List<Evento> items,int seleccion) {
        this.items = items;
        this.seleccion = seleccion;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragmento_eventos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Evento item = items.get(seleccion);
        viewHolder.fecha_evento.setText(item.getsFecha());
        viewHolder.titulo_evento.setText(item.getTitulo());
        viewHolder.lugar_evento.setText(item.getTitulo());
    }
}