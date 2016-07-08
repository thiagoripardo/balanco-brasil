package br.ufc.dc.dspm.balancobrasil;

import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

import br.ufc.dc.dspm.balancobrasil.Fragments.MapsFragment;

public class MapaLocal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*Fragment fr = new MapsFragment();
        fr.setArguments(args);
        FragmentManager fm = getFragmentManager();
        SupportMapFragment fragmentTransaction = getFragmentManager().beginTransaction();;
        fragmentTransaction.replace(R.id.container_map, fr);
        fragmentTransaction.commit();*/
    }
}
