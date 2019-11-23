package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultaMarcada implements Serializable {
    private Date data;
    private Nutricionista Nutricionist;
    private int idPaciente;
    private String Local;
    private String horario;

    public Nutricionista getNutricionist() {
        return Nutricionist;
    }

    public void setNutricionist(Nutricionista nutricionist) {
        Nutricionist = nutricionist;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }


    public String getData() {
        return data.toString();
    }

    public void setData(Date data) {
        this.data = data;
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

    public ConsultaMarcada(Date data, Nutricionista nutricionista, String local, String horario,int idPaciente) {
        this.data = data;
        this.Nutricionist = nutricionista;
        Local = local;
        this.horario = horario;
        this.idPaciente = idPaciente;
    }

    public String dataArray(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(Date.parse(getData()));
        return date + getHorario();
    }

    public JSONObject array(){
        JSONObject array = new JSONObject();
        try {
            array.put("date", dataArray());
            array.put("place",getLocal());
            array.put("idnutritionist",getNutricionist().getId());
            array.put("idpatient",getIdPaciente());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }
}
