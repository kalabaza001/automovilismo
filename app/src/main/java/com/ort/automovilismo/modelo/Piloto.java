package com.ort.automovilismo.modelo;

import android.graphics.Bitmap;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Piloto {
    private int idPiloto;
    private String nombre;
    private String apellido;
    private Date fNac;
    private int numero;
    private String marcaAuto;
    private String equipo;
    private String nacionalidad;
    private int puntos;
    private int podios;
    private int campeonatos;
    private String idDrawable;
    private Bitmap imagenPiloto;

    public int getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(int idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getfNac() {
        return fNac;
    }

    public void setfNac(Date fNac) {
        this.fNac = fNac;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMarcaAuto() {
        return marcaAuto;
    }

    public void setMarcaAuto(String marcaAuto) {
        this.marcaAuto = marcaAuto;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPodios() {
        return podios;
    }

    public void setPodios(int podios) {
        this.podios = podios;
    }

    public int getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(int campeonatos) {
        this.campeonatos = campeonatos;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public Bitmap getImagenPiloto() {
        return imagenPiloto;
    }

    public void setImagenPiloto(Bitmap imagenPiloto) {
        this.imagenPiloto = imagenPiloto;
    }

    public Piloto(String nombre, String idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public Piloto(int idPiloto, String nombre, String apellido, Date fNac, int numero, String marcaAuto, String equipo, String nacionalidad, int puntos, int podios, int campeonatos, String idDrawable) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNac = fNac;
        this.numero = numero;
        this.marcaAuto = marcaAuto;
        this.equipo = equipo;
        this.nacionalidad = nacionalidad;
        this.puntos = puntos;
        this.podios = podios;
        this.campeonatos = campeonatos;
        this.idDrawable = idDrawable;
        //this.imagenPiloto = imagenPiloto;
    }

    public static final List<Piloto> PILOTOS = new ArrayList<Piloto>();

    /*static {
        PILOTOS.add(new Piloto("Fernando Rama", R.drawable.img_fernando_rama));
        PILOTOS.add(new Piloto("Daniel Fresnedo", R.drawable.img_daniel_fesnedo));
        PILOTOS.add(new Piloto("Rodrigo Aramendia", R.drawable.img_rodrigo_aramendia));
        PILOTOS.add(new Piloto("Jorge Pontet", R.drawable.img_jorge_pontet));
        PILOTOS.add(new Piloto("Fabricio Larratea", R.drawable.img_fabricio_larratea));


    }*/


}
