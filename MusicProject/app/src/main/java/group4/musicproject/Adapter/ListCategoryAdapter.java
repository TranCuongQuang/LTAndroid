package group4.musicproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import group4.musicproject.Model.Category;
import group4.musicproject.R;

public class ListCategoryAdapter extends  RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Category> categories;

    public ListCategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listcategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        Picasso.with(context).load(category.getHinhChuDe()).into(holder.imageViewListCategory);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewListCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewListCategory = itemView.findViewById(R.id.imageViewListCategory);

//            imageViewListCategory.setOnClickListener(new View.OnClickListener( ) {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(context, PlayMusicAcivity.class);
////                    intent.putExtra("song", songs.get(getPosition( )));
////                    context.startActivity(intent);
//                }
//            });
        }
    }
}
