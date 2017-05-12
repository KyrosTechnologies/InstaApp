package com.kyrostechnologies.template.insta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kyrostechnologies.template.insta.R;
import com.kyrostechnologies.template.insta.model.Feed;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

public class ExploreGridAdapter extends RecyclerView.Adapter<ExploreGridAdapter.ViewHolder>{

    private List<Feed> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Feed obj, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;
        public MaterialRippleLayout lyt_parent;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExploreGridAdapter(Context ctx, List<Feed> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public ExploreGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_explore, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Feed p = items.get(position);
        holder.image.setImageResource(p.getPhoto());
        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, p, position);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}