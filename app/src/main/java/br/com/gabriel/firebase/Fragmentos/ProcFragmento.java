package br.com.gabriel.firebase.Fragmentos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import br.com.gabriel.firebase.Adapter.ClienteAdapter;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.NutricionistaModel;
import br.com.gabriel.firebase.model.Usuario;

public class ProcFragmento extends Fragment {

    private Usuario user;
    private RecyclerView lstDados;
    private TextView vazio, busca;
    private List<NutricionistaModel> dados;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_procura, container, false);
        lstDados = view.findViewById(R.id.lstDados);
        vazio = view.findViewById(R.id.Vazio);
        busca = view.findViewById(R.id.Busca);
        lstDados.setHasFixedSize(true);
        lstDados.setVisibility(View.GONE);
        vazio.setVisibility(View.VISIBLE);

        try {
            Bundle bundle = getArguments();
            assert bundle != null;
            user = bundle.getParcelable("Usuario");
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventoEdit();

        return view;
    }

    private void eventoEdit() {
        busca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = busca.getText().toString().trim();
                pesquisarPalavra(palavra);
            }
        });
    }

    private void pesquisarPalavra(String palavra) {

        if (palavra.length() > 2) {

            dados = enviaApi(palavra);
        }
            /*
            if (dados == null) {
                vazio.setText(getText(R.string.nEncontrado));
                Toast.makeText(getContext(), "Deu Ruim", Toast.LENGTH_LONG).show();
            } else {
                lstDados.setVisibility(View.VISIBLE);
                vazio.setVisibility(View.GONE);
                try {
                    iniciaRecyclerView(dados);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        } else {
            lstDados.setVisibility(View.GONE);
            vazio.setVisibility(View.VISIBLE);
        }
*/
    }

    private void iniciaRecyclerView(List<NutricionistaModel> dados) {


        //Criação do LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lstDados.setLayoutManager(linearLayoutManager);

        //Passando para o adapter
        ClienteAdapter clienteAdapter = new ClienteAdapter(dados);

        //Setando na tela
        lstDados.setAdapter(clienteAdapter);
        clienteAdapter.notifyDataSetChanged();
        lstDados.setVisibility(View.VISIBLE);
    }

    private List<NutricionistaModel> enviaApi(String palavra) {
        String url = getString(R.string.web_service_url) + "nutritionist/";
        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        JsonArrayRequest req = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                (resultado) -> {
                    dados = new ArrayList<>();
                    try {
                        user = new Usuario();
                        JSONObject iesimo;
                        for (int i = 0; i < resultado.length(); i++) {
                            iesimo = resultado.getJSONObject(i);
                            int idUser = iesimo.getInt("iduser");
                            String email = iesimo.getString("email");
                            String pwd = iesimo.getString("password");
                            String name = iesimo.getString("fullname");
                            String niver = iesimo.getString("birthday");
                            String tipo = iesimo.getString("type");
                            String esp = iesimo.getString("specialization");
                            int crn = iesimo.getInt("crn");
                            dados.add(new NutricionistaModel(idUser, 0, name, null, email, null, esp));
                        }
                        iniciaRecyclerView(dados);
                    } catch (JSONException e) {

                    }
                    Toast.makeText(getContext(), resultado.toString(), Toast.LENGTH_LONG).show();

                    Toast.makeText(getContext(), "Sucesso\n" + resultado, Toast.LENGTH_LONG).show();
                },
                (excecao) -> {
                    Toast.makeText(
                            getContext(),
                            getString(R.string.connect_error),
                            Toast.LENGTH_SHORT
                    ).show();
                    excecao.printStackTrace();
                }
        );
        requestQueue.add(req);
        return dados;
    }

    private List<NutricionistaModel> todosOsClientes() {
        return new ArrayList<>(Arrays.asList(
                new NutricionistaModel(0, R.drawable.download, "Gabriel", "Vila Santa Catarina",
                        "gsb@gmail.com", "(11)95813-3519", "Cardio"),
                new NutricionistaModel(0, R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "infanto"),
                new NutricionistaModel(0, R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "Clinico"),
                new NutricionistaModel(0, R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb"),
                new NutricionistaModel(0, R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb"),
                new NutricionistaModel(0, R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb")
        ));
    }
}