package com.liufx.extras.span;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liufx.delegationadapter.AdapterDelegate;
import com.liufx.delegationadapter.DelegationAdapter;

/**
 * @package: com.liufx.extras.span
 * @author: liufx
 * @date: 2018/12/26 2:21 PM
 * @description: 简要描述
 */
public class SpanDelegationAdapter extends DelegationAdapter {
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView != null) {
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {

                ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        AdapterDelegate delegate = getDelegatesManager().getDelegate(getItemViewType(position));

                        if (delegate != null && (delegate instanceof SpanAdapterDelegate)) {
                            return ((SpanAdapterDelegate) delegate).getSpanSize();
                        } else {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                    }
                });
            }
        }

    }
}
