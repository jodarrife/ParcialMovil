package com.example.parcial2.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.parcial2.ADAPTER.TrackAdapter;
import com.example.parcial2.IO.Track.TracksApiAdapter;
import com.example.parcial2.MODEL.Consulta.Artist;
import com.example.parcial2.MODEL.Track;
import com.example.parcial2.MODEL.Consulta.Tracks;
import com.example.parcial2.MODEL.Consulta.TracksList;
import com.example.parcial2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    /**
     * Instanciamos las variables para setearlas en el evento onCreate
     */
    FloatingActionButton fab;
    FloatingActionButton fab2;
    TrackAdapter adapter;
    String nombreSong = null;
    String nombreArti = null;

    //INTENT
    private final String LOG_TAG = addSongActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "extra.MESSAGE";


    //LSITAS
    List<Artist> artistList = new ArrayList<>();
    List<Track> trackListAux;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("key3", true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TrackAdapter(trackListAux, artistList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore the state.
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("key3");
            if (isVisible) {

            }
        }
        //obtener
        obtenerLista();

        fab = findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addSongActivity.class);
                startActivity(intent);
            }
        });

        //Recivir por intent
        Intent intent = getIntent();
        String receivedMessage = intent.getStringExtra("key");
        nombreSong = receivedMessage;
        String receivedMessage2 = intent.getStringExtra("key2");
        nombreArti = receivedMessage2;

        fab2 = findViewById(R.id.btnActualizar);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(0);
            }
        });
    }

    private void insert(int posicion) {
        trackListAux.add(posicion, new Track(nombreSong, "0"));
        artistList.add(posicion, new Artist(nombreArti));
        adapter.notifyDataSetChanged();
    }

    private void obtenerLista() {
        Call<TracksList> call = TracksApiAdapter.getApiService().getTracks();
        call.enqueue(new Callback<TracksList>() {

            @Override
            public void onResponse(Call<TracksList> call, Response<TracksList> response) {
                if (response.isSuccessful()) {
                    TracksList tracksList = response.body();
                    Tracks tracks = tracksList.getTracks();
                    trackListAux = tracks.getTrack();


                    for (Track track : trackListAux) {
                        Artist artist = track.getArtist();
                        artistList.add(artist);
                    }

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new TrackAdapter(trackListAux, artistList);
                    recyclerView.setAdapter(adapter);


                    Log.d("onResponse Track", "Size of Track => " + tracksList);
                }
            }

            @Override
            public void onFailure(Call<TracksList> call, Throwable t) {
                Log.d("onFailure Track", "Size of Track => " + t);
            }
        });

    }



}
