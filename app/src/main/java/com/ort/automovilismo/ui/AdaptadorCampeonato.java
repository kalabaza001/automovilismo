package com.ort.automovilismo.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.RowCampeonato;

import java.util.List;


public class AdaptadorCampeonato
        extends RecyclerView.Adapter<AdaptadorCampeonato.ViewHolder> {

    private List<RowCampeonato> RowCampeonatoList;
    private RecyclerClickListener listener;

    public void setListener(RecyclerClickListener listener) {
        this.listener = listener;
    }

    public AdaptadorCampeonato(List<RowCampeonato> lista) {
        RowCampeonatoList = lista;
    }

    public List<RowCampeonato> getRowCampeonatoList() {
        return RowCampeonatoList;
    }

    @Override
    public int getItemCount() {
        return RowCampeonatoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_campeonato2, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RowCampeonato item = RowCampeonatoList.get(i);
        //Log.e("Piloto: ", listadoPiloto.get(i).getNombre() + " " + listadoPiloto.get(i).getApellido());
        viewHolder.posicion.setText(Integer.toString(item.getPosicion()));
        viewHolder.total.setText(Integer.toString(item.getTotal()));
        viewHolder.nombre.setText(item.getPiloto().getNombre() + " " + item.getPiloto().getApellido());
        //viewHolder.primeraF.setText(item.getPrimerFecha());
        //viewHolder.segundaF.setText(item.getSegundaFecha());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView posicion;
        public TextView numero;
        public TextView nombre;
        public TextView primeraF;
        public TextView segundaF;
        public TextView total;

        public ViewHolder(View v) {
            super(v);
            posicion = (TextView) v.findViewById(R.id.camp2_pos);
            total = (TextView) v.findViewById(R.id.camp2_total);
            nombre = (TextView) v.findViewById(R.id.camp2_nombre);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
            //primeraF = (TextView) v.findViewById(R.id.camp_1f);
            //segundaF = (TextView) v.findViewById(R.id.camp_2f);
        }
    }

}