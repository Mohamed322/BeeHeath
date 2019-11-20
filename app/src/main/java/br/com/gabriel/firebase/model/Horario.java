package br.com.gabriel.firebase.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.sql.Time;

public class Horario  implements Parcelable {

    private String local;
    private Time horario;

    public Horario(String local, Time horario) {
        this.local = local;
        this.horario = horario;
    }


    protected Horario(Parcel in) {
        local = in.readString();
    }

    public static final Creator<Horario> CREATOR = new Creator<Horario>() {
        @Override
        public Horario createFromParcel(Parcel in) {
            return new Horario(in);
        }

        @Override
        public Horario[] newArray(int size) {
            return new Horario[size];
        }
    };

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    @NonNull
    @Override
    public String toString() {
        return "Horario: "+ horario + "\nLocal: " + local;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(local);
    }
}
