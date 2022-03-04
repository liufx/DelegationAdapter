package com.liufx.test.multidataandtype.binding.adapter;

import android.databinding.ViewDataBinding;

import com.liufx.test.R;
import com.liufx.test.bean.Goods;
import com.liufx.extras.binding.BindingAdapterDelegate;
import com.android.databinding.library.baseAdapters.BR;

/**
 * ArticleAdapterDelegate
 *
 * @author liufx, Created on 2018-06-11 19:20:25
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class ArticleAdapterDelegate extends BindingAdapterDelegate<Goods.Article> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_goods_articles;
    }

    @Override
    public void setVariable(ViewDataBinding binding, Goods.Article item, int position) {
        binding.setVariable(BR.article, item);
    }

}
