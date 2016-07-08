package br.ufc.dc.dspm.balancobrasil.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.ufc.dc.dspm.balancobrasil.Fragments.MunicipioFragment;
import br.ufc.dc.dspm.balancobrasil.Fragments.SetorFragment;

/**
 * Created by thiagoripardo on 06/07/16.
 */
public class TabMainActivityAdapter extends FragmentPagerAdapter {

    int tabCount;
    Intent intent;

    public TabMainActivityAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        //bundle.putParcelable("data", data);

        switch (position) {
            case 0:
                MunicipioFragment tab1 = new MunicipioFragment();
                tab1.setArguments(bundle);

                return tab1;
            case 1:
                SetorFragment tab2 = new SetorFragment();
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