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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView posicion;
        public TextView numero;
        public TextView nombre;
        public TextView primeraF;
        public TextView segundaF;
        public TextView total;

        public ViewHolder(View v) {
            super(v);
            posicion = (TextView) v.findViewById(R.id.camp_pos);
            numero = (TextView) v.findViewById(R.id.camp_num);
            primeraF = (TextView) v.findViewById(R.id.camp_1f);
        }
    }

    private List<RowCampeonato>RowCampeonatoList;
    public AdaptadorCampeonato(List<RowCampeonato>lista) {
        RowCampeonatoList=lista;
    }

    @Override
    public int getItemCount() {
        return RowCampeonatoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_campeonato, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RowCampeonato item = RowCampeonatoList.get(i);
        //Log.e("Piloto: ", listadoPiloto.get(i).getNombre() + " " + listadoPiloto.get(i).getApellido());
        Log.e("Pos: ", Integer.toString(RowCampeonatoList.get(i).getPosicion()));
        viewHolder.posicion.setText(item.getPosicion());
        viewHolder.numero.setText("1");
        viewHolder.nombre.setText("TEST");
        viewHolder.primeraF.setText(item.getPrimerFecha());

       // viewHolder.precio.setText("$" + item.getPrecio());

    }


}