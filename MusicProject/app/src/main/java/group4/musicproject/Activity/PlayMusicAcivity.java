package group4.musicproject.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import group4.musicproject.Adapter.ViewPagerPlayMusicAdapter;
import group4.musicproject.Fragment.Fragment_DiskMusic;
import group4.musicproject.Fragment.Fragment_PlayMusic;
import group4.musicproject.Model.Song;
import group4.musicproject.R;

public class PlayMusicAcivity extends AppCompatActivity {

    Toolbar toolBarListSong;
    ViewPager viewPager;
    TextView textViewTimeSong;
    SeekBar seekBarSong;
    TextView textViewTotalTime;
    ImageButton imageButtonSuffle;
    ImageButton imageButtonPreview;
    ImageButton imageButtonPlay;
    ImageButton imageButtonNext;
    ImageButton imageButtonRepeat;

    public static ArrayList<Song> songs = new ArrayList<>( );
    public static ViewPagerPlayMusicAdapter viewPagerPlayMusicAdapter;
    Fragment_DiskMusic fragment_diskMusic;
    Fragment_PlayMusic fragment_playMusic;
    MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder( ).permitAll( ).build( );
        StrictMode.setThreadPolicy(policy);

        DateIntent( );
        anhxa( );
        eventClick( );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed( );
        finish( );
        mediaPlayer.stop( );
        songs.clear( );
    }

    private void eventClick() {
        final Handler handler = new Handler( );
        handler.postDelayed(new Runnable( ) {
            @Override
            public void run() {
                if (viewPagerPlayMusicAdapter.getItem(1) != null) {
                    if (songs.size( ) > 0) {
                        fragment_diskMusic.PlayNhac(songs.get(0).getHinhBaiHat( ));
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imageButtonPlay.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying( )) {
                    mediaPlayer.pause( );
                    imageButtonPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start( );
                    imageButtonPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imageButtonRepeat.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imageButtonRepeat.setImageResource(R.drawable.iconsyned);
                        imageButtonSuffle.setImageResource(R.drawable.iconsuffle);
                    }

                    imageButtonRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imageButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imageButtonSuffle.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtonSuffle.setImageResource(R.drawable.iconshuffled);
                        imageButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    }

                    imageButtonSuffle.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                } else {
                    imageButtonSuffle.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });

        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener( ) {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress( ));
            }
        });

        imageButtonNext.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if (songs.size( ) > 0) {
                    if (mediaPlayer.isPlaying( ) || mediaPlayer != null) {
                        mediaPlayer.stop( );
                        mediaPlayer.release( );
                        mediaPlayer = null;
                    }

                    if (position < songs.size( )) {
                        imageButtonPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songs.size( );
                            }
                            position -= 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random( );
                            int index = random.nextInt(songs.size( ));
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        if (position > (songs.size( ) - 1)) {
                            position = 0;
                        }

                        new PlayMP3( ).execute(songs.get(position).getLinkBaiHat( ));
                        fragment_diskMusic.PlayNhac(songs.get(position).getHinhBaiHat( ));
                        getSupportActionBar( ).setTitle(songs.get(position).getTenBaiHat( ));
                        UpdateTime( );
                    }
                }

                imageButtonPreview.setClickable(false);
                imageButtonNext.setClickable(false);
                Handler handler1 = new Handler( );
                handler1.postDelayed(new Runnable( ) {
                    @Override
                    public void run() {
                        imageButtonPreview.setClickable(true);
                        imageButtonNext.setClickable(true);
                    }
                }, 3000);
            }
        });

        imageButtonPreview.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if (songs.size( ) > 0) {
                    if (mediaPlayer.isPlaying( ) || mediaPlayer != null) {
                        mediaPlayer.stop( );
                        mediaPlayer.release( );
                        mediaPlayer = null;
                    }

                    if (position < songs.size( )) {
                        imageButtonPlay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = songs.size( ) - 1;
                        }

                        if (repeat == true) {
                            position += 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random( );
                            int index = random.nextInt(songs.size( ));
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        new PlayMP3( ).execute(songs.get(position).getLinkBaiHat( ));
                        fragment_diskMusic.PlayNhac(songs.get(position).getHinhBaiHat( ));
                        getSupportActionBar( ).setTitle(songs.get(position).getTenBaiHat( ));
                        UpdateTime( );
                    }
                }

                imageButtonPreview.setClickable(false);
                imageButtonNext.setClickable(false);
                Handler handler1 = new Handler( );
                handler1.postDelayed(new Runnable( ) {
                    @Override
                    public void run() {
                        imageButtonPreview.setClickable(true);
                        imageButtonNext.setClickable(true);
                    }
                }, 3000);
            }
        });
    }

    private void anhxa() {
        toolBarListSong = findViewById(R.id.toolBarListSong);
        viewPager = findViewById(R.id.viewPager);
        textViewTimeSong = findViewById(R.id.textViewTimeSong);
        seekBarSong = findViewById(R.id.seekBarSong);
        textViewTotalTime = findViewById(R.id.textViewTotalTime);
        imageButtonSuffle = findViewById(R.id.imageButtonSuffle);
        imageButtonPreview = findViewById(R.id.imageButtonPreview);
        imageButtonPlay = findViewById(R.id.imageButtonPlay);
        imageButtonNext = findViewById(R.id.imageButtonNext);
        imageButtonRepeat = findViewById(R.id.imageButtonRepeat);

        setSupportActionBar(toolBarListSong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarListSong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop( );
                songs.clear( );
            }
        });
        toolBarListSong.setTitleTextColor(Color.WHITE);

        fragment_diskMusic = new Fragment_DiskMusic( );
        fragment_playMusic = new Fragment_PlayMusic( );
        viewPagerPlayMusicAdapter = new ViewPagerPlayMusicAdapter(getSupportFragmentManager( ));
        viewPagerPlayMusicAdapter.addFragment(fragment_diskMusic);
        viewPagerPlayMusicAdapter.addFragment(fragment_playMusic);
        viewPager.setAdapter(viewPagerPlayMusicAdapter);

        fragment_diskMusic = (Fragment_DiskMusic) viewPagerPlayMusicAdapter.getItem(0);
        if (songs.size( ) > 0) {
            getSupportActionBar( ).setTitle(songs.get(0).getTenBaiHat( ));
            new PlayMP3( ).execute(songs.get(0).getLinkBaiHat( ));
            imageButtonPlay.setImageResource(R.drawable.iconpause);
        }
    }

    private void DateIntent() {
        Intent intent = getIntent( );
        songs.clear( );
        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song song = intent.getParcelableExtra("song");
                songs.add(song);
//                Toast.makeText(this, song.getTenBaiHat( ), Toast.LENGTH_LONG).show( );
            }

            if (intent.hasExtra("songlist")) {
                ArrayList<Song> songslist = intent.getParcelableArrayListExtra("songlist");
                songs = songslist;
            }
        }
    }

    class PlayMP3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String bh) {
            super.onPostExecute(bh);
            try {
                mediaPlayer = new MediaPlayer( );
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener( ) {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop( );
                        mediaPlayer.reset( );
                    }
                });
                mediaPlayer.setDataSource(bh);
                mediaPlayer.prepare( );
            } catch (IOException e) {
                e.printStackTrace( );
            }

            mediaPlayer.start( );
            TimeSong( );
            UpdateTime( );
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration( )));
        seekBarSong.setMax(mediaPlayer.getDuration( ));
    }

    private void UpdateTime() {
        final Handler handler = new Handler( );
        handler.postDelayed(new Runnable( ) {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition( ));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition( )));

                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener( ) {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace( );
                            }
                        }
                    });
                }
            }
        }, 300);

        final Handler handler1 = new Handler( );
        handler1.postDelayed(new Runnable( ) {
            @Override
            public void run() {
                if (next == true) {
                    if (position < songs.size( )) {
                        imageButtonPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songs.size( );
                            }
                            position -= 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random( );
                            int index = random.nextInt(songs.size( ));
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        if (position > (songs.size( ) - 1)) {
                            position = 0;
                        }

                        new PlayMP3( ).execute(songs.get(position).getLinkBaiHat( ));
                        fragment_diskMusic.PlayNhac(songs.get(position).getHinhBaiHat( ));
                        getSupportActionBar( ).setTitle(songs.get(position).getTenBaiHat( ));
                    }

                    imageButtonRepeat.setClickable(false);
                    imageButtonNext.setClickable(false);
                    Handler handler1 = new Handler( );
                    handler1.postDelayed(new Runnable( ) {
                        @Override
                        public void run() {
                            imageButtonRepeat.setClickable(true);
                            imageButtonNext.setClickable(true);
                        }
                    }, 5000);

                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
