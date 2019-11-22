package br.com.gabriel.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.gabriel.firebase.model.Nutricionista;
import br.com.gabriel.firebase.model.Paciente;
import br.com.gabriel.firebase.model.Usuario;

public class RegistraNutri extends AppCompatActivity {

    private EditText txtCRN,txtEsp;
    private RequestQueue requestQueue;
    private Nutricionista nutricionista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_nutri);
        Bundle bundle = getIntent().getExtras();
        nutricionista =(Nutricionista) bundle.getSerializable("Nutri");
        txtCRN = findViewById(R.id.registerCRN);
        txtEsp = findViewById(R.id.registerEsp);

    }

    public void registra(View view) {
        nutricionista.setCRN(Integer.parseInt(txtCRN.getText().toString()));
        nutricionista.setEspecialiacao(txtEsp.getText().toString());
        enviaApi(nutricionista);
    }

    public void entrar(Nutricionista nutricionista) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("Usuario",nutricionista);
        startActivity(intent);
    }

    public void enviaApi(Nutricionista nutricionista){
        String url = getString(
                R.string.web_service_url
        )+"/user/register/";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                nutricionista.array(),
                (resultado) ->{
                    Toast.makeText(this,"Sucesso\n"+resultado,Toast.LENGTH_LONG).show();
                },
                (excecao) ->{
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
