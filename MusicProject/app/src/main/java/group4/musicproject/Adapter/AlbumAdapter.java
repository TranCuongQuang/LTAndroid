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

import group4.musicproject.Model.Album;
import group4.musicproject.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.txtArtistAlbum.setText(album.getTenCaSiAlbum());
        holder.txtNameAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgPictureAlbum);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPictureAlbum;
        TextView txtNameAlbum, txtArtistAlbum;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPictureAlbum = itemView.findViewById(R.id.imageViewAlbum);
            txtNameAlbum = itemView.findViewById(R.id.textViewTitleAlbum);
            txtArtistAlbum = itemView.findViewById(R.id.textViewArtist);
        }
    }
}
