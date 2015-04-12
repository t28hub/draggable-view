package com.t28.draggableview.demo.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t28.draggableview.DraggableView;
import com.t28.draggableview.demo.R;
import com.t28.draggableview.demo.data.model.App;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends DraggableView.Adapter<AppAdapter.ItemViewHolder> {
    private final List<App> mApps;

    public AppAdapter() {
        super();
        mApps = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.linear_layout_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final App app = getItem(position);
        holder.bind(app);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    @Override
    public boolean move(int position1, int position2) {
        return false;
    }

    public void changeApps(List<App> apps) {
        mApps.clear();
        if (apps != null && apps.size() != 0) {
            mApps.addAll(apps);
        }
        notifyDataSetChanged();
    }

    private App getItem(int position) {
        return mApps.get(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconView;
        private final TextView mPrimaryTextView;
        private final TextView mSecondaryTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mIconView = (ImageView) itemView.findViewById(R.id.linear_layout_item_icon);
            mPrimaryTextView = (TextView) itemView.findViewById(R.id.linear_layout_item_primary_text);
            mSecondaryTextView = (TextView) itemView.findViewById(R.id.linear_layout_item_secondary_text);
        }

        public void bind(App app) {
            mIconView.setImageDrawable(app.getIcon());
            mPrimaryTextView.setText(app.getName());
            mSecondaryTextView.setText(app.getPackageName());
        }
    }
}