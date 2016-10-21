package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ort.automovilismo.R;
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


public class FragmentoCampeonato extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorCampeonato adaptador;
    private ProgressDialog progressDiag;

    public FragmentoCampeonato() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Consumo servicio
        View view = inflater.inflate(R.layout.fragmento_campeonato2, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/campeonato");
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
                List<RowCampeonato> LRows = new ArrayList<>();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de pilotos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectRowCampeonato = jsonArray.getJSONObject(i);


                        //Datos de puntaje
                        String idRowCampeonato = jsonObjectRowCampeonato.optString("_id");
                        int posicion = jsonObjectRowCampeonato.optInt("posicion");
                        String primerFecha = jsonObjectRowCampeonato.optString("1");
                        String segundaFecha = jsonObjectRowCampeonato.optString("2");
                        String terceraFecha = jsonObjectRowCampeonato.optString("3");
                        String cuartaFecha = jsonObjectRowCampeonato.optString("4");
                        String quintaFecha = jsonObjectRowCampeonato.optString("5");
                        String sextaFecha = jsonObjectRowCampeonato.optString("6");
                        String septimaFecha = jsonObjectRowCampeonato.optString("7");
                        String octavaFecha = jsonObjectRowCampeonato.optString("8");
                        String novenaFecha = jsonObjectRowCampeonato.optString("9");
                        String decimaFecha = jsonObjectRowCampeonato.optString("10");
                        String total = String.valueOf(primerFecha) + String.valueOf(segundaFecha);

                        //Piloto
                        JSONObject jsonObjectPiloto = jsonObjectRowCampeonato.getJSONObject("piloto");
                        String idPiloto = jsonObjectPiloto.optString("_id");
                        String nombre = jsonObjectPiloto.optString("nombre");
                        String apellido = jsonObjectPiloto.optString("apellido");
                        String fNac = jsonObjectPiloto.optString("fNac");
                        int numero = jsonObjectPiloto.optInt("numero");
                        String marcaAuto = jsonObjectPiloto.optString("marcaAuto");
                        String equipo = jsonObjectPiloto.optString("equipo");
                        String nacionalidad = jsonObjectPiloto.optString("nacionalidad");
                        int puntos = jsonObjectPiloto.optInt("puntos");
                        int podios = jsonObjectPiloto.optInt("podios");
                        int campeonatos = jsonObjectPiloto.optInt("campeonatos");
                        String idDrawable = jsonObjectPiloto.optString("idDrawable");

                        Date ffNac = new Date();
                        Piloto piloto = new Piloto(idPiloto, nombre, apellido, ffNac, numero, marcaAuto, equipo, nacionalidad, puntos, podios, campeonatos, idDrawable);
                        Log.d("piloto:", piloto.getNombre() + " " + piloto.getApellido());
                        Log.d("pos: ", Integer.toString(posicion) + "_" + primerFecha + "_" + segundaFecha);

                        RowCampeonato rowCampeonato = new RowCampeonato(idRowCampeonato, posicion, piloto,primerFecha,segundaFecha,terceraFecha,cuartaFecha,quintaFecha,sextaFecha,septimaFecha, octavaFecha, novenaFecha,decimaFecha);

                        LRows.add(rowCampeonato);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorCampeonato(LRows);
                adaptador.setListener(new RecyclerClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, FragmentoPilotos.newInstance()).addToBackStack(null).commit();
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


