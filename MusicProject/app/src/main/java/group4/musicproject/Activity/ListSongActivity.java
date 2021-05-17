package group4.musicproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import group4.musicproject.Adapter.AlbumAdapter;
import group4.musicproject.Adapter.ListSongAdapter;
import group4.musicproject.Adapter.TheBestLikeSongAdapter;
import group4.musicproject.Model.Banner;
import group4.musicproject.Model.Song;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import group4.musicproject.R;

public class ListSongActivity extends AppCompatActivity {

    Banner hotSong;
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
        DateIntent();
        anhxa();
//        init();
        if (hotSong != null && !hotSong.getId().equals("")) {
            setValueInView(hotSong.getTenBaiHat(), hotSong.getHinhAnh());
            getDataHotSong(hotSong.getId().toString());
        }
    }

    private void anhxa() {
        corrdinatorLayout = findViewById(R.id.corrdinatorLayout);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolBar = findViewById(R.id.collapsingToolBar);
        toolBarListSong = findViewById(R.id.toolBarListSong);
        imageViewListSong = findViewById(R.id.imageViewListSong);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerViewListSong = findViewById(R.id.recyclerViewListSong);
    }

    private void setValueInView(String nameSong, String picture) {
//        collapsingToolBar.setTitle(nameSong);
        try {
            URL url = new URL(picture);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolBar.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(picture).into(imageViewListSong);
    }

    private void getDataHotSong(String idhotsong) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetListSongByHotSong(idhotsong);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewListSong.setLayoutManager(layoutManager);
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString());
            }
        });
    }

//    private void init() {
//        setSupportActionBar(toolBarListSong);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolBarListSong.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        collapsingToolBar.setExpandedTitleColor(Color.WHITE);
//        collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE);
//    }

    private void DateIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                hotSong = (Banner) intent.getSerializableExtra("banner");
                Toast.makeText(this, hotSong.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
