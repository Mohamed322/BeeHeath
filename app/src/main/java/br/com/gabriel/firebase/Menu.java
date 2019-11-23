package br.com.gabriel.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.gabriel.firebase.Fragmentos.FavFragmento;
import br.com.gabriel.firebase.Fragmentos.HomeFragmento;
import br.com.gabriel.firebase.Fragmentos.ProcFragmento;
import br.com.gabriel.firebase.model.Paciente;
import br.com.gabriel.firebase.model.Usuario;


public class Menu extends AppCompatActivity {

    private Fragment fragSelecionado = null;
    private Bundle bundle = new Bundle();
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bundle = getIntent().getExtras();
        Paciente user =(Paciente) bundle.getSerializable("Paciente");
        bundle.putSerializable("Paciente",user);
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", user.getId()+"");
        editor.commit();
        alert(user.getNome());
        navListener = menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.Home:
                    fragSelecionado = new HomeFragmento();
                    break;
                case R.id.favorito:
                    fragSelecionado = new FavFragmento();
                    break;
                case R.id.pesquisar:
                    fragSelecionado = new ProcFragmento();
                    break;
            }

            fragSelecionado.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                    .replace(R.id.container_fragmento, fragSelecionado).commit();

            return true;
        };
        BottomNavigationView bottomNavigationView = findViewById(R.id.botoes_navegacao);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        fragSelecionado = new HomeFragmento();
        fragSelecionado.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragmento,
               fragSelecionado).commit();
    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
