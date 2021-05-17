package group4.musicproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Model.Song;
import group4.musicproject.R;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public ListSongAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listsong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewNameTheListSong.setText(song.getTenBaiHat());
        holder.textViewArtistTheListSong.setText(song.getCaSi());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNameTheListSong;
        TextView textViewArtistTheListSong;
        ImageView imageViewLike;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameTheListSong = itemView.findViewById(R.id.textViewNameTheListSong);
            textViewArtistTheListSong = itemView.findViewById(R.id.textViewArtistTheListSong);
            imageViewLike = itemView.findViewById(R.id.imageViewLike);
        }
    }
}
