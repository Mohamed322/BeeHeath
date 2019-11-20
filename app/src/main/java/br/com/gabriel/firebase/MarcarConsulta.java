package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import br.com.gabriel.firebase.model.ConsultaModel;
import br.com.gabriel.firebase.model.Horario;

public class MarcarConsulta extends AppCompatActivity {

    private TextView txtMcData, txtMcEsp,txtMcNutri;
    private ImageView McFoto;
    private ConsultaModel consultaModel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_consulta);
        try {
            consultaModel = (ConsultaModel) getIntent().getParcelableExtra("Consulta");
            assert consultaModel != null;
            Toast.makeText(this, consultaModel.getNutricionista().getNome(), Toast.LENGTH_LONG).show();
            iniciarComponentes();
            setarValores();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void setarValores() {
        McFoto.setImageResource(consultaModel.getNutricionista().getFoto());
        txtMcNutri.setText(txtMcNutri.getText() + consultaModel.getNutricionista().getNome());
        txtMcEsp.setText(txtMcEsp.getText() + consultaModel.getNutricionista().getEspecialiacao());
        txtMcData.setText(txtMcEsp.getText() + consultaModel.getData().toString());
        ArrayAdapter<Horario> adapter = new ArrayAdapter<Horario>(this, android.R.layout.simple_list_item_1, consultaModel.getHorarios());
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


