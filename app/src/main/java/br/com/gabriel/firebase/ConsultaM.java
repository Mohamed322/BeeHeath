package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import br.com.gabriel.firebase.model.ConsultaMarcada;

public class ConsultaM extends AppCompatActivity {

    private TextView ConsultaMarcarNutri, ConsultaMarcarData, ConsultaMarcarEsp, ConsultaMarcarLocal;
    private RequestQueue requestQueue;
    private ConsultaMarcada consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_marcada);
        iniciarCmponentes();
        consulta = (ConsultaMarcada) getIntent().getSerializableExtra("Consulta");
        setInfo();
        Button b = findViewById(R.id.ConsultaMarcar);
        b.setOnClickListener(v -> enviaApi(consulta));
    }

    private void setInfo() {
        ConsultaMarcarLocal.setText(getText(R.string.local) + ": " + consulta.getLocal());
        ConsultaMarcarEsp.setText(getText(R.string.Esp) + ": " + consulta.getNutricionist().getSpecialization());
        ConsultaMarcarData.setText(getText(R.string.Data) + " " + consulta.getData() + " " + consulta.getHorario());
        ConsultaMarcarNutri.setText(getText(R.string.NomeCompleto) + ": " + consulta.getNutricionist().getNome());
    }

    private void iniciarCmponentes() {
        ConsultaMarcarNutri = findViewById(R.id.ConsultaMarcarNutri);
        ConsultaMarcarData = findViewById(R.id.ConsultaMarcarData);
        ConsultaMarcarEsp = findViewById(R.id.ConsultaMarcarEsp);
        ConsultaMarcarLocal = findViewById(R.id.ConsultaMarcarLocal);
    }

    public void enviaApi(ConsultaMarcada consulta) {
        requestQueue = Volley.newRequestQueue(this);
        String url = getString(R.string.web_service_url) + "/consult/register/";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                consulta.array(),
                (resultado) -> {
                    finish();
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
}
