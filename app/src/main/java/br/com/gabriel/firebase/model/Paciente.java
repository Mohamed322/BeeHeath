package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Paciente extends Usuario implements Serializable {

    private Double weight;
    private Double height;
    private String description;

    public Paciente(String nome, String email, String senha, String tipo, Date nascimento, Double weight, Double height, String description) {
        super(nome, email, senha, tipo, nascimento);
        this.weight = weight;
        this.height = height;
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Paciente(int id,String nome, String email, String senha, String tipo, Date nascimento) {
        super(id,nome, email, senha, tipo, nascimento);
    }

    public Paciente(String nome, String email, String senha, String tipo, Date nascimento) {
        super(nome, email, senha, tipo, nascimento);
    }

    public JSONObject json(){
        JSONObject json = new JSONObject();
        try {
            json.put("fullname", getNome());
            json.put("email", getEmail());
            json.put("password", getSenha());
            json.put("type", getTipo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
