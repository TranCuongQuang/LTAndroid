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

import group4.musicproject.Activity.AlbumActivity;
import group4.musicproject.Activity.ListSongActivity;
import group4.musicproject.Model.Album;
import group4.musicproject.Model.Topic;
import group4.musicproject.R;

public class AlbumAllAdapter extends RecyclerView.Adapter<AlbumAllAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albums;

    public AlbumAllAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);
        Picasso.with(context).load(album.getHinhAlbum( )).into(holder.imageViewAlbums);
        holder.textViewAlbums.setText(album.getTenAlbum( ));
    }

    @Override
    public int getItemCount() {
        return albums.size( );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAlbums;
        TextView textViewAlbums;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewAlbums = itemView.findViewById(R.id.imageViewAlbums);
            textViewAlbums = itemView.findViewById(R.id.textViewAlbums);
            imageViewAlbums.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album", albums.get(getPosition( )));
                    context.startActivity(intent);
                }
            });
        }
    }
}
