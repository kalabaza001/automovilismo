package com.ort.automovilismo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.ImageLoader;
import com.ort.automovilismo.modelo.Piloto;

import java.util.List;


public class AdaptadorPilotos
        extends RecyclerView.Adapter<AdaptadorPilotos.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView numero;
        public TextView nombre;
        public ImageView imagenPiloto;
        public TextView marcaAuto;
        public ImageLoader imageLoader;

        public ViewHolder(View v) {
            super(v);
            numero = (TextView) v.findViewById(R.id.numero_pilotos);
            nombre = (TextView) v.findViewById(R.id.nombre_pilotos);
            imagenPiloto = (ImageView) v.findViewById(R.id.miniatura_pilotos);
            marcaAuto = (TextView) v.findViewById(R.id.marca_auto_pilotos);
        }
    }

    private List<Piloto>listadoPiloto;
    public AdaptadorPilotos(List<Piloto>lista) {
        listadoPiloto=lista;
    }

    @Override
    public int getItemCount() {
        return listadoPiloto.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pilotos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //ImageLoader imageLoader= new ImageLoader(viewHolder.itemView.getContext());
        Piloto item = listadoPiloto.get(i);
        //Log.d("Piloto: ", listadoPiloto.get(i).getNombre() + " " + listadoPiloto.get(i).getApellido());
        //Log.d("Imagen: ", listadoPiloto.get(i).getIdDrawable());

        Glide.with(viewHolder.itemView.getContext())

               // .load("http://imageshack.com/a/img921/4559/kLKnfL.jpg")
                .load("http://www.baremos.uy:8000/images_pilotos/alejandro_borio.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
               // .placeholder(R.drawable.placeholder)
                //.error(R.drawable.auvo_error)
               // .centerCrop()
                .fitCenter()
                .into(viewHolder.imagenPiloto);


        viewHolder.nombre.setText(item.getNombre() + " " + item.getApellido());
        viewHolder.numero.setText(Integer.toString(item.getNumero()));
        viewHolder.marcaAuto.setText(item.getMarcaAuto());

    }
}