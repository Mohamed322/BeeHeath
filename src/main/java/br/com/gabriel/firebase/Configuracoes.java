package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.gabriel.firebase.model.Paciente;

public class Configuracoes extends AppCompatActivity {

    private TextView perfilNome,perfilEmail;
    private Button alterarPerfil,singout;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        paciente = (Paciente) getIntent().getSerializableExtra("Paciente");
        iniciarComponentes();
        setInfo();
        singout.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        alterarPerfil.setOnClickListener(v -> {
            Intent i = new Intent(this,AlterarPaciente.class);
            i.putExtra("Paciente",paciente);
            startActivity(i);
        });
    }

    private void setInfo() {
        perfilNome.setText(paciente.getNome());
        perfilEmail.setText(paciente.getEmail());
    }

    private void iniciarComponentes() {
        perfilNome = findViewById(R.id.PerfilNome);
        perfilEmail = findViewById(R.id.PerfilEmail);
        alterarPerfil = findViewById(R.id.alterPerfil);
        singout = findViewById(R.id.singout);
    }

}
