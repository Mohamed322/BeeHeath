package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MarcarConsulta extends AppCompatActivity {

    private TextView txtMcData, txtMcLocal, txtMcEsp,txtMcNutri;
    private ImageView McFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_consulta);
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        McFoto = findViewById(R.id.McFoto);
        txtMcNutri = findViewById(R.id.txtMcNutri);
        txtMcEsp = findViewById(R.id.txtMcEsp);
        txtMcData = findViewById(R.id.txtMcData);
        txtMcLocal = findViewById(R.id.txtMcLocal);
    }
}


