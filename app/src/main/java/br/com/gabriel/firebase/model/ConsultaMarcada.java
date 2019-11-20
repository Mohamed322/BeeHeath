package br.com.gabriel.firebase.model;

import java.io.Serializable;
import java.util.Date;

public class ConsultaMarcada implements Serializable {
    private Date data;
    private String nutricionista;
    private String Local;
    private String horario;

    public String getData() {
        return data.toGMTString();
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(String nutricionista) {
        this.nutricionista = nutricionista;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ConsultaMarcada(Date data, String nutricionista, String local, String horario) {
        this.data = data;
        this.nutricionista = nutricionista;
        Local = local;
        this.horario = horario;
    }
}
