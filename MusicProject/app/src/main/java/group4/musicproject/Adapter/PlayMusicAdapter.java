package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import group4.musicproject.Activity.PlayMusicAcivity;
import group4.musicproject.Model.Song;
import group4.musicproject.R;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public PlayMusicAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_playmusic, parent, false);
        return new PlayMusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewPlayMusicIndex.setText(position + 1 + "");
        holder.textViewPlayMusicName.setText(song.getTenBaiHat( ));
        holder.textViewPlayMusicCaSi.setText(song.getCaSi( ));
    }

    @Override
    public int getItemCount() {
        return songs.size( );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlayMusicIndex;
        TextView textViewPlayMusicName;
        TextView textViewPlayMusicCaSi;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPlayMusicIndex = itemView.findViewById(R.id.textViewPlayMusicIndex);
            textViewPlayMusicName = itemView.findViewById(R.id.textViewPlayMusicName);
            textViewPlayMusicCaSi = itemView.findViewById(R.id.textViewPlayMusicCaSi);


        }
    }
}
