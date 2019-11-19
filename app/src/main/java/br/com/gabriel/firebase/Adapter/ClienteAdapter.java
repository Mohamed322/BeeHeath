package br.com.gabriel.firebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.gabriel.firebase.PerfilNutricionista;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.NutricionistaModel;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private List<NutricionistaModel> dados;

    public ClienteAdapter(List<NutricionistaModel> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflando a View e passando o context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_nutricionistas, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }


    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente holder, int position) {

        if (dados != null && dados.size() > 0) {
            NutricionistaModel nutricionistaModel = dados.get(position);

            //Passando os dados para a View
            holder.textNome.setText(nutricionistaModel.getNome());
            holder.textEsp.setText(holder.textEsp.getText().toString() + ": " + nutricionistaModel.getEspecialiacao());
            holder.img.setImageResource(nutricionistaModel.getFoto());;

        }
    }

    @Override
    public int getItemCount() {
        //Tamanho do List
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView textNome;
        public TextView textEsp;
        public ImageView img;

        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            //Passando o TextView de referencia
            textNome = (TextView) itemView.findViewById(R.id.textNome);
            textEsp = (TextView) itemView.findViewById(R.id.textEsp);
            img = itemView.findViewById(R.id.foto);


            itemView.setOnClickListener(v -> {
                if (dados.size() > 0) {

                    NutricionistaModel nutricionistaModel = dados.get(getLayoutPosition());
                    Intent intent = new Intent(context, PerfilNutricionista.class);
                    intent.putExtra("Nutri", nutricionistaModel);
                    context.startActivity(intent);
                }
            });
        }
    }

}
