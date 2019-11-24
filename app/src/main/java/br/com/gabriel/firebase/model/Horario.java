package br.com.gabriel.firebase.model;

import android.os.Parcel;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.sql.Time;

public class Horario  implements Serializable {

    private String local;
    private String horario;

    public Horario(String local, String horario) {
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @NonNull
    @Override
    public String toString() {
        return "Horario: " + horario + "\nLocal: " + local;
    }
}


