package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.gabriel.firebase.Adapter.ConsutaAdapter;
import br.com.gabriel.firebase.model.Consulta;
import br.com.gabriel.firebase.model.Horario;
import br.com.gabriel.firebase.model.Nutricionista;

public class Consultas extends AppCompatActivity {

    private RecyclerView lstDados;
    private Nutricionista n;
    private ConsutaAdapter consutaAdapter;
    private List<Nutricionista> dadosNutri;
    private List<Consulta> dadosConsulta;
    private ImageView consFoto;
    private TextView consNome, consEsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        iniciarComponentes();
        setInfo();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);
        dadosConsulta = todasConsultas();
        consutaAdapter = new ConsutaAdapter(dadosConsulta);
        lstDados.setAdapter(consutaAdapter);
    }

    private void setInfo() {
        Intent intent = getIntent();
        n = (Nutricionista) intent.getSerializableExtra("Nutri");
        consFoto.setImageResource(n.getFoto());
        consNome.setText(n.getNome());
        consEsp.setText(n.getEspecialiacao());
    }

    private void iniciarComponentes() {
        consFoto  = findViewById(R.id.consFoto);
        consEsp = findViewById(R.id.consEsp);
        consNome = findViewById(R.id.consNome);
        lstDados = findViewById(R.id.consList);
    }

    private List<Consulta> todasConsultas() {
        return new ArrayList<Consulta>(Arrays.asList(
                new Consulta(todosHorarios(), new Date("12/04/2019"),n),
                new Consulta(todosHorarios(), new Date("09/08/2019"),n),
                new Consulta(todosHorarios(), new Date("01/01/2019"),n),
                new Consulta(todosHorarios(), new Date("12/02/2019"),n)
        ));
    }


    private List<Horario> todosHorarios(){
        Random r = new Random();
        return new ArrayList<>(Arrays.asList(
                new Horario("Rua Joaquim Moraes", new Time(r.nextInt())),
                new Horario("Rua Vital Brasil", new Time(r.nextInt())),
                new Horario("Rua dos Bobos", new Time(r.nextInt()))
        ));
    }

}
