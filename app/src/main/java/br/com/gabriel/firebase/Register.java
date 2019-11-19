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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.UUID;

import br.com.gabriel.firebase.model.Usuario;

public class Register extends AppCompatActivity {

    EditText nome, email, senha;
    RadioButton paciente, nutricionista;
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
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("Usuario",usuario);
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
        String url = getString(R.string.web_service_url) + "/user/register";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                user.array(),
                (resultado) ->{
                    Toast.makeText(this,"Sucesso\n"+resultado,Toast.LENGTH_LONG).show();
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