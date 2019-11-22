package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gabriel.firebase.model.Consulta;
import br.com.gabriel.firebase.model.Horario;

public class MarcarConsulta extends AppCompatActivity {

    private TextView txtMcData, txtMcEsp,txtMcNutri;
    private ImageView McFoto;
    private Consulta consulta;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_consulta);
        try {
            consulta = (Consulta) getIntent().getSerializableExtra("Consultas");
            assert consulta != null;
            Toast.makeText(this, consulta.getNutricionista().getNome(), Toast.LENGTH_LONG).show();
            iniciarComponentes();
            setarValores();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void setarValores() {
        McFoto.setImageResource(consulta.getNutricionista().getFoto());
        txtMcNutri.setText(txtMcNutri.getText() +":"+ consulta.getNutricionista().getNome());
        txtMcEsp.setText(txtMcEsp.getText() + ":"+consulta.getNutricionista().getEspecialiacao());
        txtMcData.setText(consulta.getData().toString());

        ArrayAdapter<Horario> adapter = new ArrayAdapter<Horario>(this, android.R.layout.simple_list_item_1, consulta.getHorarios());
        listView.setAdapter(adapter);
    }

    public void iniciarComponentes(){
        McFoto = findViewById(R.id.McFoto);
        txtMcNutri = findViewById(R.id.txtMcNutri);
        txtMcEsp = findViewById(R.id.txtMcEsp);
        txtMcData = findViewById(R.id.txtMcData);
        listView = findViewById(R.id.marcarconsulta);
    }
}


