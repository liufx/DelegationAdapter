package com.liufx.test.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewBindingAdapter
 *
 * @author liufx, Created on 2018-06-11 17:47:23
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class ViewBindingAdapter {

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float dipValue) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            final float scale = view.getResources().getDisplayMetrics().density;
            lp.height = (int) (dipValue * scale + 0.5f);
            view.setLayoutParams(lp);
        }
    }

}
