package br.ufc.dc.dspm.balancobrasil.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;
import br.ufc.dc.dspm.balancobrasil.TabMunicipioActivity;

/**
 * Created by thiagoripardo on 06/07/16.
 */
public class MunicipioAdapter extends RecyclerView.Adapter<MunicipioAdapter.ViewHolder> {

    private List<Municipio> listNames;
    private Resources resources;
    private Context context;

    public MunicipioAdapter(List<Municipio> listNames) {

        this.listNames = listNames;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            resources = itemView.getResources();
            context = itemView.getContext();
        }
    }


    public class ItensViewHolder extends ViewHolder {
        public TextView name;
        //public CircleImageView pedidoImg;


        public ItensViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.itemName);

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.municipio_card, parent, false);

        return new ItensViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        ItensViewHolder holder = (ItensViewHolder) viewHolder;

        holder.name.setText(listNames.get(position).getName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addName(listNames.get(position).getName(), listNames.get(position).getLatitude(), listNames.get(position).getLongitude());
                Intent intent = new Intent(context, TabMunicipioActivity.class);
                intent.putExtra("municipio", listNames.get(position));
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {

        return (listNames.size());
    }

    public void addName(String nome, double latitude, double longitude) {

        String URL = "content://br.ufc.dc.dspm.provider.Counties/informations";

        Uri counties = Uri.parse(URL);
        ContentValues values = new ContentValues();

        Cursor c = context.getContentResolver().query(counties, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                /*Toast.makeText(this,
                        c.getString(c.getColumnIndex(InformationsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.NAME)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.LATITUDE)) +
                                ", " + c.getString(c.getColumnIndex( InformationsProvider.LONGITUDE)),
                        Toast.LENGTH_SHORT).show();
                c.getDouble();*/

                if(nome.equals(c.getString(c.getColumnIndex( InformationsProvider.NAME)))){
                    return;
                }

            } while (c.moveToNext());
        }

        values.put(InformationsProvider.NAME, nome.toString());

        values.put(InformationsProvider.LATITUDE, latitude);

        values.put(InformationsProvider.LONGITUDE, longitude);

        Uri uri = context.getContentResolver().insert(
                InformationsProvider.CONTENT_URI, values);

        Toast.makeText(context,
                uri.toString(), Toast.LENGTH_LONG).show();
    }
}