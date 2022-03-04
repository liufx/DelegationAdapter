package com.liufx.test.meishijie.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.liufx.test.R;
import com.liufx.test.databinding.ItemMeishiSancanBinding;
import com.liufx.test.meishijie.bean.Meishi;
import com.liufx.extras.binding.BindingAdapterDelegate;

import java.util.List;

/**
 * MeishiSancanAdapterDelegate
 *
 * @author liufx, Created on 2018-06-13 12:34:00
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class MeishiSancanAdapterDelegate extends BindingAdapterDelegate<Meishi.Sancan> {

    public ObservableInt selected = new ObservableInt();

    @Override
    public int getLayoutRes() {
        return R.layout.item_meishi_sancan;
    }

    @Override
    public void setVariable(ViewDataBinding binding, Meishi.Sancan sancan, int position) {
        binding.setVariable(BR.model, sancan);
        binding.setVariable(BR.view, this);
        ItemMeishiSancanBinding sancanBinding = (ItemMeishiSancanBinding) binding;

        // 未初始化过ViewPager
        if (null == sancanBinding.vpMeal.getAdapter()) {
            selected.set(sancan.select);
            MealPageAdapter adapter = new MealPageAdapter(sancan.items);
            sancanBinding.vpMeal.setAdapter(adapter);
            sancanBinding.vpMeal.setOffscreenPageLimit(sancan.items.size() - 1); // 设置最多预加载
            sancanBinding.vpMeal.setCurrentItem(sancan.select, false);
            sancanBinding.vpMeal.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    selected.set(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    /**
     * 底部图标点击的监听回调
     *
     * @param view
     * @param index
     */
    public void onMealTitleClick(View view, int index) {
        selected.set(index);
    }

    /**
     * "更多"被点击的监听回调
     *
     * @param view
     */
    public void onMoreClick(View view) {
        Toast.makeText(view.getContext(), "更多", Toast.LENGTH_SHORT).show();
    }


    static class MealPageAdapter extends PagerAdapter {

        private List<Meishi.SancanItem> itemList;

        public MealPageAdapter(List<Meishi.SancanItem> itemList) {
            this.itemList = itemList;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            ViewDataBinding binding = (ViewDataBinding) object;
            return binding.getRoot() == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            return DataBindingUtil.inflate(inflater, R.layout.layout_meishi_sancan, container, true);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            ViewDataBinding binding = (ViewDataBinding) object;
            binding.setVariable(BR.model, itemList.get(position));
            binding.executePendingBindings();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ViewDataBinding dataBinding = (ViewDataBinding) object;
            container.removeView(dataBinding.getRoot());
        }

    }
}
