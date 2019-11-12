package br.com.gabriel.firebase.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaModel implements Serializable {

    private List<Horario> horarios;
    private Date data;

    public ConsultaModel(List<Horario> horarios, Date data) {
        this.horarios = horarios;
        this.data = data;
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

}
