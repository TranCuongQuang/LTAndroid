package group4.musicproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    Toolbar toolBarListCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        anhxa( );
        getDataCategory( );
    }

    private void anhxa() {
        recyclerViewListCategory = findViewById(R.id.recyclerViewListCategory);
        toolBarListCategory = findViewById(R.id.toolBarListCategory);

        setSupportActionBar(toolBarListCategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolBarListCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        toolBarListCategory.setTitleTextColor(Color.WHITE);
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