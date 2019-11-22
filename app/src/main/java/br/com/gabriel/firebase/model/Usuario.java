package br.com.gabriel.firebase.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private int id;
    private int foto;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private Date nascimento;


    public Usuario(int Id, String nome, String email, String senha, String tipo, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.nascimento = nascimento;
    }

    public Usuario(String nome, String email, String senha, String tipo, Date nascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.nascimento = nascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getFoto() {
        return foto;
    }


    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
