package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class ImagenInicio {
    private String titulo;
    private String desc;
    private int idDrawable;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ImagenInicio(String desc, int idDrawable, String titulo) {
        this.desc = desc;
        this.idDrawable = idDrawable;
        this.titulo = titulo;
    }

    public static final List<ImagenInicio> IMAGENES_INICIO = new ArrayList<ImagenInicio>();

    static {
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio1, "Michell Bonnin"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio2, "Gran Premio Turil"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio3, "Diego Noceti"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio4, "Entrada a recta"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio5, "Equipo Noceti"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio6, "Safety Car"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio7, "Daniel Ferra"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio8, "Pre Largada"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio9, "Fernando Rama"));
        IMAGENES_INICIO.add(new ImagenInicio("8ª Fecha Autodromo P.Cabrera", R.drawable.inicio10, "Faltan 5"));
    }
}