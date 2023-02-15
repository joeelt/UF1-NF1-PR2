package com.example.uf1_nf1_pr2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mETNom,mETGenero, mETVotacion;
    private Button mBtnAfegir, mBtnActualizar, mBtnEsborrar;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private ListView mLvLista;
    private List<Pelicula> mListaPelicula = new ArrayList<>();

    private ArrayAdapter<Pelicula> mAdapterPizzes;
    private Pelicula mPeliculaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicialitzarComponents();

        InicialitzarListeners();
    }

    private void InicialitzarListeners() {

        mBtnAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AfegirPelicula();
            }
        });

        mBtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActualizarPelicula();
            }
        });

        mBtnEsborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EsborrarPelicula();
            }
        });

        mLvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                mPeliculaSeleccionada = (Pelicula) adapterView.getItemAtPosition(position);
                mETNom.setText(mPeliculaSeleccionada.getNom());
                mETGenero.setText(mPeliculaSeleccionada.getGenero());
                mETVotacion.setText(mPeliculaSeleccionada.getVotacion());
            }
        });

    }

    private void EsborrarPelicula() {
        mReference.child("Pelicula").child(mPeliculaSeleccionada.getUid()).removeValue();
    }

    private void ActualizarPelicula() {
        String nom = mETNom.getText().toString();
        String genero = mETGenero.getText().toString();
        String votacion = mETVotacion.getText().toString();
        Pelicula pelicula = new Pelicula(nom, genero, votacion, mPeliculaSeleccionada.getUid());

        mReference.child("Pelicula").child(mPeliculaSeleccionada.getUid()).setValue(pelicula);

        ResetCamps();
    }



    private void AfegirPelicula() {
        String nom = mETNom.getText().toString();
        String genero = mETGenero.getText().toString();
        String votacion = mETVotacion.getText().toString();
        String uid = mReference.push().getKey();

        Pelicula pelicula = new Pelicula(nom, genero, votacion, uid);

        mReference.child("Pelicula").child(uid).setValue(pelicula);
    }

    private void ResetCamps() {
        mETNom.setText("");
        mETGenero.setText("");
        mETVotacion.setText("");
    }

    private void InicialitzarComponents() {

        mETNom = findViewById(R.id.ET_Nom);
        mETGenero = findViewById(R.id.ET_Genero);
        mETVotacion = findViewById(R.id.ET_Votacion);
        mBtnAfegir = findViewById(R.id.BTN_Afegir);
        mBtnActualizar = findViewById(R.id.BTN_Actualizar);
        mBtnEsborrar = findViewById(R.id.BTN_Esborrar);

        mLvLista = findViewById(R.id.LV_Lista);

        mDatabase = FirebaseDatabase.getInstance("https://uf1-nf1-pr2-default-rtdb.europe-west1.firebasedatabase.app/");
        mReference = mDatabase.getReference();



    }
}