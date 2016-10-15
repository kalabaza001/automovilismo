package com.ort.automovilismo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static com.ort.automovilismo.R.id.imageView;


public class AdaptadorPilotos
        extends RecyclerView.Adapter<AdaptadorPilotos.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView numero;
        public TextView nombre;
        public ImageView imagenPiloto;
        public TextView marcaAuto;

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
        Piloto item = listadoPiloto.get(i);
        Log.e("Piloto: ", listadoPiloto.get(i).getNombre() + " " + listadoPiloto.get(i).getApellido());
        Log.e("Imagen: ", listadoPiloto.get(i).getIdDrawable());
        Glide.with(viewHolder.itemView.getContext())
                .load(listadoPiloto.get(i).getImagenPiloto())
                .centerCrop()
                .into(viewHolder.imagenPiloto);
        viewHolder.nombre.setText(item.getNombre() + " " + item.getApellido());
        viewHolder.numero.setText(Integer.toString(item.getNumero()));
        viewHolder.marcaAuto.setText(item.getMarcaAuto());
       // viewHolder.precio.setText("$" + item.getPrecio());

    }


}