package br.com.gabriel.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;

import br.com.gabriel.firebase.model.Paciente;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private EditText login;
    private EditText senha;
    private SignInButton btnSingIn;
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    private RequestQueue requestQueue;
    private RadioButton rdPaciente, rdNutricionista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        iniciaComponentes();
        iniciaFirebase();
        conectaGoogleApi();
        clickButton();
    }

    private void clickButton() {
        btnSingIn.setOnClickListener(v -> singGoogle());
    }

    private void conectaGoogleApi() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void iniciaFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    private void iniciaComponentes() {
        login = findViewById(R.id.loginUsuario);
        senha = findViewById(R.id.loginSenha);
        btnSingIn = findViewById(R.id.btnSingIn);
        rdPaciente = findViewById(R.id.rdPaciente);
        rdNutricionista = findViewById(R.id.rdNutri);
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void authenticate(View view) {

        if (login.getText().toString().equals("")) {
            alert("Login em branco");
            login.requestFocus();
        } else if (senha.getText().toString().equals("")) {
            alert("Senha em branco");
            senha.requestFocus();
        } else {
            if (rdPaciente.isChecked()) {
                Paciente p = new Paciente();
                p.setEmail(login.getText().toString());
                p.setSenha(senha.getText().toString());
                p.setTipo("patient");
                enviaApi(p, false);
            }
            else
                alert("Acesso somente para pacientes");
        }
    }

    private void entrar(Paciente user, boolean google) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("Paciente", user);
        intent.putExtra("Google",google);
        startActivity(intent);
        mFirebaseAuth.signOut();
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void singGoogle() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(i, 1);
    }

    private void firebaseLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Paciente paciente = new Paciente(account.getDisplayName(),
                                account.getEmail(), account.getId()+"google", null);
                        enviaApi(paciente, true);
                    } else {
                        alert("Falha na autenticação");
                    }
                });
    }

    public void enviaApi(Paciente paciente, boolean google) {
        String url = getString(R.string.web_service_url) + "/user/login";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                paciente.json(),
                (resultado) -> {
                    try {
                        paciente.setId(resultado.getInt("iduser"));
                        paciente.setNome(resultado.getString("fullname"));
                        paciente.setNascimento(resultado.getString("birthday"));
                        entrar(paciente,google);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                (excecao) -> {
                    if (excecao.networkResponse.statusCode == 404) {
                        if (google) {
                            enviaApiRegistrer(paciente,google);
                        } else {
                            alert("Usuario não encontrado");
                        }
                    } else {
                        alert(getString(R.string.connect_error));
                        excecao.printStackTrace();
                    }
                }
        );
        requestQueue.add(req);
    }

    public void enviaApiRegistrer(Paciente paciente, boolean google) {
        String url = getString(
                R.string.web_service_url
        ) + "/user/register/";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                paciente.json(),
                (resultado) -> {
                    try {
                        int idUser = resultado.getInt("iduser");
                        paciente.setId(idUser);
                        entrar(paciente, google);
                        finish();
                    } catch (JSONException e) {
                        alert("Erro na resposta2");
                    }
                },
                (excecao) -> {
                    alert(getString(R.string.connect_error));
                    excecao.printStackTrace();
                }
        );
        requestQueue.add(req);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão com o Google");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                //Alterar para mandar para o banco
                assert account != null;
                firebaseLogin(account);
            }
        }
    }

}
