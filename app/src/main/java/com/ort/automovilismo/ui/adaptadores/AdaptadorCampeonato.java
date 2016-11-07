package com.ort.automovilismo.ui.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.RowCampeonato;
import com.ort.automovilismo.modelo.Utils;
import com.ort.automovilismo.ui.RecyclerClickListener;

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
        //Log.d("Piloto: " + item.getPiloto().getApellido() + , listadoPiloto.get(i).getNombre() + " " + listadoPiloto.get(i).getApellido());
        viewHolder.posicion.setText(Integer.toString(item.getPosicion()));
        viewHolder.total.setText(Integer.toString(item.getTotal()));
        viewHolder.nombre.setText(item.getPiloto().getNombre() + " " + item.getPiloto().getApellido());
        String urlImage = Utils.getServidor() + "images_pilotos/" + item.getPiloto().getIdDrawable() + ".jpg";
        //String urlImage = "http://www.baremos.uy:8000/images_pilotos/" + item.getPiloto().getIdDrawable() + ".jpg";
        Glide.with(viewHolder.itemView.getContext())
                .load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // .placeholder(R.drawable.placeholder)
                //.error(R.drawable.auvo_error)
                // .centerCrop()
                .fitCenter()
                .into(viewHolder.miniaturaPiloto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView posicion;
        public TextView numero;
        public TextView nombre;
        public ImageView miniaturaPiloto;
        public TextView segundaF;
        public TextView total;

        public ViewHolder(View v) {
            super(v);
            posicion = (TextView) v.findViewById(R.id.camp2_pos);
            total = (TextView) v.findViewById(R.id.camp2_total);
            nombre = (TextView) v.findViewById(R.id.camp2_nombre);
            miniaturaPiloto = (ImageView) v.findViewById(R.id.iv_avatar) ;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }

}