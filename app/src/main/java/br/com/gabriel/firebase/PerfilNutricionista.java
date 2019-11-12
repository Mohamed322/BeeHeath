package br.com.gabriel.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.gabriel.firebase.model.NutricionistaModel;

public class PerfilNutricionista extends AppCompatActivity {
    private ImageView foto;
    private TextView nome, espec, emal, ender, tel;
    private NutricionistaModel n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_nutricionista);
        foto = findViewById(R.id.fotoP);
        nome = findViewById(R.id.textNomeP);
        espec = findViewById(R.id.textEspP);
        emal = findViewById(R.id.textEmailP);
        ender = findViewById(R.id.textEnderecoP);
        tel = findViewById(R.id.textTelefoneP);
        setarNutri();
    }

    @SuppressLint("SetTextI18n")
    private void setarNutri() {
        n = (NutricionistaModel) getIntent().getSerializableExtra("Nutri");

        assert n != null;
        foto.setImageResource(n.getFoto());
        nome.setText(getText(R.string.NomeCompleto)+": "+n.getNome());
        espec.setText(getText(R.string.Esp)+": "+n.getEspecialiacao());
        emal.setText(getText(R.string.Email) +": "+n.getEmail());
        ender.setText(getText(R.string.End) +": "+n.getEndereco());
        tel.setText(getText(R.string.tel) +": "+n.getTelefone());
    }

    public void marcarConsulta(View view) {
        Intent i = new Intent(this, Consulta.class);
        i.putExtra("Nutri",n);
        startActivity(i);
        Toast.makeText(view.getContext(),"Agendar ConsultaModel",Toast.LENGTH_LONG).show();
    }
}
