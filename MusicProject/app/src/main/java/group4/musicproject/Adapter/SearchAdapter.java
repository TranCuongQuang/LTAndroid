package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Model.Song;
import group4.musicproject.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songs;
    boolean active = false;

    public SearchAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewSongSearch.setText(song.getTenBaiHat( ));
        holder.textViewCaSiSearch.setText(song.getCaSi( ));
        Picasso.with(context).load(song.getHinhBaiHat( )).into(holder.imageViewSongSeach);
    }

    @Override
    public int getItemCount() {
        return songs.size( );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSongSearch;
        TextView textViewCaSiSearch;
        ImageView imageViewSongSeach;
        ImageView imageViewLikeSearch;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewSongSearch = itemView.findViewById(R.id.textViewSongSearch);
            textViewCaSiSearch = itemView.findViewById(R.id.textViewCaSiSearch);
            imageViewSongSeach = itemView.findViewById(R.id.imageViewSongSeach);
            imageViewLikeSearch = itemView.findViewById(R.id.imageViewLikeSearch);

            itemView.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicAdapter.class);
                    intent.putExtra("song", songs.get(getPosition( )));
                    context.startActivity(intent);
                }
            });

            imageViewLikeSearch.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    if (active == false) {
                        imageViewLikeSearch.setImageResource(R.drawable.iconloved);
                        active = true;
                    } else {
                        imageViewLikeSearch.setImageResource(R.drawable.iconlove);
                        active = false;
                    }
                }
            });
        }
    }
}
