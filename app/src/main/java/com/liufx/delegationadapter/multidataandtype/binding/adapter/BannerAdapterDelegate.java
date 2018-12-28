package com.liufx.delegationadapter.multidataandtype.binding.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.kevin.loopview.BannerView;
import com.kevin.loopview.internal.BaseLoopAdapter;
import com.kevin.loopview.internal.ImageLoader;
import com.kevin.loopview.internal.LoopData;
import com.liufx.delegationadapter.R;
import com.liufx.delegationadapter.databinding.ItemGoodsBannerBinding;
import com.liufx.extras.binding.BindingAdapterDelegate;

/**
 * BannerAdapterDelegate
 *
 * @author liufx, Created on 2018-04-07 21:53:19
 * Major Function：<b></b>
 * <p/>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class BannerAdapterDelegate extends BindingAdapterDelegate<LoopData> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_goods_banner;
    }


    @Override
    public void setVariable(ViewDataBinding binding, LoopData item, int position) {
        BannerView bannerView = ((ItemGoodsBannerBinding) binding).bannerView;
        LoopData data = bannerView.getData();
        if (item != data) {
            binding.setVariable(BR.ban, item);
        }

        bannerView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, int placeholder) {
                Glide.with(imageView.getContext()).load(url).into(imageView);
            }
        });

        // 设置轮转大图点击监听
        bannerView.setOnItemClickListener(new BaseLoopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, LoopData.ItemData itemData, int position) {
                Toast.makeText(view.getContext(), itemData.link, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
