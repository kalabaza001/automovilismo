package com.ort.automovilismo.ui.fragmentos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.RowCampeonato;
import com.ort.automovilismo.ui.adaptadores.AdaptadorPilotosCampeonato;

import java.util.ArrayList;


public class FragmentoPilotosCampeonato extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorPilotosCampeonato adaptador;
    private ProgressDialog progressDiag;
    final ArrayList<RowCampeonato> LRows = new ArrayList<RowCampeonato>();
    //Datos del piloto
    final static String ROW_CAMPEONATO = "Row";

    private RowCampeonato rowCampeonato;

    public FragmentoPilotosCampeonato() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            rowCampeonato = (RowCampeonato) savedInstanceState.getSerializable(ROW_CAMPEONATO);
        }

        Bundle args = getArguments();
        rowCampeonato = (RowCampeonato) args.getSerializable(ROW_CAMPEONATO);
        LRows.add(rowCampeonato);

        View view = inflater.inflate(R.layout.fragmento_pilotos_campeonato, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
        adaptador = new AdaptadorPilotosCampeonato(LRows);
        reciclador.setAdapter(adaptador);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            rowCampeonato = (RowCampeonato) args.getSerializable(ROW_CAMPEONATO);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current rowCampeonato selection in case we need to recreate the fragment
        outState.putSerializable(ROW_CAMPEONATO, rowCampeonato);
    }

    public static Fragment newInstance() {
        return new FragmentoPilotosCampeonato();
    }

}


