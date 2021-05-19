package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Activity.ListSongActivity;
import group4.musicproject.Activity.PlayMusicAcivity;
import group4.musicproject.Model.Song;
import group4.musicproject.R;

public class TheBestLikeSongAdapter extends RecyclerView.Adapter<TheBestLikeSongAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public TheBestLikeSongAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_thebestlikesong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewNameTheBestLikeSong.setText(song.getTenBaiHat());
        holder.textViewArtistTheBestLikeSong.setText(song.getCaSi());
        Picasso.with(context).load(song.getHinhBaiHat()).into(holder.imageViewTheBestLikeSong);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNameTheBestLikeSong;
        TextView textViewArtistTheBestLikeSong;
        ImageView imageViewTheBestLikeSong;
        ImageView imageViewLike;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameTheBestLikeSong = itemView.findViewById(R.id.textViewNameTheBestLikeSong);
            textViewArtistTheBestLikeSong = itemView.findViewById(R.id.textViewArtistTheBestLikeSong);
            imageViewTheBestLikeSong = itemView.findViewById(R.id.imageViewTheBestLikeSong);
            imageViewLike = itemView.findViewById(R.id.imageViewLike);

            imageViewTheBestLikeSong.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicAcivity.class);
                    intent.putExtra("song", songs.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

