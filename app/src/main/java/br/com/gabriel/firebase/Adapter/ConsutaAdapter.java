package br.com.gabriel.firebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

import br.com.gabriel.firebase.MarcarConsulta;
import br.com.gabriel.firebase.PerfilNutricionista;
import br.com.gabriel.firebase.R;
import br.com.gabriel.firebase.model.ConsultaModel;
import br.com.gabriel.firebase.model.NutricionistaModel;

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
        if (dados != null && dados.size() > 0) {
            ConsultaModel consulta = dados.get(position);

            //Passando os dados para a View
            holder.txtDataConsulta.setText(DateFormat.getDateInstance().format(consulta.getData()));
            holder.txtNConsulta.setText("vagas: "+consulta.getHorario() + " ");
        }
    }

    @Override
    public int getItemCount() {
        //Tamanho do List
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView txtDataConsulta;
        public TextView txtNConsulta;

        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            //Passando o TextView de referencia
            txtDataConsulta = itemView.findViewById(R.id.txtDataConsulta);
            txtNConsulta = itemView.findViewById(R.id.txtNConsulta);

            itemView.setOnClickListener(v -> {
                if (dados.size() > 0) {

                    ConsultaModel consultaModel = dados.get(getLayoutPosition());
                    Intent intent = new Intent(context, MarcarConsulta.class);
                    intent.putExtra("Consulta", consultaModel);
                    Toast.makeText(context,consultaModel.getData().toString(),Toast.LENGTH_LONG).show();
                    context.startActivity(intent);
                }
            });
        }
    }

}

