package com.ort.automovilismo.ui.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.RowCarrera;
import com.ort.automovilismo.ui.RecyclerClickListener;

import java.util.List;


public class AdaptadorCarrera
        extends RecyclerView.Adapter<AdaptadorCarrera.ViewHolder> {

    private List<RowCarrera> RowCarreraList;
    private RecyclerClickListener listener;

    public void setListener(RecyclerClickListener listener) {
        this.listener = listener;
    }

    public AdaptadorCarrera(List<RowCarrera> lista) {
        RowCarreraList = lista;
    }

    public List<RowCarrera> getRowCarreraList() {
        return RowCarreraList;
    }

    @Override
    public int getItemCount() {
        return RowCarreraList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_carrera, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RowCarrera item = RowCarreraList.get(i);
        viewHolder.posicion.setText(String.valueOf(item.getPosicion()));
        viewHolder.numero.setText(String.valueOf(item.getPiloto().getNumero()));
        viewHolder.nombre.setText(item.getPiloto().getNombre() + " " + item.getPiloto().getApellido());
        viewHolder.tiempo.setText(String.valueOf(item.getTiempo()));
        viewHolder.diferencia.setText(String.valueOf(item.getDiferencia()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView posicion;
        public TextView numero;
        public TextView nombre;
        public TextView tiempo;
        public TextView diferencia;

        public ViewHolder(View v) {
            super(v);
            posicion = (TextView) v.findViewById(R.id.carrera_pos);
            numero = (TextView) v.findViewById(R.id.carrera_piloto_num);
            nombre = (TextView) v.findViewById(R.id.carrera_piloto_nom);
            tiempo = (TextView) v.findViewById(R.id.carrera_tiempo);
            diferencia = (TextView) v.findViewById(R.id.carrera_diff);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }

}