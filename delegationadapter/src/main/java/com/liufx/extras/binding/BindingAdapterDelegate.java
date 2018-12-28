package com.liufx.extras.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liufx.delegationadapter.ItemData;
import com.liufx.extras.ClickableAdapterDelegate;

/**
 * @package: com.liufx.extras.binding
 * @author: liufx
 * @date: 2018/12/26 2:59 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 简要描述
 */
public abstract class BindingAdapterDelegate<T> extends ClickableAdapterDelegate<T, BindingViewHolder> {
    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.getLayoutRes(), parent, false);
        View view = binding.getRoot();
        BindingViewHolder holder = new BindingViewHolder(view);
        holder.setBinding(binding);
        this.configureViewHolder(holder);
        return holder;
    }


    @Override
    public void onBindViewHolder(BindingViewHolder viewHolder, int position, T item) {
        super.onBindViewHolder(viewHolder, position, item);
        if (item instanceof ItemData) {
            this.setVariable(viewHolder.getBinding(), (T)((ItemData) item).getData(), position);
        } else {
            this.setVariable(viewHolder.getBinding(), item, position);
        }
        viewHolder.getBinding().executePendingBindings();
    }


    public void configureViewHolder(BindingViewHolder holder) {
    }

    public abstract void setVariable(ViewDataBinding viewDataBinding, T item, int position);

    public BindingAdapterDelegate() {
    }

    public BindingAdapterDelegate(String tag) {
        super(tag);
    }
}
