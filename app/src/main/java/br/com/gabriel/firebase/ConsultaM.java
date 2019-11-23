package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import br.com.gabriel.firebase.model.ConsultaMarcada;

public class ConsultaM extends AppCompatActivity {
    RequestQueue requestQueue;
    ConsultaMarcada consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_marcada);
        requestQueue = Volley.newRequestQueue(this);

        consulta = (ConsultaMarcada) getIntent().getSerializableExtra("Consulta");
        Toast.makeText(this,consulta.getLocal(),Toast.LENGTH_SHORT).show();

        Button b = findViewById(R.id.ConsultaMarcar);
        b.setOnClickListener(v -> enviaApi(consulta));
    }

    public void enviaApi(ConsultaMarcada consulta) {
        String url = getString(R.string.web_service_url)+"/consult/register/";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                consulta.array(),
                (resultado) -> {
                    finish();
                    Toast.makeText(this,"Consulta Marcada \n" + resultado,Toast.LENGTH_SHORT).show();
                },
                (excecao) ->{

                    Toast.makeText(
                            this,
                            getString(R.string.connect_error) + consulta.array().toString(),
                            Toast.LENGTH_SHORT
                    ).show();
                    excecao.printStackTrace();
                }
        );
        requestQueue.add(req);
    }
}
