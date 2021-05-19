package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Activity.PlayMusicAcivity;
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
        holder.textViewIndex.setText(position + 1 + "");
        holder.textViewNameTheListSong.setText(song.getTenBaiHat( ));
        holder.textViewArtistTheListSong.setText(song.getCaSi( ));
    }

    @Override
    public int getItemCount() {
        return songs.size( );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIndex;
        TextView textViewNameTheListSong;
        TextView textViewArtistTheListSong;
        ImageView imageViewLike;
        RelativeLayout relativeLayoutItem;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewIndex = itemView.findViewById(R.id.textViewIndex);
            textViewNameTheListSong = itemView.findViewById(R.id.textViewNameTheListSong);
            textViewArtistTheListSong = itemView.findViewById(R.id.textViewArtistTheListSong);
            imageViewLike = itemView.findViewById(R.id.imageViewLike);
            relativeLayoutItem = itemView.findViewById(R.id.relativeLayoutItem);

            relativeLayoutItem.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicAcivity.class);
                    intent.putExtra("song", songs.get(getPosition( )));
                    context.startActivity(intent);
                }
            });
        }
    }
}
