package br.com.gabriel.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.firebase.model.NutricionistaModel;

public class Pesquisa extends AppCompatActivity {

    private EditText editPalavra;
    private ListView listVPesquisa;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<NutricionistaModel> listPessoa = new ArrayList<NutricionistaModel>();
    private ArrayAdapter<NutricionistaModel> arrayAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        inicializarComponentes();
        inicialiazarFirebase();
        eventoEdit();
    }

    private void eventoEdit() {
        editPalavra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = editPalavra.getText().toString().trim();
                pesquisarPaavra(palavra);
            }
        });
    }

    private void pesquisarPaavra(String palavra) {
        Query query;
        if(palavra.equals("")){
            query = databaseReference.child("NutricionistaModel").orderByChild("nome");
        }else {
            query = databaseReference.child("NutricionistaModel").orderByChild("nome").
                    startAt(palavra).endAt(palavra+"\uf8ff");
        }

        listPessoa.clear();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    NutricionistaModel p = objSnapshot.getValue(NutricionistaModel.class);
                    listPessoa.add(p);
                }

                arrayAdapterPessoa = new ArrayAdapter<NutricionistaModel>(Pesquisa.this,
                        android.R.layout.simple_list_item_1,listPessoa);

                listVPesquisa.setAdapter(arrayAdapterPessoa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicialiazarFirebase() {
        FirebaseApp.initializeApp(Pesquisa.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarComponentes() {
        editPalavra = findViewById(R.id.textPalavra);
        listVPesquisa = findViewById(R.id.listVPesquisa);
    }

    @Override
    protected void onResume() {
         super.onResume();
         pesquisarPaavra("");
    }
}
