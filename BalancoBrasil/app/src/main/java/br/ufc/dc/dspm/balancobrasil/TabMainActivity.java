package br.ufc.dc.dspm.balancobrasil;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.dc.dspm.balancobrasil.Adapters.TabMainActivityAdapter;
import br.ufc.dc.dspm.balancobrasil.Fragments.FeedFragment;
import br.ufc.dc.dspm.balancobrasil.Fragments.MapsFragment;
import br.ufc.dc.dspm.balancobrasil.MapsLocation.MapLocation;

public class TabMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Query query = new Query();
    Context context = this;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        assert tabLayout != null;
        tabLayout.addTab(tabLayout.newTab().setText("Municípios"));
        tabLayout.setSelectedTabIndicatorColor(4);
        tabLayout.addTab(tabLayout.newTab().setText("Recentes"));
        //tabLayout.addTab(tabLayout.newTab().setText("Opiniões"));

        viewPager = (ViewPager) findViewById(R.id.container);
        final PagerAdapter adapter = new TabMainActivityAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        getSupportActionBar().setTitle("Balanço Brasil");
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


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.query(3534l,context);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        //User
        TextView nameUser = (TextView) header.findViewById(R.id.nameUser);
        nameUser.setText("Balanço Brasil");

        TextView helloUser = (TextView) header.findViewById(R.id.helloUser);
        helloUser.setText("Olá, camarada!");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getFragmentManager();

        Fragment fragment = null;
        //Name toolbar
        String title = "BalancoBrasil";

        //Change for fragment is better
        if (id == R.id.nav_con) {
            //fragment = new MapsFragment();

            startActivity(new Intent(this, ConsultasActivity.class));
        } else if (id == R.id.nav_signout) {
            finish();
        }else if (id == R.id.nav_map) {

            startActivity(new Intent(this, MapLocation.class));

        }



        /*if (fragment != null) {
            fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /*public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        EditText name = (EditText) findViewById(R.id.name);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        values.put(InformationsProvider.NAME, name.getText().toString());

        values.put(InformationsProvider.LATITUDE, Double.parseDouble(latitude.getText().toString()));

        values.put(InformationsProvider.LONGITUDE, Double.parseDouble(longitude.getText().toString()));

        Uri uri = getContentResolver().insert(
                InformationsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // Retrieve student records
        String URL = "content://br.ufc.dc.dspm.provider.Counties/informations";

        Uri counties = Uri.parse(URL);
        //Cursor c = managedQuery(counties, null, null, null, "name");
        Cursor c = getContentResolver().query(counties, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(InformationsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.NAME)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.LATITUDE)) +
                                ", " + c.getString(c.getColumnIndex( InformationsProvider.LONGITUDE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }*/



}
