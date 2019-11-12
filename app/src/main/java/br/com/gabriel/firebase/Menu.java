package br.com.gabriel.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.gabriel.firebase.Fragmentos.FavFragmento;
import br.com.gabriel.firebase.Fragmentos.HomeFragmento;
import br.com.gabriel.firebase.Fragmentos.ProcFragmento;
import br.com.gabriel.firebase.model.Usuario;


public class Menu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mFirebaseAuth;
    Fragment fragSelecionado = null;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mFirebaseUser;
    private GoogleSignInOptions mGoogleApiClient;
    private Bundle bundle = new Bundle();
    private Usuario user;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conectaGoogleApi();
        iniciaFirebase();
        setContentView(R.layout.menu);
        navListener = menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.Home:
                    fragSelecionado = new HomeFragmento();
                    //bundle.putParcelable("Usuario",user);
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
        /*getSupportFragmentManager().beginTransaction().replace(R.id.container_fragmento,
               fragSelecionado).commit();*/
    }

    private void conectaGoogleApi() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    private void iniciaFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = firebaseAuth -> {
            mFirebaseUser = firebaseAuth.getCurrentUser();
            if (mFirebaseUser != null) {
                user = new Usuario(mFirebaseUser.getPhotoUrl(), mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail(), mFirebaseUser.zzg(), "Pacirnt");
                bundle.putParcelable("Usuario", user);
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragmento,
                        fragSelecionado).commit();
            } else {
                finish();
            }
        };
    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Falha na conexão", Toast.LENGTH_LONG).show();
    }
}
