package group4.musicproject.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.SearchAdapter;
import group4.musicproject.Adapter.TheBestLikeSongAdapter;
import group4.musicproject.Model.Song;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolBarSearch;
    RecyclerView recyclerViewSearch;
    TextView textViewSearch;

    ArrayList<Song> songs;
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        anhxa( );
        return view;
    }

    private void anhxa() {
        toolBarSearch = view.findViewById(R.id.toolBarSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        textViewSearch = view.findViewById(R.id.textViewSearch);

        ((AppCompatActivity) getActivity( )).setSupportActionBar(toolBarSearch);
        toolBarSearch.setTitle("Tìm kiếm");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.memuSeach);
        SearchView searchView = (SearchView) menuItem.getActionView( );
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GetDataSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void GetDataSearch(String keySearch) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongSearch(keySearch);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                if(songs.size()>0) {
                    searchAdapter = new SearchAdapter(getContext( ), songs);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext( ));
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewSearch.setLayoutManager(layoutManager);
                    recyclerViewSearch.setAdapter(searchAdapter);
                    textViewSearch.setVisibility(View.GONE);
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                }else {
                    textViewSearch.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
            }
        });
    }
}
