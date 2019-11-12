package br.com.gabriel.firebase.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class Banco {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public DatabaseReference iniciarBanco(Context context) {
        if(databaseReference == null) {
            try {
                FirebaseApp.initializeApp(context);
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseDatabase.setPersistenceEnabled(true);
                databaseReference = firebaseDatabase.getReference();
                return databaseReference;
            }catch (Exception e){
                Toast.makeText(context,"Erro no banco",Toast.LENGTH_LONG).show();
            }
        }
        return getDatabaseReference();
    }


    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
