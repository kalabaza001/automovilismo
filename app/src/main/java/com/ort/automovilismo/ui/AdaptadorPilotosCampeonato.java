package com.ort.automovilismo.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.RowCampeonato;

import java.util.List;


public class AdaptadorPilotosCampeonato
        extends RecyclerView.Adapter<AdaptadorPilotosCampeonato.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView numero;
        public TextView nombre;
        public ImageView imagenPiloto;
        public TextView marcaAuto;
        public TextView primerFecha;
        public TextView segundaFecha;
        public TextView terceraFecha;
        public TextView cuartaFecha;
        public TextView quintaFecha;
        public TextView sextaFecha;
        public TextView septimaFecha;
        public TextView octavaFecha;
        public TextView novenaFecha;
        public TextView decimaFecha;
        public TextView totalPuntos;

        public ViewHolder(View v) {
            super(v);
            imagenPiloto = (ImageView) v.findViewById(R.id.miniatura_pilotos_campeonato);
            // numero = (TextView) v.findViewById(R.id.numero_pilotos_campeonato);
            nombre = (TextView) v.findViewById(R.id.nombre_pilotos_campeonato);
            marcaAuto = (TextView) v.findViewById(R.id.marca_auto_pilotos_campeonato);
            primerFecha = (TextView) v.findViewById(R.id.primer_fecha_campeonato);
            segundaFecha = (TextView) v.findViewById(R.id.segunda_fecha_campeonato);
            terceraFecha = (TextView) v.findViewById(R.id.tercera_fecha_campeonato);
            cuartaFecha = (TextView) v.findViewById(R.id.cuarta_fecha_campeonato);
            quintaFecha = (TextView) v.findViewById(R.id.quinta_fecha_campeonato);
            sextaFecha = (TextView) v.findViewById(R.id.sexta_fecha_campeonato);
            septimaFecha = (TextView) v.findViewById(R.id.septima_fecha_campeonato);
            octavaFecha = (TextView) v.findViewById(R.id.octava_fecha_campeonato);
            novenaFecha = (TextView) v.findViewById(R.id.novena_fecha_campeonato);
            decimaFecha = (TextView) v.findViewById(R.id.decima_fecha_campeonato);
            totalPuntos = (TextView) v.findViewById(R.id.total_campeonato);
        }
    }

    private List<RowCampeonato>listadoRow;
    public AdaptadorPilotosCampeonato(List<RowCampeonato>lista) {
        listadoRow=lista;
    }

    @Override
    public int getItemCount() {
        return listadoRow.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pilotos_campeonato, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RowCampeonato item = listadoRow.get(i);
        Log.d("Adapter ", item.toString());
        Glide.with(viewHolder.itemView.getContext())
               // .load("http://superturismo.com.uy/wp-content/uploads/2015/10/rama-foto.jpg")
                .load("http://imageshack.com/a/img921/4559/kLKnfL.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
               // .placeholder(R.drawable.placeholder)
                .error(R.drawable.auvo_error)
               // .centerCrop()
                .fitCenter()
                .into(viewHolder.imagenPiloto);


        //viewHolder.numero.setText(Integer.toString(item.getPiloto().getNumero()));
        String nom = item.getPiloto().getNombre() + " " + item.getPiloto().getApellido();
        viewHolder.nombre.setText(nom);
        viewHolder.marcaAuto.setText(item.getPiloto().getMarcaAuto());
        viewHolder.primerFecha.setText(item.getPrimerFecha());
        viewHolder.segundaFecha.setText(item.getSegundaFecha());
        viewHolder.terceraFecha.setText(item.getTercerFecha());
        viewHolder.cuartaFecha.setText(item.getCuartaFecha());
        viewHolder.quintaFecha.setText(item.getQuintaFecha());
        viewHolder.sextaFecha.setText(item.getSextaFecha());
        viewHolder.septimaFecha.setText(item.getSeptimaFecha());
        viewHolder.octavaFecha.setText(item.getOctavaFecha());
        viewHolder.novenaFecha.setText(item.getNovenaFecha());
        viewHolder.decimaFecha.setText(item.getDecimaFecha());
        viewHolder.totalPuntos.setText(String.valueOf(item.getTotal()));
    }
}