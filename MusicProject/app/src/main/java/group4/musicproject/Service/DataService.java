package group4.musicproject.Service;

import java.util.List;

import group4.musicproject.Model.Banner;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("ads")
    Call<List<Banner>> GetDataBanner();
}
