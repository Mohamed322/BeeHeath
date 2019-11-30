package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Nutricionista extends Usuario implements Serializable {

    private int crn;
    private String specialization;


    public Nutricionista(String nome,String email, String senha, String nascimento,  int crn,
                         String specialization) {
        super(nome,email,senha,"nutritionist", nascimento);
        this.crn = crn;
        this.specialization = specialization;
    }

    public Nutricionista(int IdUser,String nome,String email, String senha, String nascimento,  int crn,
                         String specialization){
        super(IdUser,nome,email,senha,"Nutritionist", nascimento);
        this.crn = crn;
        this.specialization = specialization;
    }

    public JSONObject array() {
        JSONObject array = new JSONObject();
        try {
            array.put("fullname", getNome());
            array.put("email", getEmail());
            array.put("password", getSenha());
            array.put("type", getTipo());
            array.put("specialization", getSpecialization());
            array.put("crn", getCrn());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}
