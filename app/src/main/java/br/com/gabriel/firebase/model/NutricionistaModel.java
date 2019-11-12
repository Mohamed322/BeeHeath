package br.com.gabriel.firebase.model;

import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;

public class NutricionistaModel implements Serializable {

    private String id;
    private int foto;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;
    private String especialiacao;


    public NutricionistaModel(String id, int foto, String nome, String endereco,
                              String email, String telefone, String especialiacao) {

        this.id = id;
        this.foto = foto;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.especialiacao = especialiacao;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialiacao() {
        return especialiacao;
    }

    public void setEspecialiacao(String especialiacao) {
        this.especialiacao = especialiacao;
    }
}
