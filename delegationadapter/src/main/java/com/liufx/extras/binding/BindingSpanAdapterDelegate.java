package com.liufx.extras.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liufx.delegationadapter.ItemData;
import com.liufx.extras.span.SpanAdapterDelegate;

/**
 * @package: com.liufx.extras.binding
 * @author: liufx
 * @date: 2018/12/26 4:21 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 简要描述
 */
public abstract class BindingSpanAdapterDelegate<T> extends SpanAdapterDelegate<T, BindingViewHolder> {
    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.getLayoutRes(), parent, false);
        View view = binding.getRoot();
        BindingViewHolder holder = new BindingViewHolder(view);
        holder.setBinding(binding);
        return holder;
    }


    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position, T item) {
        super.onBindViewHolder(holder, position, item);
        if (item instanceof ItemData) {
            this.setVariable(holder.getBinding(), item, position);
        } else {
            this.setVariable(holder.getBinding(), item, position);
        }

        holder.getBinding().executePendingBindings();
    }


    public abstract void setVariable(ViewDataBinding viewDataBinding, T item, int postion);

    public BindingSpanAdapterDelegate() {
    }

    public BindingSpanAdapterDelegate(String tag) {
        super(tag);
    }
}
