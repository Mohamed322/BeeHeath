package br.com.gabriel.firebase.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.gabriel.firebase.Adapter.ListaConsultasAdapter;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.ConsultaMarcada;
import br.com.gabriel.firebase.model.Usuario;

public class FavFragmento extends Fragment {

    private RecyclerView favRecycler;
    private View view;
    private Usuario user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_fav, container, false);
        iniciaComponentes();
        try {
            Bundle bundle = getArguments();
            assert bundle != null;
            user = bundle.getParcelable("Usuario");

        }catch (Exception e){
            Toast.makeText(getContext(),"Erro na exbição\n"+e.toString(),Toast.LENGTH_LONG).show();

        }
        exibirDados();
        return view;
    }

    private void exibirDados() {
        iniciaRecyclerView(todasConsultasMarcadas());
    }

    private ArrayList todasConsultasMarcadas(){
        return new ArrayList<ConsultaMarcada>(Arrays.asList(
                new ConsultaMarcada(new Date(),"Douglas","Rua Martinho Claro","12:12"),
                new ConsultaMarcada(new Date(),"Marcelo","Rua Martinho Claro","15:25")
                ));
    }

    private void iniciaComponentes() {
        favRecycler = view.findViewById(R.id.favRecicler);
    }

    private void iniciaRecyclerView(List<ConsultaMarcada> dados) {

        //Criação do LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favRecycler.setLayoutManager(linearLayoutManager);

        //Passando para o adapter
        ListaConsultasAdapter listaConsultasAdapter= new ListaConsultasAdapter(dados);

        //Setando na tela
        favRecycler.setAdapter(listaConsultasAdapter);
    }
}
