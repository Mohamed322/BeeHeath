package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gabriel.firebase.model.Consulta;
import br.com.gabriel.firebase.model.ConsultaMarcada;
import br.com.gabriel.firebase.model.Horario;
import br.com.gabriel.firebase.model.Usuario;

public class MarcarConsulta extends AppCompatActivity {

    private TextView txtMcData, txtMcEsp, txtMcNutri;
    private ImageView McFoto;
    private Consulta consulta;
    private ListView listView;
    private String result;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        result = sharedPreferences.getString("id", "");
        setContentView(R.layout.activity_marcar_consulta);
        try {
            consulta = (Consulta) getIntent().getSerializableExtra("Consultas");
            assert consulta != null;
            iniciarComponentes();
            setarValores();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void setarValores() {
        McFoto.setImageResource(consulta.getNutricionista().getFoto());
        txtMcNutri.setText(txtMcNutri.getText() + ":" + consulta.getNutricionista().getNome());
        txtMcEsp.setText(txtMcEsp.getText() + ":" + consulta.getNutricionista().getSpecialization());
        txtMcData.setText(consulta.getData().toString());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //Seu codigo aqui
            Horario h = consulta.getHorarios().get(position);
            ConsultaMarcada c = new ConsultaMarcada(consulta.getData(),consulta.getNutricionista().getId(),
                    h.getLocal(),h.getHorario().toString(),Integer.parseInt(result));
            Toast.makeText(this,consulta.getNutricionista().getId() + "",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ConsultaM.class);
            i.putExtra("Consulta",c);
            startActivity(i);
        });
        ArrayAdapter<Horario> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consulta.getHorarios());
        listView.setAdapter(adapter);
    }

    public void iniciarComponentes() {
        McFoto = findViewById(R.id.McFoto);
        txtMcNutri = findViewById(R.id.txtMcNutri);
        txtMcEsp = findViewById(R.id.txtMcEsp);
        txtMcData = findViewById(R.id.txtMcData);
        listView = findViewById(R.id.marcarconsulta);
    }
}


