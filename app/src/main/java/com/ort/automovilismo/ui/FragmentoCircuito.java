package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.RowCampeonato;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FragmentoCircuito extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorCircuito adaptador;
    private ProgressDialog progressDiag;

    public FragmentoCircuito() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Consumo servicio
        View view = inflater.inflate(R.layout.fragmento_circuito, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/circuitos");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {
        private Activity m_activity;

        public GetDataTask(Activity activity) {
            m_activity = activity;
        }


        protected String doInBackground(String... urls) {
            try {
                // CONEXION
                URL url = new URL(urls[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //con.setRequestProperty("_id", String.valueOf(idAct));
                con.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String valor = reader.readLine();
                reader.close();
                con.disconnect();
                return valor;
            } catch (Exception e) {
                Log.e("ERROR ", e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String result) {
            if (m_activity != null) {
                List<Circuito> listaCircuitos = new ArrayList<>();
                try {
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de circuitos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectRowCircuito = jsonArray.getJSONObject(i);

                        //Datos de circuito
                        String idRowCircuito = jsonObjectRowCircuito.optString("_id");
                        String nombre = jsonObjectRowCircuito.optString("nombre");
                        String numero = jsonObjectRowCircuito.optString("numero");
                        int longitud = jsonObjectRowCircuito.optInt("longitud");
                        int curvas = jsonObjectRowCircuito.optInt("curvas");
                        String record = jsonObjectRowCircuito.optString("record");
                        String historia = jsonObjectRowCircuito.optString("historia");
                        String idDrawable = jsonObjectRowCircuito.optString("idDrawable");

                        //Circuito
                        Circuito circuito = new Circuito(idRowCircuito,nombre, numero,longitud,curvas,record,historia,idDrawable);
                        Log.d("Circuito: ", circuito.getNombre() + " " + circuito.getNumero());
                        listaCircuitos.add(circuito);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorCircuito(listaCircuitos);
                adaptador.setListener(new RecyclerClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, FragmentoPilotos.newInstance()).addToBackStack(null).commit();
                     //   Toast.makeText(getContext(), adaptador.getRowCampeonatoList().get(position).getPosicion()+"", Toast.LENGTH_LONG).show();
                    }
                });
                reciclador.setAdapter(adaptador);
                progressDiag.dismiss();
                //viewPilotos();
            }
        }
    }
}


