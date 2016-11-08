package com.ort.automovilismo.ui.adaptadores;

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
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.Utils;
import com.ort.automovilismo.ui.RecyclerClickListener;

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
        //Lalaaa
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.numero.setText(item.getNumero());
        //viewHolder.longitud.setText(String.valueOf(item.getLongitud()));
       // viewHolder.curvas.setText(String.valueOf(item.getCurvas()));
        String urlImage = Utils.getServidor() + "images_pilotos/" + item.getIdDrawable() + ".jpg";
        Glide.with(viewHolder.itemView.getContext())
                .load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.auvo_error)
                // .centerCrop()
                .fitCenter()
                .into(viewHolder.imagenCircuito);
        //viewHolder.primeraF.setText(item.getPrimerFecha());
        //viewHolder.segundaF.setText(item.getSegundaFecha());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView numero;
     //   public TextView longitud;
     //   public TextView curvas;
        public ImageView imagenCircuito;
      //  public TextView record;
     //   public TextView historia;
     //   public Button botonIr;


        public ViewHolder(View v) {
            super(v);
            imagenCircuito = (ImageView) v.findViewById(R.id.circuito_miniatura);
            nombre = (TextView) v.findViewById(R.id.circuito_nombre);
            numero = (TextView) v.findViewById(R.id.circuito_numero);
         //   longitud = (TextView) v.findViewById(R.id.circuito_longitud);
         //   curvas = (TextView) v.findViewById(R.id.circuito_curvas);



            //// TODO: 25/10/16 Hacer implementacion de record e historia
            //record = (TextView) v.findViewById(R.id.circuito_record);
            //historia = (TextView) v.findViewById(R.id.circuito_historia);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }

}