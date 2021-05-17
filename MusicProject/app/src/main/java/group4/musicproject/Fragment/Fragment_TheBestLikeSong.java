package group4.musicproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.TheBestLikeSongAdapter;
import group4.musicproject.Model.Song;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import group4.musicproject.R;

public class Fragment_TheBestLikeSong extends Fragment {
    View view;
    ArrayList<Song> songs;
    TheBestLikeSongAdapter adapter;
    RecyclerView recyclerViewTheBestLikeSong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thebestlikesong, container, false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        recyclerViewTheBestLikeSong = view.findViewById(R.id.recyclerViewTheBestLikeSong);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetTheBestLikeSong();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                adapter = new TheBestLikeSongAdapter(getContext(), songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewTheBestLikeSong.setLayoutManager(layoutManager);
                recyclerViewTheBestLikeSong.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
            }
        });
    }
}
