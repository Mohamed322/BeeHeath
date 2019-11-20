package br.com.gabriel.firebase.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ConsultaModel implements Parcelable {

    private List<Horario> horarios;
    private Date data;
    private NutricionistaModel nutricionista;



    public ConsultaModel(List<Horario> horarios, Date data, NutricionistaModel nutricionista) {
        this.horarios = horarios;
        this.data = data;
        this.nutricionista = nutricionista;
    }


    protected ConsultaModel(Parcel in) {
    }

    public static final Creator<ConsultaModel> CREATOR = new Creator<ConsultaModel>() {
        @Override
        public ConsultaModel createFromParcel(Parcel in) {
            return new ConsultaModel(in);
        }

        @Override
        public ConsultaModel[] newArray(int size) {
            return new ConsultaModel[size];
        }
    };

    public NutricionistaModel getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(NutricionistaModel nutricionista) {
        this.nutricionista = nutricionista;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getHorario(){
        return horarios.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
