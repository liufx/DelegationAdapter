package com.liufx.extras.span;

import android.support.v7.widget.RecyclerView;

import com.liufx.extras.ClickableAdapterDelegate;

/**
 * @package: com.liufx.extras.span
 * @author: liufx
 * @date: 2018/12/26 2:17 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 简要描述
 */
public abstract class SpanAdapterDelegate<T, VH extends RecyclerView.ViewHolder> extends ClickableAdapterDelegate<T, VH> {

    public int spanSize = 1;

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public SpanAdapterDelegate() {
    }

    public SpanAdapterDelegate(String tag) {
        super(tag);
    }
}
