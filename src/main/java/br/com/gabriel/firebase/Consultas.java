package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.xml.transform.Result;

import br.com.gabriel.firebase.Adapter.ConsutaAdapter;
import br.com.gabriel.firebase.model.Consulta;
import br.com.gabriel.firebase.model.ConsultaMarcada;
import br.com.gabriel.firebase.model.Horario;
import br.com.gabriel.firebase.model.Nutricionista;

public class Consultas extends AppCompatActivity {

    private RecyclerView lstDados;
    private Nutricionista n;
    private ConsutaAdapter consutaAdapter;
    private List<Consulta> dadosConsulta;
    private ImageView consFoto;
    private TextView consNome, consEsp,consEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Intent intent = getIntent();
        n = (Nutricionista) intent.getSerializableExtra("Nutri");
        iniciarComponentes();
        setInfo();
        iniciaRecyclerView(lstDados);

    }

    private void iniciaRecyclerView(RecyclerView lstDados) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);
        dadosConsulta = todasConsultas();
        consutaAdapter = new ConsutaAdapter(dadosConsulta);
        lstDados.setAdapter(consutaAdapter);
    }

    private void setInfo() {
        consFoto.setImageResource(n.getFoto());
        consNome.setText(n.getNome());
        consEsp.setText(n.getSpecialization());
        consEnd.setText(n.getLocal());
    }


    private void enviaApi() {
        String url = getString(R.string.web_service_url) + "/consult/nutritionist/" + n.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        JsonArrayRequest req = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                (resultado) -> {
                    ArrayList<String> dados = new ArrayList<>();
                    try {
                        JSONObject iesimo;
                        for (int i = 0; i < resultado.length(); i++) {
                            iesimo = resultado.getJSONObject(i);
                            String date = iesimo.getString("date");
                            dados.add(date);
                        }
                        comparar(dados);
                    } catch (JSONException e) {
                        Toast.makeText(this, "Erro na resposta", Toast.LENGTH_SHORT).show();
                    }

                },
                (excecao) -> {
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

    private void comparar(ArrayList<String> dados) {

    }

    private void iniciarComponentes() {
        consFoto  = findViewById(R.id.consFoto);
        consEsp = findViewById(R.id.consEsp);
        consNome = findViewById(R.id.consNome);
        lstDados = findViewById(R.id.consList);
        consEnd = findViewById(R.id.consEnd);
    }

    private List<Consulta> todasConsultas() {
        return new ArrayList<>(Arrays.asList(
                new Consulta(todosHorarios(), "2019/12/04",n),
                new Consulta(todosHorarios(), "2019/09/12",n),
                new Consulta(todosHorarios(), "2019/01/11",n),
                new Consulta(todosHorarios(), "2019/02/12",n)
        ));
    }


    private List<Horario> todosHorarios(){
        return new ArrayList<>(Arrays.asList(
                new Horario("08:00:00"),
                new Horario("10:00:00"),
                new Horario("12:00:00")
        ));
    }

}
