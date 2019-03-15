package com.course.recyclerview3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ItemViewHolder extends RecyclerView.ViewHolder implements ItemCallback {
    public abstract int getIndex();

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
