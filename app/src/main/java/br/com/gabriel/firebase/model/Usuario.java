package br.com.gabriel.firebase.model;

import android.media.Image;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Usuario implements Parcelable {

    private String id;
    private Uri foto;
    private String nome;
    private String email;
    private String senha;
    private String tipo;

    public Usuario() {
    }

    public Usuario(Uri foto, String nome, String email, String senha, String tipo) {
        this.foto = foto;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    protected Usuario(Parcel in) {
        id = in.readString();
        foto = in.readParcelable(Uri.class.getClassLoader());
        nome = in.readString();
        email = in.readString();
        senha = in.readString();
        tipo = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Uri getFoto() {
        return foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(foto, flags);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeString(tipo);
    }

    public JSONObject array() {
        JSONObject array = new JSONObject();
        try {
            array.put("fullname", nome);
            array.put("email", email);
            array.put("password", senha);
            array.put("type", tipo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}
