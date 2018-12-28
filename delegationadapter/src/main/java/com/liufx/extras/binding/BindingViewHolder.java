package com.liufx.extras.binding;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @package: com.liufx.extras.binding
 * @author: liufx
 * @date: 2018/12/26 2:50 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 简要描述
 */
public class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public BindingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public <T extends ViewDataBinding> T getBinding() {
        if (this.binding == null) {
            throw new ClassCastException("null cannot be cast to non-null type T");
        } else {
            return (T) binding;
        }
    }

    public final void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
