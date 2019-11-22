package br.com.gabriel.firebase.model;

import android.os.Parcel;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.sql.Time;

public class Horario  implements Serializable {

    private String local;
    private Time horario;

    public Horario(String local, Time horario) {
        this.local = local;
        this.horario = horario;
    }


    protected Horario(Parcel in) {
        local = in.readString();
    }


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
        return "Horario: " + horario + "\nLocal: " + local;
    }
}


