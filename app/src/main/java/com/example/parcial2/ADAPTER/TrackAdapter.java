package com.example.parcial2.ADAPTER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial2.MODEL.Consulta.Artist;
import com.example.parcial2.MODEL.Track;
import com.example.parcial2.R;

import java.util.List;


public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private List<Track> tracks;
    private List<Artist> artist;

    public TrackAdapter(List<Track> tracks, List<Artist> artist) {
        this.tracks = tracks;
        this.artist = artist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_track,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTrack(tracks.get(position));
        holder.bindArtist(artist.get(position));

    }

    @Override
    public int getItemCount() {
        int item = 1;
        if (item==1){
            return tracks.size();
        }
        return artist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mNombre;
        public TextView mDduracion;
        public TextView mArtista;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNombre = (TextView) itemView.findViewById(R.id.name);
            mDduracion = (TextView) itemView.findViewById(R.id.duration);
            mArtista = (TextView) itemView.findViewById(R.id.artist);

        }

        public void bindTrack(Track track) {
            String nombre = track.getName();
            String duracion = track.getDuration();

            mNombre.setText(nombre);
            mDduracion.setText(duracion);
        }

        public void bindArtist(Artist artist) {
            String artista = artist.getName();

            mArtista.setText(artista);
        }
    }
}
