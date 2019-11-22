package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Paciente extends Usuario implements Serializable {
    public Paciente(String nome, String email, String senha, String tipo, Date nascimento) {
        super(nome, email, senha, tipo, nascimento);
    }

    public JSONObject json(){
        JSONObject json = new JSONObject();
        try {
            json.put("fullname", super.getNome());
            json.put("email", super.getEmail());
            json.put("password", super.getSenha());
            json.put("type", super.getTipo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
