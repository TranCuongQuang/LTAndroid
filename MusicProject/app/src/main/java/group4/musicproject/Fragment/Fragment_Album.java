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
import java.util.List;

import group4.musicproject.Adapter.AlbumAdapter;
import group4.musicproject.Model.Album;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {
    View view;
    ArrayList<Album> albums;
    AlbumAdapter albumAdapter;
    TextView textViewTitleAlbum;
    TextView textViewViewMoreAlbum;
    RecyclerView recylerViewAlbum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        textViewTitleAlbum = view.findViewById(R.id.textViewTitleAlbum);
        textViewViewMoreAlbum = view.findViewById(R.id.textViewViewMoreAlbum);
        recylerViewAlbum = view.findViewById(R.id.recylerViewAlbum);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetAlbumCurrentDay();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getContext(), albums);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recylerViewAlbum.setLayoutManager(layoutManager);
                recylerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
            }
        });
    }
}
