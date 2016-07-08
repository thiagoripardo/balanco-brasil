package br.ufc.dc.dspm.balancobrasil.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import br.ufc.dc.dspm.balancobrasil.Fragments.MapsFragment;
import br.ufc.dc.dspm.balancobrasil.Fragments.MunicipioFragment;
import br.ufc.dc.dspm.balancobrasil.Fragments.PointsFragment;
import br.ufc.dc.dspm.balancobrasil.Fragments.SetorFragment;
import br.ufc.dc.dspm.balancobrasil.Model.Municipio;

/**
 * Created by thiagoripardo on 06/07/16.
 */
public class TabMunicipioAdapter extends FragmentPagerAdapter {

    int tabCount;
    Intent intent;
    private Municipio municipio;
    private ArrayList<String> arrayList;

    public TabMunicipioAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }


    public TabMunicipioAdapter (FragmentManager fm, int numberOfTabs, Municipio m) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.municipio = m;
        //this.arrayList = intent.getStringArrayListExtra("list");
        //Log.i("teste", municipio.getName());
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("municipio", municipio);
        //bundle.putStringArrayList("list", arrayList);

        switch (position) {
            case 0:
                PointsFragment tab1 = new PointsFragment();
                tab1.setArguments(bundle);

                return tab1;
            case 1:
                MapsFragment tab2 = new MapsFragment();
                tab2.setArguments(bundle);

                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}