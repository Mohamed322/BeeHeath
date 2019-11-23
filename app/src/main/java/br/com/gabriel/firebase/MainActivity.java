package br.com.gabriel.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import org.json.JSONObject;

import br.com.gabriel.firebase.model.Paciente;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private EditText login;
    private EditText senha;
    private SignInButton btnSingIn;
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    RequestQueue requestQueue;


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
            if (login.getText().toString().equals("admin")) {
                if (senha.getText().toString().equals("admin")) {
                    entrar(null);
                } else {
                    alert("Senha Incorreta");
                }
            } else {
                alert("Login Incorreto");
            }
        }
    }

    private void entrar(Paciente user) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("Paciente",user);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void singGoogle() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                //Alterar para mandar para o banco
                firebaseLogin(account);
            }
        }
    }

    private void firebaseLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Paciente paciente = new Paciente(account.getDisplayName(),
                                account.getEmail(),account.getId(),"patient",null);
                        //enviaApi(paciente);
                        entrar(paciente);
                    } else {
                        alert("Falha na autenticação");
                    }
                });
    }



    public void enviaApi(Paciente paciente){
        JSONObject json = new JSONObject();
        try {
            json.put("fullname", paciente.getNome());
            json.put("email", paciente.getEmail());
            json.put("password", paciente.getSenha());
            json.put("type", paciente.getTipo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getString(
                R.string.web_service_url
        )+"/user/login";
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                json,
                (resultado) ->{
                    Toast.makeText(this,resultado.toString(),Toast.LENGTH_LONG).show();
                    //entrar(paciente);
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
