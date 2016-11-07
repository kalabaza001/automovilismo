package com.ort.automovilismo.ui.fragmentos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Utils;
import com.ort.automovilismo.ui.DataParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class FragmentoMapa extends Fragment
        implements OnMapReadyCallback, LocationListener {
    private GoogleMap googleMap;
    private MapView mapView;
    private ArrayList<LatLng> MarkerPoints;
    private TextView tvLatitud, tvLongitud;
    private double latitud =-34.90317866;
    private double longitud=-56.179533;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragmento_mapa, container, false);


        mapView = (MapView) view.findViewById(R.id.mi_mapa);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        MarkerPoints = new ArrayList<>();
        tvLatitud = (TextView) view.findViewById(R.id.tvLatitud);
        tvLongitud = (TextView) view.findViewById(R.id.tvLongitud);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(false);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);

        map.getUiSettings().setZoomControlsEnabled(true);


        LatLng cine = new LatLng(latitud, longitud);

        LatLng aqui;

        if (!Utils.isGPS(this.getActivity())) {
            double latitud = Double.parseDouble(Utils.leer(this.getActivity(), "ultimoLatitud"));
            double longitud = Double.parseDouble(Utils.leer(this.getActivity(), "ultimoLongitud"));
            aqui = new LatLng(latitud, longitud);
            Toast.makeText(getActivity().getApplicationContext(), "Sin GPS activado, ultimo lugar conocido", Toast.LENGTH_SHORT).show();
        } else {
            LocationManager service = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            try {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location ubicacion = service.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                Utils.guardar(getActivity(),"ultimoLatitud",ubicacion.getLatitude()+"");
                Utils.guardar(getActivity(),"ultimoLongitud",ubicacion.getLongitude()+"");
                aqui = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
            }
            catch(Exception ex){
                Location ubicacion = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Utils.guardar(getActivity(),"ultimoLatitud",ubicacion.getLatitude()+"");
                Utils.guardar(getActivity(),"ultimoLongitud",ubicacion.getLongitude()+"");
                aqui = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
            }
        }
            //Necesitamos posicion actual del gps


        map.addMarker(new MarkerOptions()
                .position(cine)
                .title("Cine")
        );
        map.addMarker(new MarkerOptions()
                .position(aqui)
                .title("Actual")
        );

        // Getting URL to the Google Directions API
        MarkerPoints.add(aqui);
        MarkerPoints.add(cine);

        String url = getUrl(aqui, cine);
        FetchUrl FetchUrl = new FetchUrl();
        FetchUrl.execute(url);
        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(aqui, 10.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(aqui));
        map.animateCamera(CameraUpdateFactory.zoomTo(11));

    }

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
        private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

            // Parsing the data in non-ui thread
            @Override
            protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try {
                    jObject = new JSONObject(jsonData[0]);
                    Log.d("ParserTask",jsonData[0].toString());
                    DataParser parser = new DataParser();
                    Log.d("ParserTask", parser.toString());

                    // Starts parsing data
                    routes = parser.parse(jObject);
                    Log.d("ParserTask","Executing routes");
                    Log.d("ParserTask",routes.toString());

                } catch (Exception e) {
                    Log.d("ParserTask",e.toString());
                    e.printStackTrace();
                }
                return routes;
            }

            // Executes in UI thread, after the parsing process
            @Override
            protected void onPostExecute(List<List<HashMap<String, String>>> result) {
                ArrayList<LatLng> points;
                PolylineOptions lineOptions = null;

                // Traversing through all the routes
                for (int i = 0; i < result.size(); i++) {
                    points = new ArrayList<>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(10);
                    lineOptions.color(Color.RED);

                    Log.d("onPostExecute","onPostExecute lineoptions decoded");

                }

                // Drawing polyline in the Google Map for the i-th route
                if(lineOptions != null) {
                    googleMap.addPolyline(lineOptions);
                }
                else {
                    Log.d("onPostExecute","without Polylines drawn");
                }
            }
        }
    }



}

