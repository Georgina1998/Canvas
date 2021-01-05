package com.example.canvas.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canvas.Interface.ViewClick;
import com.example.canvas.R;

public class ToolsViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView name;

    private ViewClick mViewClick;

    public void setViewClick(ViewClick viewClick){
        this.mViewClick = viewClick;
    }

    public ToolsViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.tools_icon);
        name = itemView.findViewById(R.id.tools_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewClick.onClick(getAdapterPosition());
            }
        });
    }

}
