package com.uowee.droid.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uowee.droid.util.R;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.LayoutHelper;

import java.util.List;

/**
 * Created by GuoWee on 2018/1/26.
 */

public class GridViewAdapter extends DelegateAdapter.Adapter {

    private Context mContext;
    private List<String> titles;
    private List<Integer> images;
    private LayoutInflater inflater;
    private LayoutHelper helper;


    public GridViewAdapter(Context context, LayoutHelper helper, List<Integer> images, List<String> titles) {
        this.mContext = context;
        this.images = images;
        this.titles = titles;
        this.helper = helper;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout_item, parent, false);
        return new MyHoldView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        final MyHoldView viewHolder = (MyHoldView) holder;
        if (onGridViewItemListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    onGridViewItemListener.onItemClick(view, position);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    onGridViewItemListener.onItemLongClick(view, position);
                    return true;
                }
            });
        }
        viewHolder.textView.setText(titles.get(position));
        viewHolder.imageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyHoldView extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyHoldView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_item_textview);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_item_imageview);
        }
    }

    private OnGridViewItemListener onGridViewItemListener;

    public void setOnGridViewItemListener(OnGridViewItemListener listener) {
        this.onGridViewItemListener = listener;
    }

    public interface OnGridViewItemListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


}