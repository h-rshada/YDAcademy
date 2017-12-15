package com.ydacademy.dell.Yashodeep2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class TabActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    Toast toast;
    @InjectView(R.id.img_back)
    ImageView imageback;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private long back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TestFragment(), "Test");
        adapter.addFragment(new SyllabusFragment(), "Syllabus");
        adapter.addFragment(new CareerGuidenceFragment(), "Career");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
               /* Intent intent = new Intent(TabActivity.this, MainActivity.class);
                startActivity(intent);*/
                break;
        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
