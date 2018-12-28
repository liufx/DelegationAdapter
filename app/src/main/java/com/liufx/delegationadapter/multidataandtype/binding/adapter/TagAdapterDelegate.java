package com.liufx.delegationadapter.multidataandtype.binding.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.liufx.delegationadapter.R;
import com.liufx.delegationadapter.bean.Goods;
import com.liufx.extras.binding.BindingSpanAdapterDelegate;
import com.android.databinding.library.baseAdapters.BR;

/**
 * TagAdapterDelegate
 *
 * @author liufx, Created on 2018-06-11 16:34:51
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class TagAdapterDelegate extends BindingSpanAdapterDelegate<Goods.TagItem> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_goods_tag;
    }

    @Override
    public void setVariable(ViewDataBinding binding, Goods.TagItem item, int position) {
        binding.setVariable(BR.tagItem, item);
    }

    @Override
    public int getSpanSize() {
        return 1;
    }

    @Override
    public void onItemClick(View view, Goods.TagItem item, int position) {
        Toast.makeText(view.getContext(), item.title, Toast.LENGTH_SHORT).show();
    }
}
