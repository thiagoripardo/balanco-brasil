package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.ufc.dc.dspm.balancobrasil.Adapters.MunicipioAdapter;
import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.Query;
import br.ufc.dc.dspm.balancobrasil.R;

public class PointsFragment extends Fragment {

    //Context context = this;
    private ArrayList<String> arrayList;
    private Query processingQuery = new Query();
    private br.ufc.dc.dspm.balancobrasil.WebService.Query query =new br.ufc.dc.dspm.balancobrasil.WebService.Query();
    private FloatingActionButton fab;
    private Municipio municipio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            municipio = getArguments().getParcelable("municipio");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view;

        view = inflater.inflate(R.layout.fragment_points, container, false);

        query.setCaps(municipio.getName());
        query.setTipo("1");
        query.setComecarDe(0);
        query.setTamanhoBusca(10);
        processingQuery.query(getActivity(),query,view);

        /*query.setCaps("AMONTADA");
        query.setTipo("1");
        //query.setSetor();
        query.setTamanhoBusca(100);
        //query.setFonteFinalidade("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        query.setComecarDe(2);

        arrayList = new ArrayList<String>();

        fab = (FloatingActionButton)view.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processingQuery.query(query);
                arrayList = processingQuery.getList();
                //Snackbar.make(view,arrayList.get(0),Snackbar.LENGTH_SHORT).show();
            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                arrayList );

        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);*/

        return view;
    }

    /*public void next(View view){
        query.setCaps("AMONTADA");
        query.setTipo("1");
        //query.setFonteFinalidade("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        query.setTamanhoBusca(100);
        query.setComecarDe(0+query.getComecarDe());
        processingQuery.query(query);
    }*/
}
