package com.ort.automovilismo.ui.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.ui.adaptadores.AdaptadorPilotos;
import java.util.List;


public class FragmentoPilotosEvento extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorPilotos adaptador;
    private List<Piloto> listaPilotoEvento;

    public FragmentoPilotosEvento() {

    }

    public void setListaPilotoEvento(List<Piloto>listado){
        listaPilotoEvento=listado;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_pilotos, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new AdaptadorPilotos(listaPilotoEvento);
        reciclador.setAdapter(adaptador);
        return view;
    }

}
