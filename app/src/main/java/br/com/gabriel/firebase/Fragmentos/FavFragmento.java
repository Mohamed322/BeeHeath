package br.com.gabriel.firebase.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.gabriel.firebase.Adapter.ClienteAdapter;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.NutricionistaModel;
import br.com.gabriel.firebase.model.Usuario;

public class FavFragmento extends Fragment {

    private RecyclerView favRecycler;
    private View view;
    private Usuario user;
    private RecyclerView lstDados;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_fav, container, false);
        iniciaComponentes();
        try {
            Bundle bundle = getArguments();
            assert bundle != null;
            user = bundle.getParcelable("Usuario");
            exibirDados();
        }catch (Exception e){
            Toast.makeText(getContext(),"Erro na exbição\n"+e.toString(),Toast.LENGTH_LONG).show();
        }
        return view;
    }
    private void exibirDados() {

    }

    private void iniciaComponentes() {
        favRecycler = view.findViewById(R.id.favRecicler);
    }

    private void iniciaRecyclerView(List<NutricionistaModel> dados) {

        //Criação do LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lstDados.setLayoutManager(linearLayoutManager);

        //Passando para o adapter
        ClienteAdapter clienteAdapter = new ClienteAdapter(dados);

        //Setando na tela
        lstDados.setAdapter(clienteAdapter);

    }
}
