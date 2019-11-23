package br.com.gabriel.firebase.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Consulta implements Serializable {

    private List<Horario> horarios;
    private Date data;
    private Nutricionista nutricionista;

    public Consulta(List<Horario> horarios, Date data, Nutricionista nutricionista) {
        this.horarios = horarios;
        this.data = data;
        this.nutricionista = nutricionista;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
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

}
