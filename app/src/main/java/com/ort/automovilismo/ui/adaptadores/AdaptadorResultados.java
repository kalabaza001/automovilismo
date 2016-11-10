package com.ort.automovilismo.ui.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.ResultadoEvento;
import java.util.List;


public class AdaptadorResultados extends RecyclerView.Adapter<AdaptadorResultados.ViewHolder> {

    private final List<ResultadoEvento> listaResultadoEvento;

    public AdaptadorResultados(List<ResultadoEvento> items) {
        listaResultadoEvento = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resultados, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultadoEvento resultadoEvento = listaResultadoEvento.get(position);
        holder.posicion.setText(String.valueOf(resultadoEvento.getPosicion()));
        holder.numeroPiloto.setText(String.valueOf(resultadoEvento.getNumero()));
        holder.nombrePiloto.setText(resultadoEvento.getNombrePiloto());
    }

    @Override
    public int getItemCount() {
        return listaResultadoEvento.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        public TextView posicion;
        public TextView numeroPiloto;
        public TextView nombrePiloto;


        public ViewHolder(View view) {
            super(view);
            posicion = (TextView) view.findViewById(R.id.posicion_resultado);
            numeroPiloto = (TextView) view.findViewById(R.id.piloto_numero_resultado);
            nombrePiloto = (TextView) view.findViewById(R.id.piloto_nombre_resultado);
        }
    }
}
