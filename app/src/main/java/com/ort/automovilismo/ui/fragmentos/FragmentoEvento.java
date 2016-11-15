package com.ort.automovilismo.ui.fragmentos;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Evento;
import com.ort.automovilismo.modelo.Weather;
import com.ort.automovilismo.ui.JSONWeatherParser;
import com.ort.automovilismo.ui.ResultadosFragment;
import com.ort.automovilismo.ui.WeatherHttpClient;
import com.ort.automovilismo.ui.adaptadores.AdaptadorHorarios;
import org.json.JSONException;
import java.util.ArrayList;

import static com.ort.automovilismo.R.id.condIcon;

public class FragmentoEvento extends Fragment {

    private static final String EVENTO = "Evento";
    private ArrayList<Evento> LEventos = new ArrayList<>();
    private ProgressDialog progressDiag;
    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;

    // ******** CLIMA ************ //
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView hum;
    private ImageView imgView;
    private String imagen;

    private static String IMG_URL = "http://openweathermap.org/img/w/";
    // FIN-CLIMA***************** //

    public static FragmentoEvento nuevaInstancia(Evento evento) {
        FragmentoEvento fragment = new FragmentoEvento();
        Bundle args = new Bundle();
        args.putSerializable(EVENTO,evento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_eventos, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Evento evento = (Evento) getArguments().getSerializable(EVENTO);

        //Elementos de la vista
        TextView tituloEvento = (TextView) view.findViewById(R.id.evento_titulo);
        TextView fechas = (TextView) view.findViewById(R.id.evento_fechas);
        TextView circuitoNombre = (TextView) view.findViewById(R.id.circuito_nombre);
        TextView circuitoNumero = (TextView) view.findViewById(R.id.circuito_numero);
        TextView longitud = (TextView) view.findViewById(R.id.circuito_longitud);
        TextView curvas = (TextView) view.findViewById(R.id.circuito_curvas);
        ImageView circuitoMiniatura = (ImageView) view.findViewById(R.id.circuito_miniatura);
        Button btnResultados = (Button) view.findViewById(R.id.btnResultados);


        // ******** CLIMA ************ //
        condDescr = (TextView) view.findViewById(R.id.condDescr);
        temp = (TextView) view.findViewById(R.id.temp);
        hum = (TextView) view.findViewById(R.id.hum);
        press = (TextView) view.findViewById(R.id.press);
        windSpeed = (TextView) view.findViewById(R.id.windSpeed);
        windDeg = (TextView) view.findViewById(R.id.windDeg);
        imgView = (ImageView) view.findViewById(R.id.condIcon);

        String auxLatitud=evento.getCircuito().getLatitud()+"";
        //auxLatitud=auxLatitud.substring(0,2);
        // imprimir latitud

        String auxLongitud=evento.getCircuito().getLongitud()+"";
        //auxLongitud=auxLongitud.substring(0,2);
        // imprimir longitud
        JSONWeatherTask task = new JSONWeatherTask();
        String ubicacion="lat="+auxLatitud+"&lon="+auxLongitud;
        //String ubicacion="lat=-34.7821679&lon=-55.9923278";
       // task.execute(new String[]{city});
        task.execute(new String[]{ubicacion});
        // FIN-CLIMA***************** //

        tituloEvento.setText(evento.getTitulo());
        fechas.setText(evento.getsFecha());
        circuitoNombre.setText(evento.getCircuito().getNombre());
        circuitoNumero.setText(evento.getCircuito().getNumero());
        longitud.setText(String.valueOf(evento.getCircuito().getLongitud()));
        curvas.setText(String.valueOf(evento.getCircuito().getCurvas()));
        String urlImage = "http://www.baremos.uy:8000/images_pilotos/" + evento.getCircuito().getIdDrawable() + ".jpg";
        Glide.with(view.getContext())
                .load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.auvo_error)
                .fitCenter()
                .into(circuitoMiniatura);
        while(imagen==null);
        Glide.with(view.getContext())
                .load(IMG_URL + imagen.toString() + ".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(imgView);
        //Cargo horarios
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorHorarios adapter = new AdaptadorHorarios(evento.getListaActividades());
        reciclador.setAdapter(adapter);
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultadosFragment resultadosFragment = ResultadosFragment.newInstance(evento.getResultados());
                getFragmentManager().popBackStackImmediate();
                getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, resultadosFragment).addToBackStack("").commit();
            }
        });
    }
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
           // String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));
            try {
                weather = JSONWeatherParser.getWeather(data);
                imagen=weather.currentCondition.getIcon();


                //weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

           condDescr.setText(weather.currentCondition.getDescr());
           // condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            //condDescr.setText(weather.currentCondition.getCondition());
            temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "ºC");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + + Math.round(weather.wind.getSpeed() * 1.6) + " kph");
            windDeg.setText("" + weather.wind.getDeg() + "º");

        }
    }
}
