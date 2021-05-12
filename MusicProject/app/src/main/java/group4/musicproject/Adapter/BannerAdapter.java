package group4.musicproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import group4.musicproject.Model.Banner;
import group4.musicproject.R;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Banner> arrHotSong;

    public BannerAdapter(Context context, ArrayList<Banner> arrHotSong) {
        this.context = context;
        this.arrHotSong = arrHotSong;
    }

    @Override
    public int getCount() {
        return arrHotSong.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.line_banner, null);
        ImageView imageviewbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imageviewbanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleBanner = view.findViewById(R.id.txtTitleBanner);
        TextView txtContent = view.findViewById(R.id.txtContent);
//        LineBannerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.line_banner, container, false);
//
        Picasso.with(context).load(arrHotSong.get(position).getHinhAnh()).into(imageviewbackgroundbanner);
        Picasso.with(context).load(arrHotSong.get(position).getHinhBaiHat()).into(imageviewbanner);
        txtTitleBanner.setText(arrHotSong.get(position).getTenBaiHat());
        txtContent.setText(arrHotSong.get(position).getNoiDung());

        container.addView(view);
//
//        binding.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ListSongActivity.class);
//                intent.putExtra("banner", arrHotSong.get(position));
//                context.startActivity(intent);
//            }
//        });
//
//        container.addView(binding.getRoot());
//        return binding.getRoot();

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

