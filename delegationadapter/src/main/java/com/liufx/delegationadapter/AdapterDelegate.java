package com.liufx.delegationadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @package: com.liufx.delegationadapter
 * @author: liufx
 * @date: 2018/12/26 10:18 AM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 所有的委托都有相同的方法，为了方便定义一个接口
 */
public abstract class AdapterDelegate<T,VH extends RecyclerView.ViewHolder> {
    public final static String DEFAULT_TAG = "";
    private String tag = DEFAULT_TAG;

    public AdapterDelegate() {
    }

    public AdapterDelegate(String tag) {
        if (tag.isEmpty()) {
            String name = AdapterDelegate.class.getName();
            throw new NullPointerException("The tag of " + name + " is null.");
        }
        this.tag = tag;
    }

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param item     The data source of the Adapter
     * @param position The position in the datasource
     * @return true, if this item is responsible,  otherwise false
     */
    // 查找委托时调用的方法，返回自己能处理的类型即可。
    public boolean isForViewType(T item, int position) {
        return true;
    }

    /**
     * Creates the  [RecyclerView.ViewHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return The new instantiated [RecyclerView.ViewHolder]
     */
    // 用于委托Adapter的onCreateViewHolder方法
    public abstract VH onCreateViewHolder(ViewGroup parent);

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the datas source set
     *
     * @param holder   The [RecyclerView.ViewHolder] to bind
     * @param position The position in the datasource
     * @param item     The data source
     */
    // 用于委托Adapter的onBindViewHolder方法
    public abstract void onBindViewHolder(VH holder, int position, T item);

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the datas source set
     *
     * @param holder   The [RecyclerView.ViewHolder] to bind
     * @param position The position in the datasource
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full update.
     * @param item     The data source
     */
    public void onBindViewHolder(VH holder, int position, List<T> payloads, T item) {
    }

    /**
     * Called when a view created by this adapter has been recycled.
     * <p>
     * <p>
     * <p>
     * A view is recycled when a [RecyclerView.LayoutManager] decides that it no longer
     * needs to be attached to its parent [RecyclerView]. This can be because it has
     * fallen out of visibility or a set of cached views represented by views still
     * attached to the parent RecyclerView. If an item view has large or expensive data
     * bound to it such as large bitmaps, this may be a good place to release those
     * resources.
     * <p>
     * <p>
     * RecyclerView calls this method right before clearing ViewHolder's internal data and
     * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
     * before being recycled, you can call [RecyclerView.ViewHolder.getAdapterPosition] to
     * get
     * its adapter position.
     *
     * @param holder The ViewHolder for the view being recycled
     */
    public void onViewRecycled(VH holder) {
    }

    /**
     * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
     * due to its transient state. Upon receiving this callback, Adapter can clear the
     * animation(s) that effect the View's transient state and return `true` so that
     * the View can be recycled. Keep in mind that the View in question is already removed from
     * the RecyclerView.
     * <p>
     * <p>
     * In some cases, it is acceptable to recycle a View although it has transient state. Most
     * of the time, this is a case where the transient state will be cleared in
     * [RecyclerView.Adapter.onBindViewHolder] call when View is
     * rebound to a new position.
     * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
     * value of this method to decide whether the View should be recycled or not.
     * <p>
     * <p>
     * Note that when all animations are created by [RecyclerView.ItemAnimator], you
     * should never receive this callback because RecyclerView keeps those Views as children
     * until their animations are complete. This callback is useful when children of the item
     * views create animations which may not be easy to implement using an [ ].
     * <p>
     * <p>
     * You should *never* fix this issue by calling
     * `holder.itemView.setHasTransientState(false);` unless you've previously called
     * `holder.itemView.setHasTransientState(true);`. Each
     * `View.setHasTransientState(true)` call must be matched by a
     * `View.setHasTransientState(false)` call, otherwise, the state of the View
     * may become inconsistent. You should always prefer to end or cancel animations that are
     * triggering the transient state instead of handling it manually.
     *
     * @param holder The ViewHolder containing the View that could not be recycled due to its
     *               transient state.
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    public boolean onFailedToRecycleView(VH holder) {
        return false;
    }

    /**
     * Called when a view created by this adapter has been attached to a window.
     * <p>
     * <p>
     * <p>
     * This can be used as a reasonable signal that the view is about to be seen
     * by the user. If the adapter previously freed any resources in
     * [ onViewDetachedFromWindow][RecyclerView.Adapter.onViewDetachedFromWindow]
     * those resources should be restored here.
     *
     * @param holder Holder of the view being attached
     */
    public void onViewAttachedToWindow(VH holder) {
    }

    /**
     * Called when a view created by this adapter has been detached from its window.
     * <p>
     * <p>
     * <p>
     * Becoming detached from the window is not necessarily a permanent condition;
     * the consumer of an Adapter's views may choose to cache views offscreen while they
     * are not visible, attaching an detaching them as appropriate.
     *
     * @param holder Holder of the view being detached
     */
    public void onViewDetachedFromWindow(VH holder) {
    }

    /**
     * Called by RecyclerView when it starts observing this Adapter.
     * <p>
     * <p>
     * Keep in mind that same adapter may be observed by multiple RecyclerViews.
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter.
     * @see .onDetachedFromRecyclerView
     */
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see .onAttachedToRecyclerView
     */
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
