package com.ort.automovilismo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.ResultadoEvento;
import com.ort.automovilismo.ui.adaptadores.AdaptadorResultados;
import java.util.ArrayList;


public class ResultadosFragment extends Fragment {

   private static final String LISTA_RESULTADO_EVENTO = "Lista";


    public ResultadosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ResultadosFragment newInstance(ArrayList<ResultadoEvento> listaResultadoEvento) {
        ResultadosFragment fragment = new ResultadosFragment();
        Bundle args = new Bundle();
        args.putSerializable(LISTA_RESULTADO_EVENTO, listaResultadoEvento);
        fragment.setArguments(args);
        return fragment;
    }

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_resultados, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<ResultadoEvento> lista = (ArrayList<ResultadoEvento>) getArguments().getSerializable(LISTA_RESULTADO_EVENTO);
        RecyclerView reciclador = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorResultados adapter = new AdaptadorResultados(lista);
        reciclador.setAdapter(adapter);

    }
}
