package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Activity.ListSongActivity;
import group4.musicproject.Model.Category;
import group4.musicproject.Model.Topic;
import group4.musicproject.R;


public class TopicCategoryAdapter extends RecyclerView.Adapter<TopicCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Topic> topics;

    public TopicCategoryAdapter(Context context, ArrayList<Topic> categories) {
        this.context = context;
        this.topics = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_topiccategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topics.get(position);
        Picasso.with(context).load(topic.getHinhTheLoai( )).into(holder.imageViewTopicCategory);
        holder.textViewTopicCategory.setText(topic.getTenTheLoai( ));
    }

    @Override
    public int getItemCount() {
        return topics.size( );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewTopicCategory;
        TextView textViewTopicCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewTopicCategory = itemView.findViewById(R.id.imageViewTopicCategory);
            textViewTopicCategory = itemView.findViewById(R.id.textViewTopicCategory);
            imageViewTopicCategory.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("topic", topics.get(getPosition( )));
                    context.startActivity(intent);
                }
            });
        }
    }
}
