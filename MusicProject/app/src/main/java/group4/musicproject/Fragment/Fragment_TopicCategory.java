package group4.musicproject.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Activity.ListCategoryActivity;
import group4.musicproject.Activity.ListSongActivity;
import group4.musicproject.Model.Category;
import group4.musicproject.Model.Topic;
import group4.musicproject.Model.TopicCategory;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TopicCategory extends Fragment {
    View view;
    TopicCategory topicCategoryToday;
    TextView textViewTitle;
    TextView textViewViewMore;
    HorizontalScrollView horizontalScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topiccategory, container, false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewViewMore = view.findViewById(R.id.textViewViewMore);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);

        textViewViewMore.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TopicCategory> callback = dataService.GetTopicCategoryCurrentDay();
        callback.enqueue(new Callback<TopicCategory>() {
            @Override
            public void onResponse(Call<TopicCategory> call, Response<TopicCategory> response) {
                topicCategoryToday = response.body();

                final ArrayList<Topic> arrTopic = new ArrayList<>();
                arrTopic.addAll(topicCategoryToday.getTheLoai());

                final ArrayList<Category> arrCate = new ArrayList<>();
                arrCate.addAll(topicCategoryToday.getChuDe());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10, 20, 10, 30);

                for (int i = 0; i < arrTopic.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    TextView textView = new TextView(getActivity());
                    textView.setTextSize(40);
                    textView.setTextColor(Color.WHITE);
                    textView.setGravity(Gravity.CENTER);

                    if (arrTopic.get(i).getId() != null) {
                        textView.setText(arrTopic.get(i).getTenTheLoai());
                        Picasso.with(getActivity()).load(arrTopic.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                }

                for (int j = 0; j < arrCate.size(); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    final ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (arrCate.get(j).getId() != null) {
                        Picasso.with(getActivity()).load(arrCate.get(j).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener( ) {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra("topic", arrTopic.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TopicCategory> call, Throwable t) {
            }
        });
    }
}