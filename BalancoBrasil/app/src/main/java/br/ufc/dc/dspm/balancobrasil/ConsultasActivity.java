package br.ufc.dc.dspm.balancobrasil;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.Query;
import br.ufc.dc.dspm.balancobrasil.R;
public class ConsultasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private EditText nameQuery;
    ArrayList<Municipio> municipiosQ;
    private Button save;
    private br.ufc.dc.dspm.balancobrasil.WebService.Query queryObject = new br.ufc.dc.dspm.balancobrasil.WebService.Query();
    private br.ufc.dc.dspm.balancobrasil.Query query = new br.ufc.dc.dspm.balancobrasil.Query();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        getSupportActionBar().setTitle("Consultas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*nameQuery = (EditText) findViewById(R.id.name_query);

        nameQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //some_button.performClick();
                    return true;
                }
                return false;
            }
        });*/
        createSpinners();
        readFile();

        Button fab = (Button) findViewById(R.id.fabc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(queryObject.getFonteFinalidade().equals("Nenhum")&&queryObject.getSetor().equals("Nenhum")&&queryObject.getCaps().equals("Nenhum")&&queryObject.getValor()==0){

                        Toast.makeText(ConsultasActivity.this, "Por favor não deixe nenhum campo com 'Nenhum' ",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        queryObject.setTipo("1");
                        queryObject.setComecarDe(0);
                        queryObject.setTamanhoBusca(10);
                        query.query(ConsultasActivity.this, queryObject, (View)null);
//                    query.consult(getActivity(), queryObject);
                    }
                }catch(NullPointerException e){
                    Toast.makeText(ConsultasActivity.this, "Algum valor está em falta. Tente novamente", Toast.LENGTH_LONG).show();
                }

            }
        });

        //return view;
    }

    public void createSpinners(){



        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        municipios = readFile();
        List<String> categoriesMunicipio = new ArrayList<String>();

        categoriesMunicipio.add("Nenhum");
        for(int i=0 ; i< municipios.size();i++){
            categoriesMunicipio.add(municipios.get(i).getName());
        }


        //Spinner 1
        // Spinner element
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesMunicipio);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        /*******/




        //Spinner 2
        // Spinner element
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesSetor = new ArrayList<String>();
        categoriesSetor.add("Nenhum");
        //categoriesSetor.add("Seguran�a P�blica");
        categoriesSetor.add("Seguranca Publica");
        categoriesSetor.add("Direitos da Cidadania");
        //categoriesSetor.add("Assist�ncia Social");
        categoriesSetor.add("Assistencia Social");
        categoriesSetor.add("Encargos Especiais");
        categoriesSetor.add("Trabalho");
        //categoriesSetor.add("Educa��o");
        categoriesSetor.add("Educacao");
        //categoriesSetor.add("Administra��o");
        categoriesSetor.add("Administracao");
        //categoriesSetor.add("Rela��es Exteriores");
        categoriesSetor.add("Relacoes Exteriores");
        //categoriesSetor.add("Sa�de");
        categoriesSetor.add("Saude");
        //categoriesSetor.add("Previd�ncia Social");
        categoriesSetor.add("Previdencia Social");
        //categoriesSetor.add("Ci�ncia e Tecnologia");
        categoriesSetor.add("Ciencia e Tecnologia");
        //categoriesSetor.add("Com�rcio e Servi�os");
        categoriesSetor.add("Comercio e Servicos");
        categoriesSetor.add("Defesa Nacional");
        //categoriesSetor.add("Organiza��o Agr�ria");
        categoriesSetor.add("Organizacao Agraria");
        categoriesSetor.add("Saneamento");
        categoriesSetor.add("Urbanismo");
        categoriesSetor.add("Desporto e Lazer");
        categoriesSetor.add("Cultura");
        categoriesSetor.add("Agricultura");
        //categoriesSetor.add("Gest�o Ambiental");
        categoriesSetor.add("Gestao Ambiental");
        //categoriesSetor.add("Comunica��es");
        categoriesSetor.add("Comunicacoes");
        //categoriesSetor.add("Habita��o");
        categoriesSetor.add("Habitacao");
        //categoriesSetor.add("Ind�stria");
        categoriesSetor.add("Industria");
        categoriesSetor.add("Transporte");
        categoriesSetor.add("Energia");
        //categoriesSetor.add("Judici�ria");
        categoriesSetor.add("Judiciaria");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesSetor);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);



        //Spinner 3
        // Spinner element
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        // Spinner click listener
        spinner3.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesFonteFinalidade = new ArrayList<String>();
        categoriesFonteFinalidade.add("Nenhum");
        //categoriesFonteFinalidade.add("STN - Transfer�ncias a Munic�pios");
        categoriesFonteFinalidade.add("STN - Transferencias a Municipios");
        //categoriesFonteFinalidade.add("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        categoriesFonteFinalidade.add("STN - Convenios/Contratos de Repasses/Fundo a Fundo/Outros");
        //categoriesFonteFinalidade.add("STN - Transfer�ncias a Estados");
        categoriesFonteFinalidade.add("STN - Transferencias a Estados");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesFonteFinalidade);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter3);


        //Spinner 4
        // Spinner element
        spinner4 = (Spinner) findViewById(R.id.spinner4);

        // Spinner click listener
        spinner4.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesValor = new ArrayList<String>();
        categoriesValor.add("Nenhum");
        categoriesValor.add(">100");
        categoriesValor.add(">1000");
        categoriesValor.add(">10000");
        categoriesValor.add(">100000");
        categoriesValor.add(">1000000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesValor);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner4.setAdapter(dataAdapter4);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Nenhum")){

        }
        else{
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner1")){
                queryObject.setCaps(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner2")){
                queryObject.setSetor(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner3")){
                queryObject.setFonteFinalidade(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner4")){
                if(item.equals("Nenhum")){
                    queryObject.setValor(0);
                }
                else
                    queryObject.setValor(Integer.parseInt(item.substring(1,item.length()-1)));
            }
        }


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    /*public void saveQuery(View v){
        Toast.makeText(getActivity(),"Salvo", Toast.LENGTH_SHORT).show();
    }*/
    public ArrayList<Municipio> readFile() {

        /*
            Dados dos municipios encontrados em : http://www.mbi.com.br/mbi/biblioteca/utilidades/dddcepce/
         */

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;

        String linha;
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        String nomeMunicipio;
        String nomeMunicipioSG;
        double latitudeMunicipio;
        double longitudeMunicipio;
        String valoreRecebido;

        try {
            inputStream = assetManager.open("municipiosCeara.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((nomeMunicipio = bufferedReader.readLine()) != null) {
                latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                valoreRecebido = bufferedReader.readLine();

                Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio,valoreRecebido);

                municipios.add(municipio);
            }
            inputStream.close();
            System.out.println(municipios.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Errou", Toast.LENGTH_SHORT).show();
        }

        return municipios;
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
