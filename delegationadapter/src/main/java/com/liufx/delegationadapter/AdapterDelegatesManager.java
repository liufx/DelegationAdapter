package com.liufx.delegationadapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * AdapterDelegatesManager
 *
 * @author liufx, Created on 2018-12-10 23:24:58
 * Major Function：**This class is the element that ties [RecyclerView.Adapter]
 * together with [AdapterDelegate].
 * <p>
 * So you have to add / register your [AdapterDelegate]s to this manager by calling
 * [.addDelegate]
 * /b>
 * <p>
 * Note: If you modify this class please fill in the following content as a record.
 * @author liufx，Modified Date Modify Content:
 */
public class AdapterDelegatesManager {

    private SparseArray<String> dataTypeWithTags = new SparseArray();
    private SparseArrayCompat<AdapterDelegate<Object, RecyclerView.ViewHolder>> delegates = new SparseArrayCompat();
    private AdapterDelegate<Object, RecyclerView.ViewHolder> fallbackDelegate;

    public AdapterDelegate getFallbackDelegate() {
        return fallbackDelegate;
    }

    public void setFallbackDelegate(AdapterDelegate fallbackDelegate) {
        this.fallbackDelegate = fallbackDelegate;
    }

    /**
     * 根据添加的代理委托类型 和tag 进行添加
     *
     * @param delegate
     * @param tag
     * @return
     */
    public AdapterDelegatesManager addDelegate(AdapterDelegate<?, ?> delegate, String tag) {
        Type superclass = delegate.getClass().getGenericSuperclass();

        if (superclass instanceof ParameterizedType) {
            Class<?> clazz = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            String typeWithTag = typeWithTag(clazz, tag);

            int viewType = delegates.size();
            // Save the delegate to the collection;
            delegates.put(viewType, (AdapterDelegate<Object, RecyclerView.ViewHolder>) delegate);
            // Save the index of the delegate to the collection;
            dataTypeWithTags.put(viewType, typeWithTag);
        } else {
            // Has no generics.
            throw new IllegalArgumentException(
                    String.format("Please set the correct generic parameters on %s.", delegate.getClass().getName()));
        }
        return this;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate delegate = this.getDelegate(viewType);
        if (delegate != null) {
            return delegate.onCreateViewHolder(parent);
        } else {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate delegate = this.getDelegate(viewType);
        if (delegate != null) {
            delegate.onBindViewHolder(holder, position, item);
        } else {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate delegate = this.getDelegate(viewType);
        if (delegate != null) {
            delegate.onBindViewHolder(holder, position, payloads, item);
        } else {
            throw new NullPointerException("No delegate found for item at position =" + position + " for ViewType = " + viewType);
        }
    }

    /**
     * Return the view type of the item.
     * 关键代码 判断返回那个代理类型
     *
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(Object item, int position) {
        Class clazz = targetClass(item);
        String tag = targetTag(item);

        String typeWithTag = typeWithTag(clazz, tag);
        ArrayList<Integer> indexList = indexesOfValue(dataTypeWithTags, typeWithTag);
        for (int i = 0; i < indexList.size(); i++) {
            AdapterDelegate delegate = delegates.valueAt(indexList.get(i));
            if (delegate != null) {
                if (delegate.getTag().equals(tag) && delegate.isForViewType(item, position)) {
                    return indexList.get(i);
                }
            }

        }
        // If has not add the AdapterDelegate for data type, returns the largest viewType + 1.
        if (fallbackDelegate != null) {
            return delegates.size();
        }

        throw new NullPointerException("No AdapterDelegate added that matches position = " + position + " item = " + item + " in data source.");
    }

    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder != null) {
            int itemViewType = holder.getItemViewType();
            AdapterDelegate delegate = this.getDelegate(itemViewType);
            if (delegate != null) {
                delegate.onViewRecycled(holder);
            }

        }
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        if (holder != null) {
            int itemViewType = holder.getItemViewType();
            AdapterDelegate delegate = this.getDelegate(itemViewType);
            return delegate != null ? delegate.onFailedToRecycleView(holder) : false;
        } else {
            return false;
        }
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (holder != null) {
            int itemViewType = holder.getItemViewType();
            AdapterDelegate delegate = this.getDelegate(itemViewType);
            if (delegate != null) {
                delegate.onViewAttachedToWindow(holder);
            }

        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder != null) {
            int itemViewType = holder.getItemViewType();
            AdapterDelegate delegate = this.getDelegate(itemViewType);
            if (delegate != null) {
                delegate.onViewDetachedFromWindow(holder);
            }

        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        for (int i = 0; i < this.delegates.size(); ++i) {
            AdapterDelegate delegate = (AdapterDelegate) this.delegates.get(this.delegates.keyAt(i));
            delegate.onAttachedToRecyclerView(recyclerView);
        }

    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {

        for (int i = 0; i < this.delegates.size(); ++i) {
            AdapterDelegate delegate = (AdapterDelegate) this.delegates.get(this.delegates.keyAt(i));
            delegate.onDetachedFromRecyclerView(recyclerView);
        }

    }

    public AdapterDelegate getDelegate(int viewType) {
        return (AdapterDelegate) this.delegates.get(viewType, this.fallbackDelegate);
    }

    /**
     * 循环找出 符合的item的下标
     *
     * @param array
     * @param value
     * @return
     */
    private ArrayList<Integer> indexesOfValue(SparseArray array, String value) {
        ArrayList<Integer> indexes = new ArrayList();
        for (int i = 0; i < array.size(); ++i) {
            if (value.equals(array.valueAt(i))) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    /**
     * 获取类的tag
     *
     * @param clazz
     * @param tag
     * @return
     */
    private static String typeWithTag(Class<?> clazz, String tag) {
        if (tag.isEmpty()) {
            return clazz.getName();
        } else {
            return clazz.getName() + ":" + tag;
        }
    }

    /**
     * 根据对象返回具体类名
     *
     * @param data
     * @return
     */
    private static Class<?> targetClass(Object data) {
        if (data instanceof ItemData) {
            return (((ItemData) data).getData()).getClass();
        } else {
            return data.getClass();
        }
    }

    /**
     * 返回对象的tag
     *
     * @param data
     * @return
     */
    private static String targetTag(Object data) {
        if (data instanceof ItemData) {
            return ((ItemData) data).getTag();
        } else {
            return AdapterDelegate.DEFAULT_TAG;
        }
    }
}
