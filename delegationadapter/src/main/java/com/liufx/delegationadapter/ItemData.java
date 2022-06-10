package com.liufx.delegationadapter;

/**
 * @package: com.liufx.delegationadapter
 * @author: liufx
 * @date: 2018/12/26 10:50 AM
 * 同一个数据 不通的类型
 * @description: For the same data object corresponding to multiple Delegate
 */
public class ItemData {
    private Object data;
    private String tag;

    public ItemData() {
    }

    public ItemData(Object data, String tag) {
        this.data = data;
        this.tag = tag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
