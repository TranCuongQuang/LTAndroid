package group4.musicproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import group4.musicproject.Adapter.MainViewPagerAdapter;
import group4.musicproject.Fragment.Fragment_Tim_Kiem;
import group4.musicproject.Fragment.Fragment_Trang_Chu;
import group4.musicproject.R;

//import  group4.musicproject.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    //ActivityMainBinding binding;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AnhXa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm kiếm");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
//        binding.myViewPager.setAdapter(mainViewPagerAdapter);
//        binding.myTabLayout.setupWithViewPager(binding.myViewPager);
//        binding.myTabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
//        binding.myTabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    @SuppressLint("WrongViewCast")
    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}