package com.kyrostechnologies.template.insta.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kyrostechnologies.template.insta.R;
import com.kyrostechnologies.template.insta.adapter.ExploreGridAdapter;
import com.kyrostechnologies.template.insta.adapter.FeedListAdapter;
import com.kyrostechnologies.template.insta.data.Constant;
import com.kyrostechnologies.template.insta.data.GlobalVariable;
import com.kyrostechnologies.template.insta.data.Tools;
import com.kyrostechnologies.template.insta.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class PageExploreFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ExploreGridAdapter mGridAdapter;
    private FeedListAdapter mListAdapter;
    private GlobalVariable global;
    private int selected_pos = 0;
    private List<Feed> items = new ArrayList<>();
    private MenuItem menuItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_fragment_explore, container, false);

        // activate fragment menu
        setHasOptionsMenu(true);
        global = (GlobalVariable) getActivity().getApplication();
        items = Constant.getRandomExploreFeed(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (global.isGrid_mode()) {
            switchGridMode();
        } else {
            switchListMode();
        }
        return view;
    }

    private void switchGridMode() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Tools.getGridExplorerCount(getActivity())));
        recyclerView.setHasFixedSize(true);
        recyclerView.setPadding(2, 2, 2, 2);

        //set data and list adapter
        mGridAdapter = new ExploreGridAdapter(getActivity(), items);
        recyclerView.setAdapter(mGridAdapter);
        mGridAdapter.setOnItemClickListener(new ExploreGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Feed obj, int position) {
                selected_pos = position;
                switchListMode();
                menuItem.setIcon(R.drawable.ic_menu_grid);
            }
        });
        global.setGrid_mode(true);
    }

    private void switchListMode() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setPadding(0, 0, 0, 0);
        recyclerView.scrollToPosition(selected_pos);

        mListAdapter = new FeedListAdapter(getActivity(), items);
        recyclerView.setAdapter(mListAdapter);
        global.setGrid_mode(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_explore, menu);
        this.menuItem = menu.getItem(1);
        if (global.isGrid_mode()) {
            menuItem.setIcon(R.drawable.ic_menu_list);
        } else {
            menuItem.setIcon(R.drawable.ic_menu_grid);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_mode) {
            if (global.isGrid_mode()) {
                switchListMode();
                item.setIcon(R.drawable.ic_menu_grid);
            } else {
                switchGridMode();
                item.setIcon(R.drawable.ic_menu_list);
            }
        } else {
            Snackbar.make(view, item.getTitle() + " Clicked", Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
