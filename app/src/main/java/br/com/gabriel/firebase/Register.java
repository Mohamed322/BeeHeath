package br.com.gabriel.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import br.com.gabriel.firebase.model.Banco;
import br.com.gabriel.firebase.model.Usuario;

public class Register extends AppCompatActivity {

    EditText nome, email, senha;
    RadioButton paciente, nutricionista;
    Banco banco = new Banco();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        requestQueue = Volley.newRequestQueue(this);
        nome = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        senha = findViewById(R.id.registerPassword);
        paciente = findViewById(R.id.checkPacient);
        nutricionista = findViewById(R.id.checkDoctor);
    }


    public void entrar(Usuario usuario) {
        banco.iniciarBanco(this);
        banco.getDatabaseReference().child("Usuario").child(usuario.getId()).setValue(usuario);
        Toast.makeText(this, "Inserido no Banco", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void Autenticar(View view) {
        Usuario usuario = new Usuario();
        if (nome.length() > 0) {
            usuario.setId(UUID.randomUUID().toString());
            usuario.setNome(nome.getText().toString());
            if (email.length() > 0) {
                usuario.setEmail(email.getText().toString());
                if (senha.length() > 0) {
                    usuario.setSenha(senha.getText().toString());
                    if (paciente.isChecked()) {
                        usuario.setTipo("Paciente");
                        enviaApi(usuario);

                    } else if (nutricionista.isChecked()) {
                        usuario.setTipo("Nutricinista");
                        enviaApi(usuario);
                    } else {
                        Toast.makeText(this, "Selecione o tipo de usuario", Toast.LENGTH_SHORT).show();
                        paciente.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "Digite uma senha", Toast.LENGTH_SHORT).show();
                    senha.requestFocus();
                }
            } else {
                Toast.makeText(this, "Digite seu email", Toast.LENGTH_SHORT).show();
                email.requestFocus();
            }
        } else {
            Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show();
            nome.requestFocus();
        }

    }

    public void enviaApi(Usuario user){
        JSONObject  array = new JSONObject ();
        try {
            array.put("fullname",user.getNome());
            array.put("email",user.getEmail());
            array.put("password",user.getSenha());
            array.put("type","patient");
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        String url = getString(R.string.web_service_url) + "/user/register";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                array,
                (resultado) ->{
                    Toast.makeText(this,"Sucesso",Toast.LENGTH_LONG).show();
                    entrar(user);
                },
                (excecao) ->{
                    Toast.makeText(
                            this,
                            getString(R.string.connect_error),
                            Toast.LENGTH_SHORT
                    ).show();
                    excecao.printStackTrace();
                }
        );
        requestQueue.add(req);
    }

}