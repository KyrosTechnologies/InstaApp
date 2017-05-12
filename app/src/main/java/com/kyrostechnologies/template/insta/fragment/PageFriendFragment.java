package com.kyrostechnologies.template.insta.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kyrostechnologies.template.insta.R;
import com.kyrostechnologies.template.insta.adapter.FollowingListAdapter;
import com.kyrostechnologies.template.insta.adapter.YouListAdapter;
import com.kyrostechnologies.template.insta.data.Constant;
import com.kyrostechnologies.template.insta.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class PageFriendFragment extends Fragment {

    private View view;
    private TabLayout friend_tabs;
    private RecyclerView recyclerView;
    private List<Friend> items_you = new ArrayList<>();
    private List<Friend> items_following = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_fragment_friend, container, false);

        // activate fragment menu
        setHasOptionsMenu(true);

        List<Friend> items = Constant.getFriendsData(getActivity());
        items_you = items.subList(0, 4);
        items_following = items.subList(4, items.size()-1);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        friend_tabs = (TabLayout) view.findViewById(R.id.friend_tabs);
        friend_tabs.addTab(friend_tabs.newTab().setText("FOLLOWING"),true);
        friend_tabs.addTab(friend_tabs.newTab().setText("YOU"));

        friend_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    showListFollowing();
                }else if(tab.getPosition()==1){
                    showListYou();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });
        showListFollowing();
        return view;
    }

    private void showListFollowing(){
        FollowingListAdapter mAdapter = new FollowingListAdapter(getActivity(), items_following);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FollowingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Friend obj, int position) {

            }
        });
    }

    private void showListYou(){
        YouListAdapter mAdapter = new YouListAdapter(getActivity(), items_you);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new YouListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Friend obj, int position) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_friend, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Snackbar.make(view, item.getTitle() + " Clicked", Snackbar.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
