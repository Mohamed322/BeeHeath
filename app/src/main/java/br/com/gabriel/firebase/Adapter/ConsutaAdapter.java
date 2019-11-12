package br.com.gabriel.firebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.ConsultaModel;

public class ConsutaAdapter extends RecyclerView.Adapter<ConsutaAdapter.ViewHolderCliente> {

    private List<ConsultaModel> dados;
    private View view;

    public ConsutaAdapter(List<ConsultaModel> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflando a View e passando o context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        view = layoutInflater.inflate(R.layout.activity_consulta_recicle, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderCliente holder, int position) {
        ArrayAdapter adapter;
        if (dados != null && dados.size() > 0) {
            ConsultaModel consulta = dados.get(position);

            //Passando os dados para a View
            holder.txtDataConsulta.setText(consulta.getData().toString());
            adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_list_item_1,consulta.getHorarios());
            holder.lista.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        //Tamanho do List
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView txtDataConsulta;
        public ListView lista;


        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            //Passando o TextView de referencia
            txtDataConsulta = itemView.findViewById(R.id.txtDataConsulta);
            lista = itemView.findViewById(R.id.consultaList);

            itemView.setOnClickListener(v -> {
                if (dados.size() > 0) {
                    Toast.makeText(context,"Marcar conslta",Toast.LENGTH_LONG).show();
                    /*ConsultaModel consulta = dados.get(getLayoutPosition());
                    Intent intent = new Intent(context, PerfilNutricionista.class);
                    intent.putExtra("Nutri", consulta);
                    context.startActivity(intent);*/
                }
            });
        }
    }

}

