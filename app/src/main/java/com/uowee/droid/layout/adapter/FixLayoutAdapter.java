package com.uowee.droid.layout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uowee.droid.util.R;
import com.muse.tangram.adapter.DelegateAdapter;
import com.muse.tangram.helper.LayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class FixLayoutAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String text;

    public FixLayoutAdapter(Context context, LayoutHelper helper, String text) {
        this.context = context;
        this.helper = helper;
        this.inflater = LayoutInflater.from(context);
        this.text = text;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_text, null));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa191919);
        } else {
            holder.itemView.setBackgroundColor(0xcc666666);
        }
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.textView.setText(text);
    }

    public int getItemCount() {
        return 1;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewholder(View view) {
            super(view);
            textView = view.findViewById(R.id.fix_name);
        }
    }
}
