package com.liufx.delegationadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: com.liufx.delegationadapter
 * @author: liufx
 * @date: 2018/12/26 12:54 PM
 * Copyright © 2018 中国电信甘肃万维公司. All rights reserved.
 * @description: 代理适配器
 */
public class DelegationAdapter extends AbsDelegationAdapter {

    private List dataItems;
    private List headerItems;
    private List footerItems;

    public final void clearData() {
        this.dataItems.clear();
    }

    public final void clearHeader() {
        this.headerItems.clear();
    }

    public final void clearFooter() {
        this.footerItems.clear();
    }

    public final void clearAllData() {
        this.clearData();
        this.clearHeader();
        this.clearFooter();
    }

    public DelegationAdapter() {
        this.dataItems = (List) (new ArrayList());
        this.headerItems = (List) (new ArrayList());
        this.footerItems = (List) (new ArrayList());
    }

    public DelegationAdapter(AdapterDelegatesManager delegatesManager) {
        super(delegatesManager);
        this.dataItems = (List) (new ArrayList());
        this.headerItems = (List) (new ArrayList());
        this.footerItems = (List) (new ArrayList());
    }

    public final int getDataCount() {
        return this.dataItems.size();
    }

    public final int getHeaderCount() {
        return this.headerItems.size();
    }

    public final int getFooterCount() {
        return this.footerItems.size();
    }

    public final void setHeaderItem(Object headerItem) {

        this.headerItems.clear();
        if (headerItem != null) {
            this.headerItems.add(headerItem);
        }
        this.notifyDataSetChanged();


    }

    public final void setHeaderItems(List headerItems) {

        this.headerItems.clear();
        if (headerItems != null) {
            this.headerItems.addAll(headerItems);
        }
        this.notifyDataSetChanged();

    }

    public final void addHeaderItem(Object headerItem) {
        if (headerItem != null) {
            this.addHeaderItem(this.getHeaderCount(), headerItem);
        }

    }

    public final void addHeaderItem(int position, Object headerItem) {
        if (headerItem != null) {
            this.headerItems.add(position, headerItem);
            this.notifyItemRangeInserted(position, 1);
        }
    }

    public final void addHeaderItems(List headerItems) {
        if (headerItems != null) {
            this.addHeaderItems(this.getHeaderCount(), headerItems);
        }
    }

    public final void addHeaderItems(int position, List headerItems) {
        if (headerItems != null) {
            this.headerItems.addAll(position, headerItems);
            this.notifyItemRangeInserted(position, headerItems.size());
        }
    }

    public final void setFooterItem(Object footerItem) {

        footerItems.clear();
        if (footerItem != null) {
            footerItems.add(footerItem);
        }
        notifyDataSetChanged();

    }

    public final void setFooterItems(List footerItems) {
        this.footerItems.clear();
        if (footerItems != null) {
            this.footerItems.addAll(footerItems);
        }
        notifyDataSetChanged();

    }

    public final void addFooterItem(Object footerItem) {
        if (footerItem != null) {
            addFooterItem(getFooterCount(), footerItem);
        }
    }

    public final void addFooterItem(int position, Object footerItem) {
        if (footerItem != null) {
            footerItems.add(position, footerItem);
            notifyItemRangeInserted(getHeaderCount() + getDataCount() + position, 1);
        }
    }

    public final void addFooterItems(List footerItems) {
        if (footerItems != null) {
            addFooterItems(getFooterCount(), footerItems);
        }

    }

    public final void addFooterItems(int position, List footerItems) {
        if (footerItems != null) {
            this.footerItems.addAll(position, footerItems);
            notifyItemRangeInserted(getHeaderCount() + getDataCount() + position, footerItems.size());
        }

    }

    public final void setDataItems(List dataItems) {
        this.dataItems.clear();
        if (dataItems != null) {
            this.dataItems.addAll(dataItems);
        }
        notifyDataSetChanged();
    }

    public final void addDataItem(Object item) {
        addDataItem(getDataCount(), item);
    }

    public final void addDataItem(int position, Object item) {
        if (item != null) {
            dataItems.add(position, item);
            notifyItemRangeInserted(getHeaderCount() + position, 1);
        }
    }

    public final void addDataItems(List dataItems) {
        addDataItems(getDataCount(), dataItems);
    }

    public final void addDataItems(int position, List dataItems) {
        if (dataItems != null) {
            this.dataItems.addAll(position, dataItems);
            notifyItemRangeInserted(getHeaderCount() + position, dataItems.size());
        }
    }

    public final void moveDataItem(int fromPosition, int toPosition) {
        int moveToPosition = toPosition;
        if (fromPosition < moveToPosition) {
            moveToPosition = moveToPosition - 1;
        } else {
            moveToPosition = moveToPosition;
        }
        dataItems.add(moveToPosition, dataItems.remove(fromPosition));
        notifyItemMoved(fromPosition, moveToPosition);
    }

    public final void removeDataItem(Object dataItem) {
        int index = dataItems.indexOf(dataItem);
        if (index != -1 && index <= getDataCount()) {
            removeDataItemAt(index);
        }
    }

    public final void removeDataItemAt(int position) {

        dataItems.remove(position);

        notifyItemRangeRemoved(getHeaderCount() + position, 1);
    }

    public final void removeDataItemAt(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            if (i == position) {
                dataItems.remove(position);
            }

        }
        notifyItemRangeRemoved(getHeaderCount() + position, itemCount);
    }


    public List getDataItems() {
        return dataItems;
    }

    public List getHeaderItems() {
        return headerItems;
    }

    public List getFooterItems() {
        return footerItems;
    }

    @Override
    public Object getItem(int position) {
        if (position < getHeaderCount()) {
            return headerItems.get(position);
        }

        int offsetPosition = position - getHeaderCount();
        if (offsetPosition < getDataCount()) {
            return dataItems.get(offsetPosition);
        }

        offsetPosition -= getDataCount();
        return footerItems.get(offsetPosition);
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getDataCount() + getFooterCount();
    }
}
