package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private SupportMapFragment supportMapFragment;
    private View rootView;
    private GoogleApiClient googleApiClient;
    private GoogleMap mMap;
    private Location location;
    private LatLng locationAtual;
    private Municipio municipio;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            municipio = getArguments().getParcelable("municipio");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        supportMapFragment = SupportMapFragment.newInstance();
        rootView = inflater.inflate(R.layout.fragment_maps, null);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().replace(R.id.container_map, supportMapFragment).commitAllowingStateLoss();
        }

        callConnection();

        supportMapFragment.getMapAsync(this);



        return rootView;
    }

    private synchronized void callConnection(){
        googleApiClient = new GoogleApiClient.Builder(getContext()).addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if(municipio!=null){
            LatLng municipioLocation = new LatLng(municipio.getLatitude(),municipio.getLongitude());
            setLocation(municipio.getName(),municipioLocation);
        }
    }



    public void setLocation(String title, LatLng lc) {
        this.mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MarkerOptions marker = new MarkerOptions().position(lc).title(title);
        this.mMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(lc).zoom(14).build();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void refreshMap(Location lc,String address){
        this.mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng location = new LatLng(lc.getLatitude(), lc.getLongitude());

        /**Pegar no banco todos os municipios, colocar na classe Municipio e criar um while para addMarker para todos**/
        MarkerOptions marker = new MarkerOptions().position(location).title(address);
        this.mMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(13).build();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public ArrayList<Municipio> readFile() {
        /*
            Dados dos municipios encontrados em : http://www.mbi.com.br/mbi/biblioteca/utilidades/dddcepce/
         */

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;

        String linha;
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        String nomeMunicipio;
        double latitudeMunicipio;
        double longitudeMunicipio;

        try {
            inputStream = assetManager.open("municipiosCeara.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((nomeMunicipio = bufferedReader.readLine()) != null) {
                latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());

                Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio);

                municipios.add(municipio);
            }
            inputStream.close();
            System.out.println(municipios.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.getActivity(), "Errou", Toast.LENGTH_SHORT).show();
        }

        return municipios;
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();

    }

    public void doConnect(View view) {
        googleApiClient.connect();
    }

    public void doDisconnect(View view) {
        googleApiClient.disconnect();
    }

    public void doSubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationRequest request = new LocationRequest();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            request.setInterval(5000);
            request.setSmallestDisplacement(2);

            //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, this);
        }
    }


    public void doUnsubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this.getContext(), "Connected!", Toast.LENGTH_LONG).show();

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location != null){

        }
    }

    @Override
    public void onConnectionSuspended(int value) {
        Toast.makeText(this.getContext(), "Disconnected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(this.getContext(), "Connection failed...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        String text = "Updated Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        Toast.makeText(this.getContext(), text, Toast.LENGTH_LONG).show();
    }
}

