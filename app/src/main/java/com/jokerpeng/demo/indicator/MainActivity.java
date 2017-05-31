package com.jokerpeng.demo.indicator;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> list_banner;
    private IndicatorView indicatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        list_banner = new ArrayList<>();
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.mipmap.banner1);
        iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.mipmap.banner2);
        iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.mipmap.banner3);
        iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        list_banner.add(iv1);
        list_banner.add(iv2);
        list_banner.add(iv3);
        MyAdapter adapter = new MyAdapter();
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp);
        indicatorView = (IndicatorView) findViewById(R.id.indicator);
    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list_banner.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list_banner.get(position));
            return list_banner.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list_banner.get(position));
            super.destroyItem(container, position, object);
        }
    }
}
