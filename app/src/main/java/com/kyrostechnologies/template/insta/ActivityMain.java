package com.kyrostechnologies.template.insta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kyrostechnologies.template.insta.adapter.PageFragmentAdapter;
import com.kyrostechnologies.template.insta.data.Tools;
import com.kyrostechnologies.template.insta.fragment.PageHomeFragment;
import com.kyrostechnologies.template.insta.fragment.PageExploreFragment;
import com.kyrostechnologies.template.insta.fragment.PageGalleryFragment;
import com.kyrostechnologies.template.insta.fragment.PageFriendFragment;
import com.kyrostechnologies.template.insta.fragment.PageProfileFragment;

public class ActivityMain extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private View parent_view;

    private PageFragmentAdapter adapter;

    private PageHomeFragment f_home;
    private PageExploreFragment f_explore;
    private PageGalleryFragment f_gallery;
    private PageFriendFragment f_friend;
    private PageProfileFragment f_profile;
    private static int[] imageResId = {
            R.drawable.tab_home,
            R.drawable.tab_explore,
            R.drawable.tab_gallery,
            R.drawable.tab_friend,
            R.drawable.tab_profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle("");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        setupTabClick();

        // for system bar in lollipop
        Tools.systemBarLolipop(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PageFragmentAdapter(getSupportFragmentManager());
        if (f_home == null) {
            f_home = new PageHomeFragment();
        }
        if (f_explore== null) {
            f_explore = new PageExploreFragment();
        }
        if (f_gallery == null) {
            f_gallery = new PageGalleryFragment();
        }
        if (f_friend == null) {
            f_friend = new PageFriendFragment();
        }
        if (f_profile == null) {
            f_profile = new PageProfileFragment();
        }
        adapter.addFragment(f_home, null);
        adapter.addFragment(f_explore, null);
        adapter.addFragment(f_gallery, null);
        adapter.addFragment(f_friend, null);
        adapter.addFragment(f_profile, null);
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(imageResId[0]);
        tabLayout.getTabAt(1).setIcon(imageResId[1]);
        tabLayout.getTabAt(2).setIcon(imageResId[2]);
        tabLayout.getTabAt(3).setIcon(imageResId[3]);
        tabLayout.getTabAt(4).setIcon(imageResId[4]);
    }

    private void setupTabClick() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                //actionbar.setTitle(adapter.getTitle(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    public void actionClick(View v) {
        f_profile.actionClick(v);
    }

}
