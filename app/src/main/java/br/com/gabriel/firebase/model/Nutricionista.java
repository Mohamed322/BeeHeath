package br.com.gabriel.firebase.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Nutricionista extends Usuario implements Serializable {

    private int CRN;
    private String especialiacao;


    public Nutricionista(String nome,String email, String senha, Date nascimento,  int CRN,
                         String especialiacao) {
        super(nome,email,senha,"Nutritionist", nascimento);
        this.CRN = CRN;
        this.especialiacao = especialiacao;
    }

    public Nutricionista(int IdUser,String nome,String email, String senha, Date nascimento,  int CRN,
                         String especialiacao){
        super(IdUser,nome,email,senha,"Nutritionist", nascimento);
        this.CRN = CRN;
        this.especialiacao = especialiacao;
    }

    public JSONObject array() {
        JSONObject array = new JSONObject();
        try {
            array.put("fullname", getNome());
            array.put("email", getEmail());
            array.put("password", getSenha());
            array.put("type", getTipo());
            array.put("specialization",getEspecialiacao());
            array.put("crn",getCRN());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public void setEspecialiacao(String especialiacao) {
        this.especialiacao = especialiacao;
    }

    public String getEspecialiacao() {
        return especialiacao;
    }
}
