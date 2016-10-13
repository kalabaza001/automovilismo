package com.ort.automovilismo.servicios;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.ort.automovilismo.modelo.Piloto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by andres on 12/10/16.
 */

public class serviciosPiloto {

    final ArrayList<Piloto> LPilotos = new ArrayList<Piloto>();

    public ArrayList<Piloto> getListaPilotos(Activity activity){
        //Consumo servicio
         new GetDataTask(activity).execute("http://localhost:8080/Pilotos");
        return LPilotos;
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
            try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    //progressBar.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Peliculas en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("description");

                    //Cargo los datos del JSONArray a arrayList de peliculas
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectPiloto = jsonArray.getJSONObject(i);

                        int idPiloto = jsonObjectPiloto.optInt("_id");
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
                        int idDrawable = jsonObjectPiloto.optInt("idDrawable");

                        Date ffNac = formatter.parse(fNac);

                        Piloto p = new Piloto(idPiloto,nombre,apellido,ffNac,numero,marcaAuto,equipo,nacionalidad,puntos,podios,campeonatos,idDrawable);

                        LPilotos.add(p);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }

            }
    }
}
