package group4.musicproject.Service;

import java.util.List;

import group4.musicproject.Model.Album;
import group4.musicproject.Model.Banner;
import group4.musicproject.Model.Category;
import group4.musicproject.Model.Playlist;
import group4.musicproject.Model.Song;
import group4.musicproject.Model.TopicCategory;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("ads")
    Call<List<Banner>> GetDataBanner();

    @GET("playlists")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("topics")
    Call<TopicCategory> GetTopicCategoryCurrentDay();

    @GET("albums")
    Call<List<Album>> GetAlbumCurrentDay();

    @GET("songs/favorite")
    Call<List<Song>> GetTheBestLikeSong();

    @FormUrlEncoded
    @POST("ads/song")
    Call<List<Song>> GetListSongByHotSong(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("playlist/song")
    Call<List<Song>> GetListSongByPlaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("category/song")
    Call<List<Song>> GetListSongByTopic(@Field("idtheloai") String idtheloai);

    @GET("topics/all")
    Call<List<Category>> GetCategoryAll();

    @FormUrlEncoded
    @POST("songs/search")
    Call<List<Song>> GetSongSearch(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("songs/like")
    Call<String> UpdateHotSong(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);
}
