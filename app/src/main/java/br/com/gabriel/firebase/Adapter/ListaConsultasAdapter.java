package br.com.gabriel.firebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.gabriel.firebase.PerfilNutricionista;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.ConsultaMarcada;

public class ListaConsultasAdapter extends RecyclerView.Adapter<ListaConsultasAdapter.ViewHolderCliente> {

    private List<ConsultaMarcada> dados;

    public ListaConsultasAdapter(List<ConsultaMarcada> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ListaConsultasAdapter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflando a View e passando o context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_consultas, parent, false);

        ListaConsultasAdapter.ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }


    @Override
    public void onBindViewHolder(@NonNull ListaConsultasAdapter.ViewHolderCliente holder, int position) {

        if (dados != null && dados.size() > 0) {
            ConsultaMarcada consultaMarcada = dados.get(position);

            //Passando os dados para a View
            holder.LC_Nutricionista.setText(consultaMarcada.getNutricionista());
            holder.LC_Horario.setText(consultaMarcada.getHorario());
            holder.LC_Data.setText(consultaMarcada.getData());
            holder.LC_Local.setText(consultaMarcada.getLocal());
        }
    }

    @Override
    public int getItemCount() {
        //Tamanho do List
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView LC_Nutricionista;
        public TextView LC_Data;
        public TextView LC_Horario;
        public TextView LC_Local;

        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            //Passando o TextView de referencia
            LC_Nutricionista = itemView.findViewById(R.id.LC_Nutriciosta);
            LC_Data =  itemView.findViewById(R.id.LC_Data);
            LC_Local =  itemView.findViewById(R.id.LC_Local);
            LC_Horario =  itemView.findViewById(R.id.LC_Horario);


            itemView.setOnClickListener(v -> {
                if (dados.size() > 0) {
                    /*
                    ConsultaMarcada consultaMarcada= dados.get(getLayoutPosition());
                    Intent intent = new Intent(context, PerfilNutricionista.class);
                    intent.putExtra("Consultas", consultaMarcada);
                    context.startActivity(intent);
                    */
                    Toast.makeText(context,"Mostrar Consultas",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
