package com.liufx.test.multidataandtype.binding.adapter;

import android.databinding.ViewDataBinding;

import com.liufx.test.R;
import com.liufx.test.multidataandtype.bean.BlankParameters;
import com.liufx.extras.binding.BindingAdapterDelegate;
import com.android.databinding.library.baseAdapters.BR;


/**
 * BlankAdapterDelegate
 *
 * @author liufx, Created on 2018-06-11 17:42:49
 * Major Function：<b></b>
 * <p/>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class BlankAdapterDelegate extends BindingAdapterDelegate<BlankParameters> {

    @Override
    public int getLayoutRes() {
        return R.layout.layout_item_blank;
    }

    @Override
    public void setVariable(ViewDataBinding binding, BlankParameters item, int position) {
        binding.setVariable(BR.params, item);
    }

}
