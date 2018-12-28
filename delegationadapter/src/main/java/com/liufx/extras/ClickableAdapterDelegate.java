package com.liufx.extras;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liufx.delegationadapter.AdapterDelegate;
import com.liufx.delegationadapter.ItemData;

/**
 * @package: com.liufx.delegationadapter
 * @author: liufx
 * @date: 2018/12/26 10:50 AM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 可点击的代理适配器
 */
public abstract class ClickableAdapterDelegate<T, VH extends RecyclerView.ViewHolder> extends AdapterDelegate<T, VH> {
    @Override
    public void onBindViewHolder(final VH holder, int position, final T item) {
        holder.itemView.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
            public final void onClick(View v) {
                int currentPosition = getPosition(holder);
                if (ClickableAdapterDelegate.this.clickable(currentPosition)) {
                    if (item instanceof ItemData) {
                        onItemClick(v, (T) ((ItemData) item).getData(), currentPosition);
                    } else {
                        onItemClick(v, item, currentPosition);
                    }
                }

            }
        }));
        holder.itemView.setOnLongClickListener((View.OnLongClickListener) (new View.OnLongClickListener() {
            public final boolean onLongClick(View v) {
                int currentPosition = ClickableAdapterDelegate.this.getPosition(holder);
                if (ClickableAdapterDelegate.this.longClickable(currentPosition)) {
                    boolean click = false;
                    if (item instanceof ItemData) {
                        click = onItemLongClick(v, (T) ((ItemData) item).getData(), currentPosition);
                    } else {
                        click = onItemLongClick(v, item, currentPosition);
                    }
                    return click;
                } else {
                    return false;
                }
            }
        }));
    }

    /**
     * Whether the adapter item can click
     *
     * @param position
     * @return
     */
    public boolean clickable(int position) {
        return true;
    }

    /**
     * Whether the adapter item can long click
     *
     * @param position
     * @return
     */
    public boolean longClickable(int position) {
        return true;
    }

    /**
     * Called when a item view has been clicked.
     *
     * @param view
     * @param item
     * @param position
     */
    public void onItemClick(View view, T item, int position) {
    }

    /**
     * Called when a item view has been clicked and held.
     *
     * @param view
     * @param item
     * @param position
     * @return
     */
    public boolean onItemLongClick(View view, T item, int position) {
        return false;
    }

    /**
     * Get the position of ViewHolder
     *
     * @param holder
     * @return
     */
    private final int getPosition(VH holder) {
        return holder.getAdapterPosition();
    }

    public ClickableAdapterDelegate() {
    }

    public ClickableAdapterDelegate(String tag) {
        super(tag);
    }
}