package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

import br.com.gabriel.firebase.model.Paciente;

public class AlterarPaciente extends AppCompatActivity {

    private EditText alterEmail, alterNasc, alterSenhaAnt, alterSenhaNova, alterSenhaNovaRep;
    private Button alter;
    private Paciente p;
    private boolean g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_paciente);
        Bundle bundle = getIntent().getExtras();
        g = bundle.getBoolean("Google");
        p = (Paciente) getIntent().getSerializableExtra("Paciente");
        iniciaComponentes();
        setInfo(p);


        alter.setOnClickListener(v -> pegarDados());
    }

    private void pegarDados() {
        p.setEmail(alterEmail.getText().toString());
        p.setNascimento(alterNasc.getText().toString());
        if(alterEmail.length() == 0){
         alterEmail.requestFocus();
         alert("Email não pode estar vazio");
        }else if ((alterSenhaAnt.getText().toString().equals(p.getSenha())) || g) {
            if (alterSenhaNova.getText().toString().equals( alterSenhaNovaRep.getText().toString())) {
                p.setSenha(alterSenhaNovaRep.getText().toString());
                enviaApi();
                finish();
            } else {
                alterSenhaNova.requestFocus();
                alert("A senhas não são iguais");
            }
        } else {
            alterSenhaAnt.requestFocus();
            alert("Senha Incorreta");
        }
    }

    private void setInfo(Paciente p) {
        alterEmail.setText(p.getEmail());
        alterNasc.setText(p.getNascimento());
    }

    private void iniciaComponentes() {
        alterEmail = findViewById(R.id.alterEmail);
        alterNasc = findViewById(R.id.alterNasc);
        alterSenhaAnt = findViewById(R.id.alterSenhaAnt);
        alterSenhaNova = findViewById(R.id.alterSenhaNova);
        alterSenhaNovaRep = findViewById(R.id.alterSenhaNovaRep);
        alter = findViewById(R.id.Alter);
        if(g) {
            alterSenhaAnt.setVisibility(View.GONE);
            alterSenhaNova.setVisibility(View.GONE);
            alterSenhaNovaRep.setVisibility(View.GONE);
        }
    }

    private void enviaApi() {
        String url = getString(R.string.web_service_url) + "/patient/" + p.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                p.json(),
                (resultado) -> {
                    alert(resultado.toString());
                },
                (excecao) -> {
                    alert(excecao.networkResponse.statusCode+"");
                    excecao.printStackTrace();
                }
        );
        requestQueue.add(req);
    }

    public void alert(String p) {
        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
    }
}