package group4.musicproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.ListCategoryAdapter;
import group4.musicproject.Model.Category;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerViewListCategory;
    ArrayList<Category> categories;
    ListCategoryAdapter listCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        anhxa( );
        getDataCategory( );
    }

    private void anhxa() {
        recyclerViewListCategory = findViewById(R.id.recyclerViewListCategory);
        ;
    }

    private void getDataCategory() {
        DataService dataService = APIService.getService( );
        Call<List<Category>> callback = dataService.GetCategoryAll( );
        callback.enqueue(new Callback<List<Category>>( ) {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = (ArrayList<Category>) response.body( );
                listCategoryAdapter = new ListCategoryAdapter(ListCategoryActivity.this, categories);
                GridLayoutManager layoutManager = new GridLayoutManager(ListCategoryActivity.this, 1);
                recyclerViewListCategory.setLayoutManager(layoutManager);
                recyclerViewListCategory.setAdapter(listCategoryAdapter);

//                eventClick( );
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }
}