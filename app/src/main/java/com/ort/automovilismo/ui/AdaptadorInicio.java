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
import com.ort.automovilismo.modelo.ImagenInicio;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView fechaImagen;
        public TextView tituloImagen;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            fechaImagen = (TextView) v.findViewById(R.id.fecha_foto);
            tituloImagen = (TextView) v.findViewById(R.id.titulo_foto);
            imagen = (ImageView) v.findViewById(R.id.miniatura_foto_inicio);
        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return ImagenInicio.IMAGENES_INICIO.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ImagenInicio item = ImagenInicio.IMAGENES_INICIO.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.fechaImagen.setText(item.getDesc());
        viewHolder.tituloImagen.setText(""+item.getTitulo());

    }


}