package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Piloto {
    private int idPiloto;
    private String nombre;
    private String apellido;
    private String fNac;
    private int numero;
    private String marcaAuto;
    private String equipo;
    private String nacionalidad;
    private int puntos;
    private int podios;
    private int campeonatos;
    private int idDrawable;

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

    public String getfNac() {
        return fNac;
    }

    public void setfNac(String fNac) {
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

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

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


}
