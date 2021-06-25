package group4.musicproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.AlbumAllAdapter;
import group4.musicproject.Adapter.TopicCategoryAdapter;
import group4.musicproject.Model.Album;
import group4.musicproject.Model.Topic;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAlbum;
    Toolbar toolBarAlbum;
    ArrayList<Album> albums;
    AlbumAllAdapter albumAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        anhxa( );
        getAlbums( );
    }

    private void anhxa() {
        recyclerViewAlbum = findViewById(R.id.recyclerViewAlbum);
        toolBarAlbum = findViewById(R.id.toolBarAlbum);

        setSupportActionBar(toolBarAlbum);
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar( ).setTitle("Tất cả album");
        toolBarAlbum.setNavigationOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                finish( );
            }
        });
    }

    private void getAlbums() {
        DataService dataService = APIService.getService( );
        Call<List<Album>> callback = dataService.GetAlbums( );
        callback.enqueue(new Callback<List<Album>>( ) {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums = (ArrayList<Album>) response.body( );
                albumAllAdapter = new AlbumAllAdapter(AlbumActivity.this, albums);
                GridLayoutManager layoutManager = new GridLayoutManager(AlbumActivity.this, 2);
                recyclerViewAlbum.setLayoutManager(layoutManager);
                recyclerViewAlbum.setAdapter(albumAllAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }
}