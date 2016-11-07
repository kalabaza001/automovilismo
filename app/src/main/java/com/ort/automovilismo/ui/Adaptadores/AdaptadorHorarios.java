package com.ort.automovilismo.ui.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Actividad;

import java.util.List;

/**
 * Created by andres on 05/11/16.
 */

public class AdaptadorHorarios
        extends RecyclerView.Adapter<AdaptadorHorarios.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView hora;
        public TextView actividad;


        public ViewHolder(View v) {
            super(v);
            hora = (TextView) v.findViewById(R.id.actividad_horario);
            actividad = (TextView) v.findViewById(R.id.titulo_horario);
        }
    }

    private List<Actividad> listadoActividad;
    public AdaptadorHorarios(List<Actividad>lista) {
        listadoActividad=lista;
    }

    @Override
    public int getItemCount() {
        return listadoActividad.size();
    }

    @Override
    public AdaptadorHorarios.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_horarios, viewGroup, false);
        return new AdaptadorHorarios.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorHorarios.ViewHolder viewHolder, int i) {
        Actividad item = listadoActividad.get(i);
        viewHolder.actividad.setText(item.getTitulo());
        viewHolder.hora.setText(item.getHoraDH());

    }
}