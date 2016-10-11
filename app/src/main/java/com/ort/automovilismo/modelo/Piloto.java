package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Piloto {
    private String nombre;
    private int idDrawable;

    public Piloto(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static final List<Piloto> PILOTOS = new ArrayList<Piloto>();

    static {
        PILOTOS.add(new Piloto("Fernando Rama", R.drawable.fernando_rama));
        PILOTOS.add(new Piloto("Daniel Fresnedo", R.drawable.daniel_fesnedo));
        PILOTOS.add(new Piloto("Rodrigo Aramendia", R.drawable.rodrigo_aramendia));
        PILOTOS.add(new Piloto("Jorge Pontet", R.drawable.jorge_pontet));
        PILOTOS.add(new Piloto("Fabricio Larratea", R.drawable.fabricio_larratea));


    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
