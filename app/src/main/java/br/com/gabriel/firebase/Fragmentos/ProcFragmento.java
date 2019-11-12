package br.com.gabriel.firebase.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.com.gabriel.firebase.Adapter.ClienteAdapter;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.NutricionistaModel;
import br.com.gabriel.firebase.model.Usuario;

public class ProcFragmento extends Fragment {
    private RecyclerView lstDados;
    private ClienteAdapter clienteAdapter;
    private List<NutricionistaModel> dados;
    private Usuario user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_procura, container, false);

        lstDados = view.findViewById(R.id.lstDados);
        lstDados.setHasFixedSize(true);

        try {
            Bundle bundle = getArguments();
            assert bundle != null;
            user = bundle.getParcelable("Usuario");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Criação do LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lstDados.setLayoutManager(linearLayoutManager);

        //Lista de dados
        List<NutricionistaModel> dados = todosOsClientes();

        //Passando para o adapter
        clienteAdapter = new ClienteAdapter(dados);

        //Setando na tela
        lstDados.setAdapter(clienteAdapter);
        return view;
    }

    private List<NutricionistaModel> todosOsClientes() {
        return new ArrayList<>(Arrays.asList(
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Gabriel", "Vila Santa Catarina",
                        "gsb@gmail.com", "(11)95813-3519", "Cardio"),
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "infanto"),
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "Clinico"),
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb"),
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb"),
                new NutricionistaModel(UUID.randomUUID().toString(), R.drawable.download, "Douglas", "Heaven",
                        "Dougras@gmail.com", "777-777-77", "abshdb")
        ));
    }
}