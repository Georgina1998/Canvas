package com.example.canvas.Adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canvas.Interface.ToolsListener;
import com.example.canvas.Interface.ViewClick;
import com.example.canvas.Model.ToolsItem;
import com.example.canvas.R;
import com.example.canvas.viewholder.ToolsViewHolder;

import java.util.List;

public class ToolsAdapter extends RecyclerView.Adapter<ToolsViewHolder> {

    private List<ToolsItem> mToolsItemList;
    private int selected = -1;
    private ToolsListener mListener;

    public ToolsAdapter(List<ToolsItem> toolsItemList, ToolsListener listener) {
        mToolsItemList = toolsItemList;
        mListener = listener;
    }


    @NonNull
    @Override
    public ToolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tools_item, parent, false);

        return new ToolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolsViewHolder holder, int position) {
        holder.name.setText(mToolsItemList.get(position).getName());
        holder.icon.setImageResource(mToolsItemList.get(position).getIcon());

        holder.setViewClick(new ViewClick() {
            @Override
            public void onClick(int pos) {
                selected = pos;
                mListener.onSelected(mToolsItemList.get(pos).getName());
                notifyDataSetChanged();
            }
        });

        if(selected == position){
            holder.name.setTypeface(holder.name.getTypeface(), Typeface.BOLD);
        }else{
            holder.name.setTypeface(Typeface.DEFAULT);
        }
    }

    @Override
    public int getItemCount() {
        return mToolsItemList.size();
    }
}
