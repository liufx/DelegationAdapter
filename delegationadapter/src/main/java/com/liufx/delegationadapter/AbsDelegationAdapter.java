package com.liufx.delegationadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @package: com.liufx.delegationadapter
 * @author: liufx
 * @date: 2018/12/26 12:15 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: **The base DelegationAdapter**
 */
public abstract class AbsDelegationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AdapterDelegatesManager delegatesManager;

    public AbsDelegationAdapter(AdapterDelegatesManager delegatesManager) {
        this.delegatesManager = delegatesManager;
    }

    public AbsDelegationAdapter() {
        delegatesManager = new AdapterDelegatesManager();
    }

    public AdapterDelegatesManager getDelegatesManager() {
        return delegatesManager;
    }

    public void setDelegatesManager(AdapterDelegatesManager delegatesManager) {
        this.delegatesManager = delegatesManager;
    }

    public void addDelegate(AdapterDelegate<?, ?> delegate) {
        delegatesManager.addDelegate(delegate, delegate.getTag());
    }

    public void addDelegate(AdapterDelegate<?, ?> delegate, String tag) {
        delegate.setTag(tag);
        delegatesManager.addDelegate(delegate, tag);
    }

    public void setFallbackDelegate(AdapterDelegate<?, ?> delegate) {
        delegatesManager.setFallbackDelegate((AdapterDelegate<Object, RecyclerView.ViewHolder>) delegate);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return delegatesManager.onCreateViewHolder(viewGroup, viewType);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        delegatesManager.onBindViewHolder(viewHolder, position, getItem(position));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, position);
        delegatesManager.onBindViewHolder(holder, position, payloads, getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(getItem(position), position);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        delegatesManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return delegatesManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        delegatesManager.onViewAttachedToWindow(holder);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        delegatesManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        delegatesManager.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        delegatesManager.onViewDetachedFromWindow(holder);
    }

    public abstract Object getItem(int postion);
}
