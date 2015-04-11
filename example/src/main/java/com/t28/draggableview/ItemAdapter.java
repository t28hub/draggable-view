package com.t28.draggableview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t28.draggablelistview.DraggableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemAdapter extends DraggableListView.Adapter<ItemAdapter.ItemViewHolder> {
    private final List<String> mItems;
    private OnItemClickListener mItemClickListener;

    public ItemAdapter(List<String> items) {
        if (items == null || items.size() == 0) {
            mItems = Collections.emptyList();
        } else {
            mItems = new ArrayList<>(items);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final String title = getItem(position);
        holder.bind(title);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean move(int position1, int position2) {
        final int limit = mItems.size();
        if (position1 < 0 || position1 >= limit) {
            throw new IndexOutOfBoundsException();
        }
        if (position2 < 0 || position2 >= limit) {
            throw new IndexOutOfBoundsException();
        }

        mItems.add(position2, mItems.remove(position1));
        notifyItemMoved(position1, position2);

        return false;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    private String getItem(int position) {
        return mItems.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(String title);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTitleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener == null) {
                return;
            }

            final String title = getItem(getAdapterPosition());
            mItemClickListener.onItemClick(title);
        }

        public void bind(String title) {
            mTitleView.setText(title);
        }
    }
}
