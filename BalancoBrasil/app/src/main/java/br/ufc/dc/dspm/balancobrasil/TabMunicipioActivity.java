package br.ufc.dc.dspm.balancobrasil;

import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TabHost;
import android.widget.TextView;

import br.ufc.dc.dspm.balancobrasil.Adapters.TabMainActivityAdapter;
import br.ufc.dc.dspm.balancobrasil.Adapters.TabMunicipioAdapter;
import br.ufc.dc.dspm.balancobrasil.Model.Municipio;

public class TabMunicipioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private Municipio municipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_municipio);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        assert tabLayout != null;
        tabLayout.addTab(tabLayout.newTab().setText("Dados"));
        tabLayout.addTab(tabLayout.newTab().setText("Localização"));
        //tabLayout.addTab(tabLayout.newTab().setText("Opiniões"));

        this.viewPager = (ViewPager) findViewById(R.id.container);
        this.municipio = getIntent().getParcelableExtra("municipio");

        final PagerAdapter adapter = new TabMunicipioAdapter(getSupportFragmentManager(),
                this.tabLayout.getTabCount(),municipio);

        //this.municipio = getIntent().getParcelableExtra("municipio");
        getSupportActionBar().setTitle(municipio.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
