package group4.musicproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.ListSongAdapter;
import group4.musicproject.Model.Album;
import group4.musicproject.Model.Banner;
import group4.musicproject.Model.Playlist;
import group4.musicproject.Model.Song;
import group4.musicproject.Model.Topic;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import group4.musicproject.R;

public class ListSongActivity extends AppCompatActivity {

    Banner hotSong;
    Playlist playlist;
    Topic topic;
    Album album;
    ArrayList<Song> songs;
    CoordinatorLayout corrdinatorLayout;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolBar;
    Toolbar toolBarListSong;
    ImageView imageViewListSong;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerViewListSong;
    ListSongAdapter listSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder( ).permitAll( ).build( );
        StrictMode.setThreadPolicy(policy);

        anhxa( );
        DateIntent( );

        init();
        if (hotSong != null && !hotSong.getId( ).equals("")) {
            setValueInView(hotSong.getTenBaiHat( ), hotSong.getHinhAnh( ));
            getDataHotSong(hotSong.getId( ).toString( ));
        }

        if (playlist != null && !playlist.getTen( ).equals("")) {
            setValueInView(playlist.getTen( ), playlist.getHinhPlaylist( ));
            getDataPlaylist(playlist.getId( ).toString( ));
        }

        if (topic != null && !topic.getTenTheLoai( ).equals("")) {
            setValueInView(topic.getTenTheLoai( ), topic.getHinhTheLoai( ));
            getDataTopic(topic.getId( ).toString( ));
        }

        if (album != null && !album.getTenAlbum( ).equals("")) {
            setValueInView(album.getTenAlbum( ), album.getHinhAlbum( ));
            getDataAblum(album.getId( ).toString( ));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed( );
        finish( );
    }

    private void anhxa() {
        corrdinatorLayout = findViewById(R.id.corrdinatorLayout);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolBar = findViewById(R.id.collapsingToolBar);
        toolBarListSong = findViewById(R.id.toolBarListSong);
        imageViewListSong = findViewById(R.id.imageViewListSong);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerViewListSong = findViewById(R.id.recyclerViewListSong);

        floatingActionButton.setEnabled(false);
    }

    private void setValueInView(String nameSong, String picture) {
        collapsingToolBar.setTitle(nameSong);
        try {
            URL url = new URL(picture);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection( ).getInputStream( ));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources( ), bitmap);
            collapsingToolBar.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace( );
        } catch (IOException e) {
            e.printStackTrace( );
        }
        Picasso.with(this).load(picture).into(imageViewListSong);
    }

    private void getDataPlaylist(String idPlaylist) {
        DataService dataService = APIService.getService( );
        Call<List<Song>> callback = dataService.GetListSongByPlaylist(idPlaylist);
        callback.enqueue(new Callback<List<Song>>( ) {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body( );
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(layoutManager);
                recyclerViewListSong.setAdapter(listSongAdapter);

                eventClick( );
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }

    private void getDataHotSong(String idhotsong) {
        DataService dataService = APIService.getService( );
        Call<List<Song>> callback = dataService.GetListSongByHotSong(idhotsong);
        callback.enqueue(new Callback<List<Song>>( ) {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body( );
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
//                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewListSong.setLayoutManager(layoutManager);
                recyclerViewListSong.setAdapter(listSongAdapter);

                eventClick( );
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }

    private void getDataTopic(String idtheloai) {
        DataService dataService = APIService.getService( );
        Call<List<Song>> callback = dataService.GetListSongByTopic(idtheloai);
        callback.enqueue(new Callback<List<Song>>( ) {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body( );
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(layoutManager);
                recyclerViewListSong.setAdapter(listSongAdapter);

                eventClick( );
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }

    private void getDataAblum(String idalbum) {
        DataService dataService = APIService.getService( );
        Call<List<Song>> callback = dataService.GetListSongByAlbum(idalbum);
        callback.enqueue(new Callback<List<Song>>( ) {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body( );
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(layoutManager);
                recyclerViewListSong.setAdapter(listSongAdapter);

//                eventClick( );
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }

    private void init() {
        setSupportActionBar(toolBarListSong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarListSong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolBar.setExpandedTitleColor(Color.WHITE);
        collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void DateIntent() {
        Intent intent = getIntent( );
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                hotSong = (Banner) intent.getSerializableExtra("banner");
//                Toast.makeText(this, hotSong.getTenBaiHat( ), Toast.LENGTH_LONG).show( );
            }

            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
//                Toast.makeText(this, playlist.getTen( ), Toast.LENGTH_LONG).show( );
            }

            if (intent.hasExtra("topic")) {
                topic = (Topic) intent.getSerializableExtra("topic");
//                Toast.makeText(this, playlist.getTen( ), Toast.LENGTH_LONG).show( );
            }

            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
//                Toast.makeText(this, playlist.getTen( ), Toast.LENGTH_LONG).show( );
            }
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this, PlayMusicAcivity.class);
                intent.putExtra("songlist", songs);
                startActivity(intent);
            }
        });
    }
}
