package com.ort.automovilismo.ui.fragmentos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.Utils;
import com.ort.automovilismo.ui.adaptadores.AdaptadorPilotos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragmentoPilotos extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorPilotos adaptador;
    private ProgressDialog progressDiag;
    final ArrayList<Piloto> LPilotos = new ArrayList<Piloto>();
    private Bitmap imagenPiloto;

    public FragmentoPilotos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Consumo servicio
       // new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/pilotos");
        new GetDataTask(getActivity()).execute(Utils.getServidor() + "pilotos");
       // new GetDataTask(getActivity()).execute("http://www.baremos.uy:8000/pilotos");



        //Lo duermo porque a veces tarde minimo
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        //ProgressDialog progressBar = ProgressDialog.show(getActivity(), "Espere por favor...", "Pilotos...", true);
        SystemClock.sleep(1000);

        View view = inflater.inflate(R.layout.fragmento_pilotos, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);

        //adaptador = new AdaptadorPilotos(LPilotos);
        //reciclador.setAdapter(adaptador);
        return view;
    }

    public static Fragment newInstance() {
        return new FragmentoPilotos();
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
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de pilotos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectPiloto = jsonArray.getJSONObject(i);

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

                        //Date ffNac = formatter.parse(fNac);

                        Date ffNac = new Date();
                        Piloto p = new Piloto(idPiloto, nombre, apellido, ffNac, numero, marcaAuto, equipo, nacionalidad, puntos, podios, campeonatos, idDrawable);
                        Log.e("piloto:", p.getNombre() + " " + p.getApellido());
                        LPilotos.add(p);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorPilotos(LPilotos);
                reciclador.setAdapter(adaptador);
                progressDiag.dismiss();
                //viewPilotos();
            }
        }
    }
}


