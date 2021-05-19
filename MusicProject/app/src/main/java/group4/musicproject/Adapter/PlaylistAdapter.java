package group4.musicproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import group4.musicproject.Model.Playlist;
import group4.musicproject.R;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView txtNamePlaylist;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_playlist, null, false);
            viewHolder = new ViewHolder();
            viewHolder.txtNamePlaylist = convertView.findViewById(R.id.textviewNamePlaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imageViewBackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageViewPlaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtNamePlaylist.setText(playlist.getTen());
        return convertView;
    }
}
