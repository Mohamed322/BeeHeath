package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultaMarcada implements Serializable {
    private Date data;
    private int idNutricionist;
    private int idPaciente;
    private String Local;
    private String horario;

    public int getIdNutricionist() {
        return idNutricionist;
    }

    public void setIdNutricionist(int idNutricionist) {
        this.idNutricionist = idNutricionist;
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

    public int getNutricionista() {
        return idNutricionist;
    }

    public void setNutricionista(int nutricionista) {
        this.idNutricionist = nutricionista;
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

    public ConsultaMarcada(Date data, int nutricionista, String local, String horario,int idPaciente) {
        this.data = data;
        this.idNutricionist = nutricionista;
        Local = local;
        this.horario = horario;
        this.idPaciente = idPaciente;
    }

    public String dataArray(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(Date.parse(getData()));
        return date;
    }

    public JSONObject array(){
        JSONObject array = new JSONObject();
        try {
            array.put("date", dataArray());
            array.put("place",getLocal());
            array.put("idnutritionist",getIdNutricionist());
            array.put("idpatient",2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }
}
