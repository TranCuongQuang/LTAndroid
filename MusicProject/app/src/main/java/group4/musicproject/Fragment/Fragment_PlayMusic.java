package group4.musicproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import group4.musicproject.Activity.PlayMusicAcivity;
import group4.musicproject.Adapter.AlbumAdapter;
import group4.musicproject.Adapter.PlayMusicAdapter;
import group4.musicproject.Model.Album;
import group4.musicproject.R;

public class Fragment_PlayMusic extends Fragment {
    View view;
    RecyclerView recylerViewPlaymusic;
    PlayMusicAdapter playMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic, container, false);
        anhxa( );
        GetData( );
        return view;
    }

    private void GetData() {
    }

    private void anhxa() {
        recylerViewPlaymusic = view.findViewById(R.id.recylerViewPlaymusic);
        if (PlayMusicAcivity.songs.size() > 0) {
            playMusicAdapter = new PlayMusicAdapter(getActivity( ), PlayMusicAcivity.songs);
            recylerViewPlaymusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recylerViewPlaymusic.setAdapter(playMusicAdapter);
        }
    }
}
