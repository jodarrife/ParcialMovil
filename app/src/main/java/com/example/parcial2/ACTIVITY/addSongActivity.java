package com.example.parcial2.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parcial2.ADAPTER.TrackAdapter;
import com.example.parcial2.IO.AddTrack.AddTracksApiAdapter;
import com.example.parcial2.IO.Track.TracksApiAdapter;
import com.example.parcial2.MODEL.Consulta.Artist;
import com.example.parcial2.MODEL.Consulta.Tracks;
import com.example.parcial2.MODEL.Consulta.TracksList;
import com.example.parcial2.MODEL.ConsultaAdd.Results;
import com.example.parcial2.MODEL.ConsultaAdd.Searching;
import com.example.parcial2.MODEL.ConsultaAdd.TrackSearch;
import com.example.parcial2.MODEL.ConsultaAdd.Trackmatches;
import com.example.parcial2.MODEL.Track;
import com.example.parcial2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addSongActivity extends AppCompatActivity {


    /**
     * Instanciamos las variables para setearlas
     */
    TextView nombre, artista, cancionbUSCADA,nameT, artistT, artistabUSCADA;
    EditText txtBuscar;
    Button btnBuscar, buttonadd;
    Searching search;


    //listas
    List<TrackSearch> lista;
    List<Track> listTrack;
    List<Artist> artistList = new ArrayList<>();
    List<Track> trackList1;


    //Auxiliares
    String nombreAux;
    String artistAux;
    TrackAdapter adapter;

    //intent
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "extra.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);


        nombre = (TextView) findViewById(R.id.cancion);
        artista = (TextView) findViewById(R.id.autor);
        txtBuscar = (EditText) findViewById(R.id.nameBuscador);
        nameT = findViewById(R.id.cancion);
        artistT = findViewById(R.id.autor);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerLista();
            }
        });
        cancionbUSCADA = findViewById(R.id.cancion);
        artistabUSCADA = findViewById(R.id.autor);
        buttonadd = (Button) findViewById(R.id.addButtonObject);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnvioDatos(v);
            }
        });
    }


    private void obtenerLista() {
        String filtro = txtBuscar.getText().toString();
        Call<Searching> call = AddTracksApiAdapter.getApiService().getSearching("track.search", filtro, "b284db959637031077380e7e2c6f2775", "json");
        try {
            call.enqueue(new Callback<Searching>() {
                @Override
                public void onResponse(Call<Searching> call, Response<Searching> response) {
                    if (response.isSuccessful()) {
                        nombre.setText("DONE!");
                        search = response.body();
                        Results results = search.getResults();
                        Trackmatches trackmatches = results.getTrackmatches();
                        lista = trackmatches.getTrackSearch();
                        nombre.setText(lista.get(0).getName());
                        artista.setText(lista.get(0).getArtist());
                    }
                    Log.d("onResponse search", "Size of Track => ");
                }

                @Override
                public void onFailure(Call<Searching> call, Throwable t) {
                    Log.d("onFailure search", "Size of Track => ");
                }
            });
        } catch (IllegalStateException | JsonSyntaxException exception) {

        }
    }


//intent

     public void EnvioDatos(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        Intent intent2 = new Intent(this, MainActivity.class);
        String cancion = cancionbUSCADA.getText().toString();
        String artista = artistabUSCADA.getText().toString();
        intent.putExtra("key", cancion);
        intent.putExtra("key2", artista);
        startActivity(intent);
    }
}
