package group4.musicproject.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Adapter.ListCategoryAdapter;
import group4.musicproject.Adapter.TopicCategoryAdapter;
import group4.musicproject.Model.Category;
import group4.musicproject.Model.Topic;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicCategoryActivity extends AppCompatActivity {

    Category category;
    RecyclerView recyclerViewTopicCategory;
    ArrayList<Topic> topics;
    TopicCategoryAdapter topicCategoryAdapter;
    Toolbar toolBarTopicCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_category);
        
        GetContent();
        anhxa( );
        getTopicCategory();
    }

    private void GetContent() {
        Intent  intent = getIntent();
        if(intent.hasExtra("chude")){
            category = (Category) intent.getSerializableExtra("chude");
        }
    }

    private void anhxa() {
        recyclerViewTopicCategory = findViewById(R.id.recyclerViewTopicCategory);
        toolBarTopicCategory = findViewById(R.id.toolBarTopicCategory);

        setSupportActionBar(toolBarTopicCategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category.getTenChuDe());
        toolBarTopicCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getTopicCategory() {
        DataService dataService = APIService.getService( );
        Call<List<Topic>> callback = dataService.GetTopicCategory( category.getId().toString());
        callback.enqueue(new Callback<List<Topic>>( ) {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                topics = (ArrayList<Topic>) response.body( );
                topicCategoryAdapter = new TopicCategoryAdapter(TopicCategoryActivity.this, topics);
                GridLayoutManager layoutManager = new GridLayoutManager(TopicCategoryActivity.this, 2);
                recyclerViewTopicCategory.setLayoutManager(layoutManager);
                recyclerViewTopicCategory.setAdapter(topicCategoryAdapter);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                Log.e("??????????????????????er", t.toString( ));
            }
        });
    }
}