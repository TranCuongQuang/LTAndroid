package group4.musicproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Model.Banner;
import group4.musicproject.Model.Song;
import group4.musicproject.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import group4.musicproject.R;
public class ListSongActivity extends AppCompatActivity {

    Banner hotSong;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_list_song);
        DateIntent();
//        init();
//        if(hotSong != null && !hotSong.getId().equals("")){
//            setValueInView(hotSong.getTenBaiHat(), hotSong.getHinhAnh());
//            getDataHotSong(hotSong.getId());
//        }
    }

//    private void setValueInView(String nameSong, String picture) {
//        binding.collapsingToolBar.setTitle(nameSong);
//        try {
//            URL url = new URL(picture);
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//            binding.collapsingToolBar.setBackground(bitmapDrawable);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Picasso.with(this).load(picture).into(binding.imageViewListSong);
//    }
//
//    private void getDataHotSong(String idhotsong) {
//        DataService dataService = APIService.getService();
//        Call<List<Song>> callback = dataService.GetListSongByHotSong(idhotsong);
//        callback.enqueue(new Callback<List<Song>>() {
//            @Override
//            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
//                songs = (ArrayList<Song>) response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Song>> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void init() {
//        setSupportActionBar(binding.toolBarListSong);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //set icon
//        //binding.toolBarListSong.setNavigationIcon();
//        binding.toolBarListSong.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        binding.collapsingToolBar.setExpandedTitleColor(Color.WHITE);
//        binding.collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE);
//    }

    private void DateIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                hotSong = (Banner) intent.getSerializableExtra("banner");
                Toast.makeText(this, hotSong.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
