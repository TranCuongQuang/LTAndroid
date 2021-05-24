package group4.musicproject.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import group4.musicproject.R;

public class Fragment_DiskMusic extends Fragment {
    View view;
    CircleImageView circleImageViewDiskMusic;
    ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diskmusic, container, false);
        anhxa();
        return view;
    }

    private void anhxa() {
        circleImageViewDiskMusic = view.findViewById(R.id.circleImageViewDiskMusic);
        objectAnimator= ObjectAnimator.ofFloat(circleImageViewDiskMusic,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
    }

    public void PlayNhac(String hinhanh) {
        Picasso.with(getActivity()).load(hinhanh).into(circleImageViewDiskMusic);
    }
}
