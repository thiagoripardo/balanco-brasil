package br.ufc.dc.dspm.balancobrasil.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vasco on 05/07/2016.
 */
public class Municipio implements Parcelable {

    private String name;
    private double latitude;
    private double longitude;

    public Municipio(String name, double latitude, double longitude, String valorRecebido) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.valorRecebido = valorRecebido;
    }

    public String getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(String valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    private String valorRecebido;

    public Municipio(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Municipio(Parcel p){
        this.name = p.readString();;
        this.latitude = p.readDouble();
        this.longitude = p.readDouble();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*@Override
    public String toString() {
        return "Municipio{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeDouble(getLatitude());
        parcel.writeDouble(getLongitude());
    }

    public static final Creator<Municipio> CREATOR = new Creator<Municipio>() {

        @Override
        public Municipio createFromParcel(Parcel source) {

            return new Municipio(source);
        }

        @Override
        public Municipio[] newArray(int size) {

            return new Municipio[size];
        }
    };
}
