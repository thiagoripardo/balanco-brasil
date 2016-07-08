package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.ufc.dc.dspm.balancobrasil.Adapters.InformationsProvider;
import br.ufc.dc.dspm.balancobrasil.Adapters.MunicipioAdapter;
import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;

public class SetorFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SetorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setor, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.municipios_recyclerview2);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Povoamento
        ArrayList<Municipio> municipios;

        municipios = getMunicipios();

        /*ArrayList<Municipio> listMunicipios= new ArrayList<>();

        for(int i=0;i<15;i++){
            listMunicipios.add(new Municipio("Teste", -1, 2));
        }*/

        adapter = new MunicipioAdapter(municipios);
        recyclerView.setAdapter(adapter);

        return view;
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

                Municipio municipio = new Municipio(nomeMunicipio, latitudeMunicipio, longitudeMunicipio);

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

    public ArrayList<Municipio> getMunicipios() {

        // Retrieve student records
        String URL = "content://br.ufc.dc.dspm.provider.Counties/informations";

        Uri counties = Uri.parse(URL);
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();
        //Cursor c = managedQuery(counties, null, null, null, "name");
        Cursor c = getActivity().getContentResolver().query(counties, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                /*Toast.makeText(this,
                        c.getString(c.getColumnIndex(InformationsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.NAME)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.LATITUDE)) +
                                ", " + c.getString(c.getColumnIndex( InformationsProvider.LONGITUDE)),
                        Toast.LENGTH_SHORT).show();
                c.getDouble();*/
                Municipio municipio = new Municipio(c.getString(c.getColumnIndex( InformationsProvider.NAME)),
                        c.getDouble(c.getColumnIndex( InformationsProvider.LATITUDE)), c.getDouble(c.getColumnIndex( InformationsProvider.LONGITUDE)));

                municipios.add(municipio);
            } while (c.moveToNext());
        }

        return municipios;
    }
}