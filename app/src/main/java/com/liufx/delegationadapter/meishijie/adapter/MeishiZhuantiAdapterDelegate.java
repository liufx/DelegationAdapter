package com.liufx.delegationadapter.meishijie.adapter;

import android.databinding.ViewDataBinding;

import com.android.databinding.library.baseAdapters.BR;
import com.liufx.delegationadapter.R;
import com.liufx.delegationadapter.meishijie.bean.Meishi;
import com.liufx.extras.binding.BindingAdapterDelegate;

/**
 * MeishiSancanAdapterDelegate
 *
 * @author liufx, Created on 2018-06-13 12:34:00
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class MeishiZhuantiAdapterDelegate extends BindingAdapterDelegate<Meishi.ZhuantiItem> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_meishi_zhuanti;
    }

    @Override
    public void setVariable(ViewDataBinding binding, Meishi.ZhuantiItem item, int position) {
        binding.setVariable(BR.model, item);
    }

}
