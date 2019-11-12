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

import com.bumptech.glide.Glide;

import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.Usuario;

public class HomeFragmento extends Fragment {

    private ImageView ivFoto;
    private TextView homeNome, homeEmail;
    private View view;
    private Usuario user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_home, container, false);
        iniciaComponentes();
        try {
            Bundle bundle = getArguments();
            assert bundle != null;
            user = bundle.getParcelable("Usuario");
            exibirDados();
        }catch (Exception e){
            //Toast.makeText(view.getContext(),"Erro na exbição\n"+e.toString(),Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void exibirDados() {
        homeNome.setText(user.getNome());
        homeEmail.setText(user.getEmail());

        Glide.with(view.getContext()).load(user.getFoto()).circleCrop().into(ivFoto);
    }

    private void iniciaComponentes() {
        ivFoto = view.findViewById(R.id.IvFoto);
        homeNome = view.findViewById(R.id.homeNome);
        homeEmail = view.findViewById(R.id.homeEmail);
    }
}