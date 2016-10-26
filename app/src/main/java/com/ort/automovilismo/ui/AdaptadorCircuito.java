package com.ort.automovilismo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.RowCampeonato;

import java.util.List;


public class AdaptadorCircuito
        extends RecyclerView.Adapter<AdaptadorCircuito.ViewHolder> {

    private List<Circuito> listaCircuitos;
    private RecyclerClickListener listener;

    public void setListener(RecyclerClickListener listener) {
        this.listener = listener;
    }

    public AdaptadorCircuito(List<Circuito> lista) {
        listaCircuitos = lista;
    }

    public List<Circuito> getListaCircuitos() {
        return listaCircuitos;
    }

    @Override
    public int getItemCount() {
        return listaCircuitos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_circuitos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Circuito item = listaCircuitos.get(i);

        viewHolder.nombre.setText(item.getNombre());
        viewHolder.numero.setText(item.getNumero());
        viewHolder.longitud.setText(String.valueOf(item.getLongitud()));
        viewHolder.curvas.setText(String.valueOf(item.getCurvas()));
        //viewHolder.primeraF.setText(item.getPrimerFecha());
        //viewHolder.segundaF.setText(item.getSegundaFecha());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView numero;
        public TextView longitud;
        public TextView curvas;
        public TextView record;
        public TextView historia;


        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.circuito_nombre);
            numero = (TextView) v.findViewById(R.id.circuito_numero);
            longitud = (TextView) v.findViewById(R.id.circuito_longitud);
            curvas = (TextView) v.findViewById(R.id.circuito_curvas);
            //// TODO: 25/10/16 Hacer implementacion de record e historia
            //record = (TextView) v.findViewById(R.id.circuito_record);
            //historia = (TextView) v.findViewById(R.id.circuito_historia);

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