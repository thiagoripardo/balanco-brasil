package br.ufc.dc.dspm.balancobrasil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.ufc.dc.dspm.balancobrasil.Pojo.Consulta;
import br.ufc.dc.dspm.balancobrasil.WebService.ApiEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramon on 03/07/2016.
 */
public class Query {

    private ApiEndpointInterface authApi = ApiEndpointInterface.retrofit.create(ApiEndpointInterface.class);
    private List<List<String>> result;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();
//    Esse metodo procura por municipio e retorna os dados:
    /*Nome Funcao
    *Nome Programa
    *Nome Acao
    *Nome Favorecido
    *Fonte-Finalidade
    * Valor Parcela
    * Mes
    * nessa ordem
    * */

    public void query(final Context context, final br.ufc.dc.dspm.balancobrasil.WebService.Query query, final View view){
        try {
            Call<List<List<String>>> call = authApi.getQuery(query);
            call.enqueue(new Callback<List<List<String>>>() {
                ArrayList<String> values = new ArrayList<String>();
                @Override
                public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                    result = response.body();
                    String fonteFinalidade = "";
                    String setor = "";


                    //list = values;
                    /*adapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_list_item_1,values );
                    ((ListActivity)context).setListAdapter(adapter);*/
                    /*ArrayList<String> arrayList = new ArrayList<String>();

                    for(int i=0;i<10;i++){
                        arrayList.add("Teste");
                    }*/


                    if(view==null){
                        for(int i =0;i<result.size();i++){


                        if(Double.parseDouble(result.get(i).get(5))>query.getValor()){

                            if(result.get(i).get(4).substring(29,33).equals("pios")){
                                fonteFinalidade = "STN - Transferencias a Municipios";

                            }else if(result.get(i).get(4).substring(0,10).equals("STN - Conv")){
                                fonteFinalidade = "STN - Convenios/Contratos de Repasses/Fundo a Fundo/Outros";

                            }else if(result.get(i).get(4).substring(26,30).equals("dos")){
                                fonteFinalidade = "STN - Transferencias a Estados";

                            }


                            if(result.get(i).get(1).substring(0,3).equals("Seg")){
                                setor = "Seguranca Publica";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ass")){
                                setor = "Assistencia Social";
                            } else if(result.get(i).get(1).substring(0,3).equals("Edu")){
                                setor = "Educacao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Adm")){
                                setor = "Administracao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Rel")){
                                setor = "Relacoes Exteriores";
                            } else if(result.get(i).get(1).substring(0,2).equals("Sa")){
                                setor = "Saude";
                            } else if(result.get(i).get(1).substring(0,3).equals("Pre")){
                                setor = "Previdencia Social";
                            } else if(result.get(i).get(1).substring(0,2).equals("Ci")){
                                setor = "Ciencia e Tecnologia";
                            } else if(result.get(i).get(1).substring(0,3).equals("Com")){
                                setor = "Comercio e Servicos";
                            } else if(result.get(i).get(1).substring(0,3).equals("Org")){
                                setor = "Organizacao Agraria";
                            } else if(result.get(i).get(1).substring(0,3).equals("San")) {
                                setor = "Saneamento";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ges")){
                                setor = "Gestao Ambiental";
                            } else if(result.get(i).get(1).substring(0,4).equals("Comu")){
                                setor = "Comunicacoes";
                            } else if(result.get(i).get(1).substring(0,3).equals("Hab")){
                                setor = "Habitacao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ind")){
                                setor = "Industria";
                            } else if(result.get(i).get(1).substring(0,3).equals("Jud")){
                                setor = "Judiciaria";
                            }

                            if(query.getSetor().equals(setor)){
                                if(query.getFonteFinalidade().equals(fonteFinalidade)&&query.getSetor().equals(setor)){
                                    Consulta consulta = new Consulta(setor,"","",result.get(i).get(3),fonteFinalidade,result.get(i).get(5),result.get(i).get(6));
                                    values.add(consulta.toString());

                                }
                            }

                        }


                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_list_item_1,
                                values );


                        ListView listView = (ListView) ((Activity)context).findViewById(R.id.list);
                        listView.setAdapter(arrayAdapter);
                        Toast.makeText(context,"Finalizado",Toast.LENGTH_LONG).show();
                    }
                    else{
                        for(int i =0;i<result.size();i++){
                            if(result.get(i).get(4).substring(29,33).equals("pios")){
                                fonteFinalidade = "STN - Transferencias a Municipios";

                            }else if(result.get(i).get(4).substring(0,10).equals("STN - Conv")){
                                fonteFinalidade = "STN - Convenios/Contratos de Repasses/Fundo a Fundo/Outros";

                            }else if(result.get(i).get(4).substring(26,30).equals("dos")){
                                fonteFinalidade = "STN - Transferencias a Estados";

                            }


                            if(result.get(i).get(1).substring(0,3).equals("Seg")){
                                setor = "Seguranca Publica";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ass")){
                                setor = "Assistencia Social";
                            } else if(result.get(i).get(1).substring(0,3).equals("Edu")){
                                setor = "Educacao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Adm")){
                                setor = "Administracao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Rel")){
                                setor = "Relacoes Exteriores";
                            } else if(result.get(i).get(1).substring(0,2).equals("Sa")){
                                setor = "Saude";
                            } else if(result.get(i).get(1).substring(0,3).equals("Pre")){
                                setor = "Previdencia Social";
                            } else if(result.get(i).get(1).substring(0,2).equals("Ci")){
                                setor = "Ciencia e Tecnologia";
                            } else if(result.get(i).get(1).substring(0,3).equals("Com")){
                                setor = "Comercio e Servicos";
                            } else if(result.get(i).get(1).substring(0,3).equals("Org")){
                                setor = "Organizacao Agraria";
                            } else if(result.get(i).get(1).substring(0,3).equals("San")) {
                                setor = "Saneamento";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ges")){
                                setor = "Gestao Ambiental";
                            } else if(result.get(i).get(1).substring(0,4).equals("Comu")){
                                setor = "Comunicacoes";
                            } else if(result.get(i).get(1).substring(0,3).equals("Hab")){
                                setor = "Habitacao";
                            } else if(result.get(i).get(1).substring(0,3).equals("Ind")){
                                setor = "Industria";
                            } else if(result.get(i).get(1).substring(0,3).equals("Jud")){
                                setor = "Judiciaria";
                            }
                            Consulta consulta = new Consulta(setor,"","",result.get(i).get(3),fonteFinalidade,result.get(i).get(5),result.get(i).get(6));
                            values.add(consulta.toString());

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_list_item_1,
                                values );

                        ListView listView = (ListView) view.findViewById(R.id.list);
                        listView.setAdapter(arrayAdapter);
                    }


                }

                @Override
                public void onFailure(Call<List<List<String>>> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayAdapter<String> getAdapter(){
        return adapter;
    }

    public ArrayList<String> getList(){
        return list;
    }
}
