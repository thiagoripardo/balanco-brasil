package br.ufc.dc.dspm.balancobrasil.MapsLocation;

import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;

/**
 * Created by Vasco on 08/07/2016.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView addressLabel;
    private TextView locationLabel;
    private GoogleApiClient googleApiClient;
    private SupportMapFragment supportMapFragment;
    private View rootview;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        supportMapFragment = SupportMapFragment.newInstance();
        rootview = inflater.inflate(R.layout.activity_maps, null);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().replace(R.id.map, supportMapFragment).commitAllowingStateLoss();
        }
        supportMapFragment.getMapAsync(this);
        return rootview;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng location = new LatLng(-3.7460927, -38.5743825);

        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        municipios = readFile();

        for(int i=0 ; i< municipios.size();i++){
            LatLng municipioLocation = new LatLng(municipios.get(i).getLatitude(),municipios.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(municipioLocation).title(municipios.get(i).getName()+" R$"+municipios.get(i).getValorRecebido()));
        }


        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(7).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    public void refreshMap(Location lc,String address){
        this.mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng location = new LatLng(lc.getLatitude(), lc.getLongitude());


        //assets
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        municipios = readFile();

        for(int i=0 ; i< municipios.size();i++){
            LatLng municipioLocation = new LatLng(municipios.get(i).getLatitude(),municipios.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(municipioLocation).title(municipios.get(i).getName()+" R$"+municipios.get(i).getValorRecebido()));
        }

        MarkerOptions marker = new MarkerOptions().position(location).title(address);
        this.mMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(10).build();
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
        String valorRecebido;

        try {
            inputStream = assetManager.open("municipiosCeara.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((nomeMunicipio = bufferedReader.readLine()) != null) {
                latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                valorRecebido = bufferedReader.readLine();

                Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio,valorRecebido);

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
}
